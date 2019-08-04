package nycuro.api.data;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nukkitcoders.mobplugin.entities.monster.flying.Wither;
import nycuro.ai.entity.BossEntity;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.mechanic.objects.SettingsObject;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
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

    public boolean isOnArea(Player player) {
        return API.getMainAPI().isOnArea.getBoolean(player.getUniqueId());
    }


    public float getBossHealth() {
        for (Entity entity : API.getMainAPI().getServer().getDefaultLevel().getEntities()) {
            if (entity.namedTag.getBoolean("coreBOSS")) {
                return entity.getHealth();
            }
        }
        return 0;
    }

    public String getOS(Player player) {
        switch (player.getLoginChainData().getDeviceOS()) {
            case 1:
                return "Android";
            case 2:
                return "iOS";
            case 3:
                return "Mac";
            case 4:
                return "Fire";
            case 5:
                return "Gear VR";
            case 6:
                return "HoloLens";
            case 7:
                return "Windows 10";
            case 8:
                return "Windows";
            case 9:
                return "Dedicated";
            case 10:
                return "tvOS";
            case 11:
                return "PlayStation";
            case 12:
                return "NX";
            case 13:
                return "Xbox";
            default:
                return "Unknown";
        }
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

    public void handleTransferHub() {
        InetSocketAddress hub = new InetSocketAddress("mcpe.chzone.eu", 19132);
        for (Player player1 : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            player1.transfer(hub);
        }
    }
    public void handleTransferHub(Player player) {
        InetSocketAddress hub = new InetSocketAddress("mcpe.chzone.eu", 19132);
        player.transfer(hub);
    }

    public String helpopTag = TextFormat.GRAY + "[" + TextFormat.YELLOW + "HELPOP" + TextFormat.GRAY + "]" + " " + TextFormat.GOLD;
    public String staffchatTag = TextFormat.GRAY + "[" + TextFormat.RED + "STAFFCHAT" + TextFormat.GRAY + "]" + " " + TextFormat.RED;

    public void handleStaffChat(Player player, String message, int type) {
        switch (type) {
            case 0:
                if (message.contains("@")) {
                    String msg = message.substring(0, message.indexOf("@")) + message.substring(message.indexOf("@") + 1); // before and after "@"
                    handleHelpop(player, msg, staffchatTag);
                }
                break;
            case 1:
                handleHelpop(player, message, staffchatTag);
                break;
        }
    }

    public void handleHelpop(Player pp, String message, String tag) {
        if (API.getMainAPI().staffChat.isEmpty()) {
            int i = 0;
            for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                if (player.isOp()) {
                    i++;
                    player.sendMessage(tag + message);
                }
            }
            if (i == 0) {
                API.getMessageAPI().sendNoStaffOnlineMessage(pp);
            } else {
                if (!pp.hasPermission("core.staffchat")) {
                    pp.sendMessage(tag + message);
                }
            }
        } else {
            if (!pp.hasPermission("core.staffchat")) {
                pp.sendMessage(tag + message);
            }
            API.getMainAPI().staffChat.forEach(
                    (p) -> {
                        API.getMainAPI().getServer().getPlayer(p).ifPresent((pl) -> {
                            pl.sendMessage(tag + message);
                        });
                    }
            );
        }
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

    public boolean checkItems(Player player, Item[] items) {
        for (Item item : items) {
            for (Item content : player.getInventory().getContents().values()) {
                try {
                    if (player.getInventory().isEmpty()) {
                        return false;
                    }
                    if (item.equals(content) && item.getCount() == content.getCount() && content.getNamedTag().exist("NPC")) {
                        return true;
                    }
                } catch (NullPointerException e) { // idk why
                }
            }
        }
        return false;
    }

    public boolean checkItems(Player player, Item item) {
        for (Item content : player.getInventory().getContents().values()) {
            try {
                if (player.getInventory().isEmpty()) {
                    return false;
                }
                if (item.equals(content) && item.getCount() == content.getCount() && content.getNamedTag().exist("NPC")) {
                    return true;
                }
            } catch (NullPointerException e) { // idk why
            }
        }
        return false;
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

    public void sendSettingsForm(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Settings Player");
        infoMenu.addElement(new ElementLabel(API.getMessageAPI().sendInfoMessageSettings(player)));
        infoMenu.addElement(new ElementToggle("Show BossBar", true));
        infoMenu.addElement(new ElementToggle("Show Scoreboard", true));
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        infoMenu.addElement(new ElementStepSlider("Toggle Render Distance", list));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    SettingsObject settings = API.getMainAPI().settings.get(player.getUniqueId());
                    if (settings != null) {
                        settings.setBossbarValue(Boolean.valueOf(String.valueOf(response.values().toArray()[1])));
                        settings.setScoreboardValue(Boolean.valueOf(String.valueOf(response.values().toArray()[2])));
                        player.setViewDistance(Integer.valueOf(String.valueOf(response.values().toArray()[3])));

                        DummyBossBar bb = API.getMainAPI().bossbar.get(player.getUniqueId());
                        if (bb != null) {
                            bb.destroy();
                        }
                        if (Boolean.valueOf(String.valueOf(response.values().toArray()[1]))) {
                            createBossBar(player);
                        } else {
                            API.getMainAPI().bossbar.remove(player.getUniqueId());
                        }

                        FakeScoreboard fsc = API.getMainAPI().scoreboard.get(player.getUniqueId());
                        if (fsc != null) {
                            fsc.removePlayer(player);
                        }
                        if (Boolean.valueOf(String.valueOf(response.values().toArray()[2]))) {
                            createScoreboard(player);
                        } else {
                            API.getMainAPI().scoreboard.remove(player.getUniqueId());
                        }
                    }
                }
            }
        }));
    }
}
