package nycuro.api.data;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.utils.DummyBossBar;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nukkitcoders.mobplugin.entities.monster.flying.Wither;
import nycuro.api.API;
import nycuro.ai.entity.BossEntity;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;

import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MechanicAPI {

    public boolean isOnSpawn(Player player) {
        return API.getMainAPI().isOnSpawn.getBoolean(player.getUniqueId());
    }

    public boolean isOnPrincipalWorld(Player player) {
        return player.getLevel().equals(API.getMainAPI().getServer().getDefaultLevel()) && !player.isOp();
    }

    public boolean isOnArena(Player player) {
        return API.getMainAPI().isOnArena.getBoolean(player.getUniqueId());
    }

    public float getBossHealth() {
        for (Entity entity : API.getMainAPI().getServer().getDefaultLevel().getEntities()) {
            if (entity.namedTag.getBoolean("coreBOSS")) {
                return entity.getHealth();
            }
        }
        return 0;
    }

    public void spawnNPC(String boolTag, int id, int x, int y, int z) {
        CompoundTag tag = new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", x + 0.5))
                        .add(new DoubleTag("", y))
                        .add(new DoubleTag("", z + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) 0))
                        .add(new FloatTag("", (float) 0)))
                .putBoolean("Invulnerable", true)
                .putString("NameTag", "coreNBT")
                .putList(new ListTag<StringTag>("Commands"))
                .putList(new ListTag<StringTag>("PlayerCommands"))
                .putBoolean(boolTag, true)
                .putFloat("scale", 1);
        Entity entity = Entity.createEntity(id, API.getMainAPI().getServer().getDefaultLevel().getChunk(x >> 4, z >> 4), tag);
        entity.spawnToAll();
    }

    public boolean isPlayerInsideOfArea(Player player, double[] d1, double[] d2, double[] d3) {
        if (player.getLocation().getX() > d1[0] && player.getLocation().getX() < d1[1]) {
            return false;
        }
        if (player.getLocation().getZ() > d2[0] && player.getLocation().getZ() < d2[1]) {
            return false;
        }

        if (player.getLocation().getY() > d3[0] && player.getLocation().getY() < d3[1]) {
            return false;
        }

        return true;
    }

    public void sendToSpawn(Player player) {
        Level level = API.getMainAPI().getServer().getDefaultLevel();
        player.teleport(level.getSpawnLocation());
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
        API.getMainAPI().bossbar.put(player.getUniqueId(), bossbar);
        player.createBossBar(bossbar);
    }

    public void createScoreboard(Player player) {
        FakeScoreboard fakeScoreboard = new FakeScoreboard();
        Objective object = new Objective("§f§l•§e•§6• SKYBLOCK §6•§e•§f•", new ObjectiveCriteria("dummy", true));
        DisplayObjective newObject = new DisplayObjective(
                object,
                ObjectiveSortOrder.DESCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        fakeScoreboard.objective = newObject;
        fakeScoreboard.addPlayer(player);
        API.getMainAPI().scoreboard.put(player.getUniqueId(), fakeScoreboard);
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
                                ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                                double dolllars = profileSkyblock.getDollars();
                                if (dolllars < 10000) {
                                    API.getMessageAPI().sendUnsuficientMoneyMessage(player, 10000 - profileSkyblock.getDollars());
                                    return;
                                } else {
                                    API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "mob spawn 52 " + player.getName());
                                    profileSkyblock.setDollars(profileSkyblock.getDollars() - 10000);
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
        FormWindowSimple jobsMenu = new FormWindowSimple("Arena Category", API.getMessageAPI().sendArenaPrincipalModal(player));
        jobsMenu.addButton(new ElementButton("Info", new ElementButtonImageData("url", "https://i.imgur.com/uWmtrax.png")));
        jobsMenu.addButton(new ElementButton("Boss Arena", new ElementButtonImageData("url", "https://i.imgur.com/XFCYdCz.png")));
        jobsMenu.addButton(new ElementButton("PvP Arena", new ElementButtonImageData("url", "https://i.imgur.com/otMDlEU.png")));
        player.showFormWindow(new ResponseFormWindow(jobsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendInfoMessageArena(player);
                            return;
                        case 1:
                            teleportArenaBoss(player);
                            return;
                        case 2:
                            teleportArenaPvP(player);
                            break;
                    }
                }
            }
        }));
    }

    private void sendInfoMessageArena(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Arena Info");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageArena(player)));
        player.showFormWindow(infoMenu);
    }

    private void teleportArenaBoss(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Teleport Boss Arena");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().teleportBossArenaMessages(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                            int level = profileSkyblock.getLevel();
                            if (level < 10) {
                                player.sendMessage(API.getMessageAPI().sendArenaException(player, 10));
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

    private void teleportArenaPvP(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Teleport PvP Arena");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().teleportPvPArenaMessages(player)));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                            int level = profileSkyblock.getLevel();
                            if (level < 5) {
                                player.sendMessage(API.getMessageAPI().sendArenaException(player, 5));
                                return;
                            } else {
                                player.teleport(new Location(1106, 70, 1311, API.getMainAPI().getServer().getDefaultLevel()));
                                player.sendMessage(API.getMessageAPI().sendTeleportArena(player));
                                return;
                            }
                    }
                }
            }
        }));
    }
}
