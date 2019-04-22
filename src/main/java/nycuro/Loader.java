package nycuro;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.level.Level;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.*;
import nycuro.abuse.handlers.AbuseHandlers;
import nycuro.ai.AiAPI;
import nycuro.api.*;
import nycuro.chat.handlers.ChatHandlers;
import nycuro.commands.list.*;
import nycuro.commands.list.economy.AddCoinsCommand;
import nycuro.commands.list.economy.GetCoinsCommand;
import nycuro.commands.list.economy.SetCoinsCommand;
import nycuro.commands.list.mechanic.TopCoinsCommand;
import nycuro.commands.list.mechanic.TopDeathsCommand;
import nycuro.commands.list.mechanic.TopKillsCommand;
import nycuro.commands.list.mechanic.TopTimeCommand;
import nycuro.commands.list.stats.StatsCommand;
import nycuro.commands.list.time.GetTimeCommand;
import nycuro.crate.CrateAPI;
import nycuro.crate.handlers.CrateHandlers;
import nycuro.database.Database;
import nycuro.dropparty.DropPartyAPI;
import nycuro.gui.handlers.GUIHandlers;
import nycuro.jobs.handlers.JobsHandlers;
import nycuro.kits.handlers.KitHandlers;
import nycuro.language.handlers.LanguageHandlers;
import nycuro.level.handlers.LevelHandlers;
import nycuro.mechanic.handlers.MechanicHandlers;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.protection.handlers.ProtectionHandlers;
import nycuro.shop.BuyUtils;
import nycuro.shop.EnchantUtils;
import nycuro.shop.MoneyUtils;
import nycuro.shop.SellUtils;
import nycuro.tasks.*;
import nycuro.utils.MechanicUtils;
import nycuro.utils.RandomTPUtils;
import nycuro.utils.WarpUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class Loader extends PluginBase {

    public static Object2LongMap<UUID> startTime = new Object2LongOpenHashMap<>();
    public Object2ObjectMap<String, DummyBossBar> bossbar = new Object2ObjectOpenHashMap<>();
    public Object2ObjectMap<String, FakeScoreboard> scoreboard = new Object2ObjectOpenHashMap<>();
    public Object2IntMap<String> timers = new Object2IntOpenHashMap<>();
    public Object2BooleanMap<String> coords = new Object2BooleanOpenHashMap<>();
    public Object2LongMap<String> played = new Object2LongOpenHashMap<>();
    public Object2BooleanMap<Player> isOnMobFarm = new Object2BooleanOpenHashMap<>();

    public static Object2ObjectMap<Integer, String> scoreboardPowerName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Double> scoreboardPowerValue = new Object2ObjectOpenHashMap<>();

    public static void log(String s) {
        API.getMainAPI().getServer().getLogger().info(TextFormat.colorize("&a" + s));
    }

    public static void registerTops() {
        Database.getTopDollars();
        Database.getTopKills();
        Database.getTopDeaths();
        Database.getTopTime();
    }

    private void addEntities() {
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", 1131 + 0.5))
                        .add(new DoubleTag("", 69))
                        .add(new DoubleTag("", 1270 + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) 0))
                        .add(new FloatTag("", (float) 0)))
                .putBoolean("Invulnerable", true)
                .putString("NameTag", "Happy NPC")
                .putList(new ListTag<StringTag>("Commands"))
                .putList(new ListTag<StringTag>("PlayerCommands"))
                .putBoolean("npc", true)
                .putFloat("scale", 1);
        Entity entity = Entity.createEntity(EntityCreeper.NETWORK_ID, this.getServer().getDefaultLevel().getChunk(1131 >> 4, 1270 >> 4), nbt);
        entity.spawnToAll();
    }

    public static String time(long time) {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(time);
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(time) - hours * 60);
        int MINS = (int) TimeUnit.MILLISECONDS.toMinutes(time);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time) - MINS * 60);
        return hours + ":" + minutes + ":" + seconds;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onLoad() {
        registerAPI();
        registerCommands();
    }

    @Override
    public void onEnable() {
        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));
        registerPlaceHolders();
        registerEvents();
        initDatabase();
        registerTasks();
        addEntities();
    }

    @Override
    public void onDisable() {
        saveToDatabase();
        removeAllFromMaps();
        removeNPC();
    }

    private void saveToDatabase() {
        for (Player player : this.getServer().getOnlinePlayers().values()) {
            Database.saveUnAsyncDatesPlayerFromHub(player);
            Database.saveUnAsyncDatesPlayerFromFactions(player);
        }
    }

    private void removeAllFromMaps() {
        for (Player player : this.getServer().getOnlinePlayers().values()) {
            Loader.startTime.removeLong(player.getUniqueId());
            API.getMainAPI().played.removeLong(player.getName());
        }
    }

    private void initDatabase() {
        log("Init SQLite Database...");
        Database.connectToDatabaseHub();
        Database.connectToDatabaseFactions();
    }

    private void registerAPI() {
        API.mainAPI = this;
        API.mechanicAPI = new MechanicAPI();
        API.utilsAPI = new UtilsAPI();
        UtilsAPI.randomTPUtils = new RandomTPUtils();
        UtilsAPI.warpUtils = new WarpUtils();
        UtilsAPI.mechanicUtils = new MechanicUtils();
        API.kitsAPI = new KitsAPI();
        API.messageAPI = new MessageAPI();
        API.shopAPI = new ShopAPI();
        API.jobsAPI = new JobsAPI();
        ShopAPI.buyUtils = new BuyUtils();
        ShopAPI.sellUtils = new SellUtils();
        ShopAPI.moneyUtils = new MoneyUtils();
        API.aiAPI = new AiAPI();
        API.crateAPI = new CrateAPI();
        API.dropPartyAPI = new DropPartyAPI();
        API.combatAPI = new CombatAPI();
        ShopAPI.enchantUtils = new EnchantUtils();
        API.database = new Database();
        API.slotsAPI = new SlotsAPI();
    }

    private void registerCommands() {
        this.getServer().getCommandMap().register("setcoins", new SetCoinsCommand());
        this.getServer().getCommandMap().register("addcoins", new AddCoinsCommand());
        this.getServer().getCommandMap().register("onlinetime", new GetTimeCommand());
        this.getServer().getCommandMap().register("coins", new GetCoinsCommand());
        this.getServer().getCommandMap().register("topcoins", new TopCoinsCommand());
        this.getServer().getCommandMap().register("topkills", new TopKillsCommand());
        this.getServer().getCommandMap().register("toptime", new TopTimeCommand());
        this.getServer().getCommandMap().register("topdeaths", new TopDeathsCommand());
        //this.getServer().getCommandMap().register("spawnentities", new SpawnEntitiesCommand());
        this.getServer().getCommandMap().register("servers", new ServersCommand());
        this.getServer().getCommandMap().register("droppartymessage", new DropPartyMessageCommand());
        this.getServer().getCommandMap().register("spawnboss", new SpawnBossCommand());
        this.getServer().getCommandMap().register("kit", new KitCommand());
        this.getServer().getCommandMap().register("kits", new KitsCommand());
        this.getServer().getCommandMap().register("shop", new ShopCommand());
        this.getServer().getCommandMap().register("spawn", new SpawnCommand());
        this.getServer().getCommandMap().register("utils", new UtilsCommand());
        this.getServer().getCommandMap().register("lang", new LangCommand());
        this.getServer().getCommandMap().register("stats", new StatsCommand());
        this.getServer().getCommandMap().register("coords", new CoordsCommand());// TODO: Save to Database
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AbuseHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new GUIHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new KitHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LanguageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LevelHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MechanicHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MessageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ProtectionHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new JobsHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new CrateHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ChatHandlers(), this);
    }

    private void registerTasks() {
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                MechanicUtils.getTops();
            }
        }, 20 * 10, 20 * 60 * 3, true);
        /*this.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "spawnentities");
                for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                    LuckPermsApi api = LuckPerms.getApi();
                    NodeFactory NODE_BUILDER = api.getNodeFactory();
                    if (player.getName().equals(Database.scoreboardtimeName.getOrDefault(1, " "))) {
                        api.getUser(player.getUniqueId()).setPrimaryGroup("HELPERJR");
                        System.out.println("Am gasit TOP1 TIME: " + player.getName());
                    } else {
                        if (!player.getName().equals("NycuR0")) {
                            api.getUser(player.getUniqueId()).setPrimaryGroup("DEFAULT");
                        }
                    }
                    if (player.getName().equals(Database.scoreboardkillsName.getOrDefault(1, " "))) {
                        api.getUser(player.getUniqueId()).setPermission(NODE_BUILDER.newBuilder("core.7").build());
                        System.out.println("Am gasit TOP1 kills: " + player.getName());
                    } else if (player.getName().equals(Database.scoreboardkillsName.getOrDefault(2, " "))) {
                        api.getUser(player.getUniqueId()).setPermission(NODE_BUILDER.newBuilder("core.3").build());
                        System.out.println("Am gasit TOP2 kills: " + player.getName());
                    } else if (player.getName().equals(Database.scoreboardkillsName.getOrDefault(3, " "))) {
                        api.getUser(player.getUniqueId()).setPermission(NODE_BUILDER.newBuilder("core.2").build());
                        System.out.println("Am gasit TOP3 kills: " + player.getName());
                    } else {
                        if (!player.getName().equals("NycuR0")) {
                            for (Node permissions : api.getUser(player.getUniqueId()).getPermissions()) {
                                if (permissions.equals(NODE_BUILDER.newBuilder("core.2").build())) {
                                    api.getUser(player.getUniqueId()).unsetPermission(NODE_BUILDER.newBuilder("core.2").build());
                                }
                                if (permissions.equals(NODE_BUILDER.newBuilder("core.3").build())) {
                                    api.getUser(player.getUniqueId()).unsetPermission(NODE_BUILDER.newBuilder("core.3").build());
                                }
                                if (permissions.equals(NODE_BUILDER.newBuilder("core.7").build())) {
                                    api.getUser(player.getUniqueId()).unsetPermission(NODE_BUILDER.newBuilder("core.7").build());
                                }
                            }
                        }
                    }
                }
            }
        }, 20 * 15, 20 * 60 * 5);*/
        this.getServer().getScheduler().scheduleRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                for (Level level : API.getMainAPI().getServer().getLevels().values()) {
                    for (Entity entity : level.getEntities()) {
                        switch (entity.getNetworkId()) {
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 69:
                                entity.close();
                                break;
                        }
                        if (entity instanceof EntityItem) entity.close();
                    }
                    API.getMechanicAPI().spawnEntities();
                }
            }
        }, 20 * 60 * 3, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckLevelTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CombatLoggerTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreTagTask(), 20, true);
    }

    private void removeNPC() {
        for (Level level : API.getMainAPI().getServer().getLevels().values()) {
            for (Entity entity : level.getEntities()) {
                if (entity.namedTag.getBoolean("npc")) {
                    entity.close();
                }
            }
        }
    }

    private void registerPlaceHolders() {
        PlaceholderAPI api = PlaceholderAPI.Companion.getInstance();
        for (int i = 1; i <= 10; i++) {
            final int value = i;
            api.staticPlaceholder("top" + value + "killsname", () -> Database.scoreboardkillsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "killscount", () -> Database.scoreboardkillsValue.getOrDefault(value, 0).toString());

            api.staticPlaceholder("top" + value + "deathsname", () -> Database.scoreboarddeathsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(value, 0).toString());

            api.staticPlaceholder("top" + value + "coinsname", () -> Database.scoreboardcoinsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "coinscount", () -> String.valueOf(round(Database.scoreboardcoinsValue.getOrDefault(value, 0.0), 2)));

            api.staticPlaceholder("top" + value + "timename", () -> Database.scoreboardtimeName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(value, 0L)));

            api.staticPlaceholder("top" + value + "powername", () -> Loader.scoreboardPowerName.getOrDefault(1, " "));
            api.staticPlaceholder("top" + value + "powercount", () -> String.valueOf(round(Loader.scoreboardPowerValue.getOrDefault(1, 0.0), 2)));
        }

        api.visitorSensitivePlaceholder("time_player", (p) -> Database.profileFactions.get(p.getUniqueId()).getTime());
    }
}