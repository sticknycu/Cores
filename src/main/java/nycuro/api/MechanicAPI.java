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
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.DummyBossBar;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nukkitcoders.mobplugin.entities.monster.flying.Wither;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;
import nycuro.gui.list.ResponseFormWindow;

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

    public boolean isOnPvP(Entity entity) {
        double x = entity.getLevel().getSpawnLocation().getX();
        double y = entity.getLevel().getSpawnLocation().getY();
        double z = entity.getLevel().getSpawnLocation().getZ();
        Vector3 vector3 = new Vector3(x, y, z);
        if (entity instanceof Player) {
            return entity.getLevel().getName().equalsIgnoreCase("pvp") && entity.getPosition().distance(vector3) <= 34 && !((Player) entity).isOp() && entity.getY() >= 75;
        } else {
            return entity.getLevel().getName().equalsIgnoreCase("pvp") && entity.getPosition().distance(vector3) <= 34 && entity.getY() >= 75;
        }
    }

    public boolean isOnBorder(Player player) {
        double x = player.getX();
        double z = player.getZ();
        return (x >= 7500 || x <= -7500) || (z >= 7500 || z <= -7500);
    }

    public void sendToSpawn(Player player) {
        Level level = API.getMainAPI().getServer().getDefaultLevel();
        if (!level.isChunkLoaded(level.getSpawnLocation().getChunkX(), level.getSpawnLocation().getChunkZ())) {
            level.loadChunk(level.getSpawnLocation().getChunkX(), level.getSpawnLocation().getChunkZ());
        }
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

    /*public void spawnBoss() {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            addDropPartyKey(player);
            new BossEntity();
            API.getMessageAPI().sendDropPartySpawnedMessage(player);
        }
    }*/

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
        ProfileProxy profile = Database.profileProxy.get(player.getName());
        int lang = profile.getLanguage();
        switch (lang) {
            case 0:
                infoMenu.addElement(new ElementLabel("                      Hello!\n" +
                        "         Welcome to Spawn Wither!\n\n" +
                        "§c» §aHow can i spawn a Wither?\n" +
                        "§eFor spawning a Wither is enough to press the button.\n" +
                        "§eYou can cancel that pushing X button from up, right side." +
                        "§c» §aWhat can i do with Wither: \n" +
                        "§eWith Wither you can raid bases and get more experience.\n" +
                        "§c» §aPrice: §610k$ \n" +
                        "§eA little information: Maximum of Withers on Server is 10, so if there exists 10 Withers spawned you need to wait to minimum 1 to be despawned!\n" +
                        "§eAnd about despawning of entities, entities are despawned every 5 minutes, so be careful!\n" +
                        "§eThank you for purchase!\n" +
                        "§eHave a nice day!"));
                break;
            case 1:
                infoMenu.addElement(new ElementLabel("                      Salut!\n" +
                        "      Bine ai venit la Wither Spawn!\n\n" +
                        "§c» §aCum pot spawna un Wither:\n" +
                        "§ePentru a spawna un wither este de ajuns pentru a apasa pe buton.\n" +
                        "§ePoti anula acest lucru apasand pe butonul din dreapta sus, X." +
                        "§c» §aCe pot face cu Wither?: \n" +
                        "§eEi bine, cu Wither poti da raid bazelor si poti castiga mult exp.\n" +
                        "§c» §aPretul: §610k$ \n" +
                        "§eO mica informatie: Maximul de Witheri pe server este de 10, deci daca exista deja 10 Witheri spawnati pe server, trebuie sa astepti ca cel putin 1 sa fie despawnat!\n" +
                        "§eSi despre spawnarea entitatilor, entitatile se despawneaza odata la 5 minute, deci fi atent!\n" +
                        "§eMultumim pentru achizitie si te mai asteptam!\n" +
                        "§eSa ai o zi buna!"));
                break;
        }
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
                                API.getMessageAPI().sendTooMuchWithers(player);
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
}
