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
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.manager.UserManager;
import nukkitcoders.mobplugin.entities.monster.flying.Wither;
import nycuro.ai.entity.BossEntity;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.mechanic.objects.SettingsObject;
import nycuro.messages.handlers.ChatHandlers;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static nycuro.api.API.*;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MechanicAPI {

    public boolean isOnSpawn(Player player) {
        return mainAPI.isOnSpawn.getBoolean(player.getUniqueId());
    }

    public boolean isOnPrincipalWorld(Player player) {
        return player.getLevel().equals(mainAPI.getServer().getDefaultLevel()) && !player.isOp();
    }

    public boolean isOnArena(Player player) {
        return mainAPI.isOnArena.getBoolean(player.getUniqueId());
    }

    public boolean isOnArea(Player player) {
        return mainAPI.isOnArea.getBoolean(player.getUniqueId());
    }

    private User giveMeADamnUser(UUID uuid) {
        UserManager userManager = ChatHandlers.api.getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);

        return userFuture.join(); // ouch!
    }

    public String getRank(IPlayer player) {
        String rank = "";
        if (mainAPI.getServer().lookupName(player.getName()).isPresent()) {
            String group = giveMeADamnUser(mainAPI.getServer().lookupName(player.getName()).get()).getPrimaryGroup().toUpperCase();
            String sGroup = group.toLowerCase();
            switch (sGroup) {
                case "default":
                    rank = "&l&o&6PLAYER&r";
                    break;
                case "premium":
                    rank = "&l&o&ePREMIUM&r";
                    break;
                case "vip":
                    rank = "&l&o&3VIP&r";
                    break;
                case "helper":
                    rank = "&l&o&aHELPER&r";
                    break;
                case "moderator":
                    rank = "&l&o&bMODERATOR&r";
                    break;
                case "yt":
                    rank = "&l&o&fYT&r";
                    break;
                case "admin":
                    rank = "&l&o&4ADMIN&r";
                    break;
            }
        }
        return TextFormat.colorize(rank);
    }


    public float getBossHealth() {
        for (Entity entity : mainAPI.getServer().getDefaultLevel().getEntities()) {
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
        Entity entity = Entity.createEntity(id, mainAPI.getServer().getDefaultLevel().getChunk(x >> 4, z >> 4), tag);
        entity.spawnToAll();
    }

    public boolean isPlayerInsideOfArea(Player player, double[] d1, double[] d2, double[] d3) {
        if (player.getLocation().getX() > d1[0] && player.getLocation().getX() < d1[1]) {
            return false;
        }
        if (player.getLocation().getZ() > d2[0] && player.getLocation().getZ() < d2[1]) {
            return false;
        }

        return !(player.getLocation().getY() > d3[0]) || !(player.getLocation().getY() < d3[1]);
    }

    public void sendToSpawn(Player player) {
        Level level = mainAPI.getServer().getDefaultLevel();
        player.teleport(level.getSpawnLocation());
    }

    public void sendStats(CommandSender commandSender, IPlayer player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("stats.form.first", player.getName()));
        ProfileProxy profileProxy = Database.profileProxy.get(commandSender.getName());
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date firstPlay = new Date(player.getFirstPlayed());
        Date lastPlay = new Date(player.getLastPlayed());
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.name", player.getName())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.rank", mechanicAPI.getRank(player))));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.level", mainAPI.emptyNoSpace + profileSkyblock.getLevel())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.experience", mainAPI.emptyNoSpace + profileSkyblock.getExperience(),
                mainAPI.emptyNoSpace + profileSkyblock.getNecesary())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.firstjoin", simple.format(firstPlay))));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.online", (player.isOnline() ? "§3YES§6" : simple.format(lastPlay)))));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.onlinetime", API.time(profileSkyblock.getTime()))));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.dollars", mainAPI.emptyNoSpace + profileSkyblock.getDollars())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.gems", mainAPI.emptyNoSpace + profileProxy.getGems())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.kills", mainAPI.emptyNoSpace + profileSkyblock.getKills())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.deaths", mainAPI.emptyNoSpace + profileSkyblock.getDeaths())));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("stats.form.votes", mainAPI.emptyNoSpace + profileProxy.getVotes())));
        infoMenu.addElement(new ElementLabel((player.isOnline() ? ((commandSender.isOp() ? (
                messageAPI.messagesObject.translateMessage("stats.form.ip", player.getPlayer().getAddress())
                ) : (""))) : (""))));
        infoMenu.addElement(new ElementLabel((player.isOnline() ? ((commandSender.isOp() ? (
                messageAPI.messagesObject.translateMessage("stats.form.devicemodel", player.getPlayer().getLoginChainData().getDeviceModel())
        ) : (""))) : (""))));
        infoMenu.addElement(new ElementLabel((player.isOnline() ?
                (messageAPI.messagesObject.translateMessage("stats.form.os", mechanicAPI.getOS(player.getPlayer()))
                ) : "")
        ));
        ((Player) commandSender).showFormWindow(infoMenu);
    }

    public void handleTransferHub() {
        InetSocketAddress hub = new InetSocketAddress("mcpe.chzone.eu", 19132);
        for (Player player1 : mainAPI.getServer().getOnlinePlayers().values()) {
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
        if (mainAPI.staffChat.isEmpty()) {
            int i = 0;
            for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
                if (player.isOp()) {
                    i++;
                    player.sendMessage(tag + message);
                }
            }
            if (i == 0) {
                pp.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.nostaffonline"));
            } else {
                if (!pp.hasPermission("core.staffchat")) {
                    pp.sendMessage(tag + message);
                }
            }
        } else {
            if (!pp.hasPermission("core.staffchat")) {
                pp.sendMessage(tag + message);
            }
            mainAPI.staffChat.forEach(
                    (p) -> {
                        mainAPI.getServer().getPlayer(p).ifPresent((pl) -> {
                            pl.sendMessage(tag + message);
                        });
                    }
            );
        }
    }

    public void spawnDropParty() {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            addDropPartyKey(player);
            new BossEntity();
            player.sendMessage(messageAPI.messagesObject.translateMessage("generic.dropparty.spawned"));
        }
    }

    private void addDropPartyKey(Player player) {
        Item dropPartyKey = Item.get(Item.TRIPWIRE_HOOK, 1, 1);
        Enchantment enchantment = Enchantment.get(Enchantment.ID_PROTECTION_ALL);
        enchantment.setLevel(1);
        dropPartyKey.setCustomName("DropParty Key");
        dropPartyKey.addEnchantment(enchantment);
        player.getInventory().addItem(dropPartyKey);
        player.sendMessage(messageAPI.messagesObject.translateMessage("generic.dropparty.receivekey"));
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
        mainAPI.bossbar.put(player.getUniqueId(), bossbar);
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
        mainAPI.scoreboard.put(player.getUniqueId(), fakeScoreboard);
    }

    public void spawnWither(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("generic.wither.form.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("generic.wither.form.message.normal")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("generic.wither.form.message.cost")));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            if (isOnSpawn(player)) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.wither.spawn.error"));
                                return;
                            }
                            if (Wither.count > 10) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.wither.spawn.count"));
                                return;
                            } else {
                                ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                                double dolllars = profileSkyblock.getDollars();
                                if (dolllars < 10000) {
                                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.money.enough", mainAPI.emptyNoSpace + (10000 - profileSkyblock.getDollars())));
                                    return;
                                } else {
                                    mainAPI.getServer().dispatchCommand(new ConsoleCommandSender(), "mob spawn 52 " + player.getName());
                                    profileSkyblock.setDollars(profileSkyblock.getDollars() - 10000);
                                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.wither.spawn.success"));
                                    return;
                                }
                            }
                    }
                }
            }
        }));
    }

    public void teleportArena(Player player) {
        FormWindowSimple jobsMenu = new FormWindowSimple(messageAPI.messagesObject.translateMessage("generic.arena.form.category"),
                messageAPI.messagesObject.translateMessage("generic.arena.first"));
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
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("arena.form.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.top")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.teleport")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.pvp")));
        player.showFormWindow(infoMenu);
    }

    private void teleportArenaBoss(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("arena.form.boss.teleport"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.boss.teleport.first")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.boss.teleport.how")));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                    int level = profileSkyblock.getLevel();
                    if (level < 10) {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("arena.teleport.level", "10"));
                        return;
                    } else {
                        player.teleport(new Location(1092, 70, 1324, mainAPI.getServer().getDefaultLevel()));
                        player.sendMessage(messageAPI.messagesObject.translateMessage("arena.teleported"));
                        return;
                    }
                }
            }
        }));
    }

    private void teleportArenaPvP(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("arena.form.pvp.teleport"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.pvp.first")));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("arena.form.pvp.how")));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                    int level = profileSkyblock.getLevel();
                    if (level < 5) {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("arena.teleport.level", "5"));
                        return;
                    } else {
                        player.teleport(new Location(1106, 70, 1311, mainAPI.getServer().getDefaultLevel()));
                        player.sendMessage(messageAPI.messagesObject.translateMessage("arena.teleported"));
                        return;
                    }
                }
            }
        }));
    }

    public void sendSettingsForm(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom(messageAPI.messagesObject.translateMessage("settings.form.first"));
        infoMenu.addElement(new ElementLabel(messageAPI.messagesObject.translateMessage("settings.form.top")));
        infoMenu.addElement(new ElementToggle(messageAPI.messagesObject.translateMessage("settings.form.toggle.first"), true));
        infoMenu.addElement(new ElementToggle(messageAPI.messagesObject.translateMessage("settings.form.toggle.second"), true));
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        infoMenu.addElement(new ElementStepSlider(messageAPI.messagesObject.translateMessage("settings.form.toggle.third"), list));
        player.showFormWindow(new ResponseFormWindow(infoMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    SettingsObject settings = mainAPI.settings.get(player.getUniqueId());
                    if (settings != null) {
                        settings.setBossbarValue(Boolean.valueOf(String.valueOf(response.values().toArray()[1])));
                        settings.setScoreboardValue(Boolean.valueOf(String.valueOf(response.values().toArray()[2])));
                        player.setViewDistance(Integer.valueOf(String.valueOf(response.values().toArray()[3])));

                        DummyBossBar bb = mainAPI.bossbar.get(player.getUniqueId());
                        if (bb != null) {
                            bb.destroy();
                        }
                        if (Boolean.valueOf(String.valueOf(response.values().toArray()[1]))) {
                            createBossBar(player);
                        } else {
                            mainAPI.bossbar.remove(player.getUniqueId());
                        }

                        FakeScoreboard fsc = mainAPI.scoreboard.get(player.getUniqueId());
                        if (fsc != null) {
                            fsc.removePlayer(player);
                        }
                        if (Boolean.valueOf(String.valueOf(response.values().toArray()[2]))) {
                            createScoreboard(player);
                        } else {
                            mainAPI.scoreboard.remove(player.getUniqueId());
                        }
                    }
                }
            }
        }));
    }
}
