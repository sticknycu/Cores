package nycuro.api;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.DummyBossBar;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nukkitcoders.mobplugin.entities.monster.flying.Wither;
import nycuro.API;
import nycuro.Loader;
import nycuro.ai.entity.BossEntity;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.gui.list.ResponseFormWindow;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class MechanicAPI {

    public boolean isOnSpawn(Player player) {
        return Loader.isOnSpawn.getBoolean(player.getName());
    }

    public boolean isOnArena(Player player) {
        return API.getMainAPI().isOnArena.getBoolean(player);
    }

    public float getBossHealth() {
        for (Entity entity : API.getMainAPI().getServer().getDefaultLevel().getEntities()) {
            if (entity.namedTag.getBoolean("coreBOSS")) {
                return entity.getHealth();
            }
        }
        return 0;
    }

    public boolean isPlayerInsideOfArea(Player player, Vector3 vecFrom, Vector3 vecTo) {
        double[] dim = new double[2];

        dim[0] = vecFrom.getX();
        dim[1] = vecTo.getX();
        Arrays.sort(dim);
        if (player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0]) {
            return false;
        }

        dim[0] = vecFrom.getZ();
        dim[1] = vecTo.getZ();
        Arrays.sort(dim);
        if (player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0]) {
            return false;
        }

        dim[0] = vecFrom.getY();
        dim[1] = vecTo.getY();
        Arrays.sort(dim);
        if (player.getLocation().getY() > dim[1] || player.getLocation().getY() < dim[0]) {
            return false;
        }

        return true;
    }

    public boolean isOnPvP(Player player) {
        return API.getMainAPI().isOnPvP.getBoolean(player);
    }

    public void sendToSpawn(Player player) {
        Level level = API.getMainAPI().getServer().getDefaultLevel();
        player.teleport(level.getSpawnLocation());
        player.setImmobile(false);
    }

    public void sendStats(CommandSender commandSender, IPlayer player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Stats");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().getStatsCommand(commandSender, player)));
        ((Player) commandSender).showFormWindow(infoMenu);
    }

    /*public void spawnFireworks() {
        entities.forEach(Entity::spawnToAll);
    }*/

    public void spawnDropParty() {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            addDropPartyKey(player);
            new BossEntity();
            API.getMessageAPI().sendDropPartySpawnedMessage(player);
        }
    }

    public void sendDropPartyMessageBroadcast(Player player) {
        API.getMessageAPI().sendDropPartyEventMessage(player);
    }

    private void addDropPartyKey(Player player) {
        Item dropPartyKey = Item.get(Item.TRIPWIRE_HOOK, 1, 1);
        Enchantment enchantment = Enchantment.get(Enchantment.ID_PROTECTION_ALL);
        enchantment.setLevel(1);
        dropPartyKey.setCustomName("DropParty Key");
        dropPartyKey.addEnchantment(enchantment);
        player.getInventory().addItem(dropPartyKey);
        API.getMessageAPI().sendDropPartyReceiveKeyMessage(player);
    }

    public void createBossBar(Player player) {
        DummyBossBar bossbar = new DummyBossBar.Builder(player)
                .text("bossbar")
                .length(100F)
                .build();
        API.getMainAPI().bossbar.put(player.getName(), bossbar);
        player.createBossBar(bossbar);
    }

    public void createScoreboard(Player player) {
        FakeScoreboard fakeScoreboard = new FakeScoreboard();
        Objective object = new Objective("§f§l•§e•§6• FACTIONS §6•§e•§f•", new ObjectiveCriteria("dummy", true));
        DisplayObjective newObject = new DisplayObjective(
                object,
                ObjectiveSortOrder.DESCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        fakeScoreboard.objective = newObject;
        fakeScoreboard.addPlayer(player);
        API.getMainAPI().scoreboard.put(player.getName(), fakeScoreboard);
    }

    public void spawnWither(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Spawn Wither");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().spawnWitherMessages(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            if (API.getMechanicAPI().isOnSpawn(player)) {
                                player.sendMessage(API.getMessageAPI().sendWitherSpawnMessage(player));
                                return;
                            }
                            if (Wither.count > 10) {
                                player.sendMessage(API.getMessageAPI().sendTooMuchWithers(player));
                                return;
                            } else {
                                ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
                                double dolllars = profileFactions.getDollars();
                                if (dolllars < 10000) {
                                    API.getMessageAPI().sendUnsuficientMoneyMessage(player, 10000 - profileFactions.getDollars());
                                    return;
                                } else {
                                    API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "mob spawn 52 " + player.getName());
                                    profileFactions.setDollars(profileFactions.getDollars() - 10000);
                                    API.getMessageAPI().sendSuccesSpawnWither(player);
                                    return;
                                }
                            }
                    }
                }
            }
        }));
    }

    public void teleportArena(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Teleport Arena");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().teleportArenaMessages(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
                            int level = profileFactions.getLevel();
                            if (level < 10) {
                                player.sendMessage(API.getMessageAPI().sendArenaException(player));
                                return;
                            } else {
                                player.teleport(new Location(1092, 70, 1324, API.getMainAPI().getServer().getDefaultLevel()));
                                player.sendMessage(API.getMessageAPI().sendTeleportArena(player));
                                return;
                            }
                    }
                }
            }
        }));
    }
}
