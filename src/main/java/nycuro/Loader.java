package nycuro;

import cn.nukkit.Player;
import cn.nukkit.command.ConsoleCommandSender;
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
import nycuro.commands.list.jobs.JobCommand;
import nycuro.commands.list.mechanic.TopCoinsCommand;
import nycuro.commands.list.mechanic.TopDeathsCommand;
import nycuro.commands.list.mechanic.TopKillsCommand;
import nycuro.commands.list.mechanic.TopTimeCommand;
import nycuro.commands.list.spawning.ArenaCommand;
import nycuro.commands.list.spawning.WitherCommand;
import nycuro.commands.list.stats.StatsCommand;
import nycuro.commands.list.time.GetTimeCommand;
import nycuro.crate.CrateAPI;
import nycuro.crate.handlers.CrateHandlers;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
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
import nycuro.utils.vote.VoteSettings;

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

    public static long dropPartyTime;
    public static int dropPartyVotes;

    public static Object2ObjectMap<Integer, String> scoreboardPowerName = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<Integer, Double> scoreboardPowerValue = new Object2ObjectOpenHashMap<>();

    public static Object2BooleanMap<String> isOnSpawn = new Object2BooleanOpenHashMap<>();
    public static Object2BooleanMap<String> isOnBorder = new Object2BooleanOpenHashMap<>();

    public static void log(String s) {
        API.getMainAPI().getServer().getLogger().info(TextFormat.colorize("&a" + s));
    }

    public static void registerTops() {
        try {
            API.getMainAPI().saveToDatabase();
        } finally {
            Database.getTopDollars();
            Database.getTopKills();
            Database.getTopDeaths();
            Database.getTopTime();
        }
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
        createConfig();
        initDatabase();
        registerEvents();
        registerTasks();
        addEntities();
        registerPlaceHolders();
    }

    @Override
    public void onDisable() {
        saveToDatabase();
        removeNPC();
        removeAllFromMaps();
        saveDropParty();
    }

    private void createConfig() {
        API.getVoteSettingsAPI().init();
        if (API.getVoteSettingsAPI().mechanic.getTimeDropParty() == 0) {
            dropPartyTime = System.currentTimeMillis();
        } else {
            dropPartyTime = API.getVoteSettingsAPI().mechanic.getTimeDropParty();
        }
        dropPartyVotes = API.getVoteSettingsAPI().mechanic.getDropParty();
    }

    private void saveDropParty() {
        API.getVoteSettingsAPI().saveConfig(dropPartyVotes, dropPartyTime);
    }

    private void saveToDatabase() {
        for (ProfileFactions profileFactions : Database.profileFactions.values()) {
            Database.saveUnAsyncDatesPlayerFromFactions(profileFactions.getName());
        }
    }

    private void removeAllFromMaps() {
        startTime.clear();
        played.clear();
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
        API.voteSettingsAPI = new VoteSettings();
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
        this.getServer().getCommandMap().register("wither", new WitherCommand());
        this.getServer().getCommandMap().register("droppartymessage", new DropPartyMessageCommand());
        this.getServer().getCommandMap().register("spawnboss", new SpawnBossCommand());
        this.getServer().getCommandMap().register("kit", new KitCommand());
        this.getServer().getCommandMap().register("kits", new KitsCommand());
        this.getServer().getCommandMap().register("shop", new ShopCommand());
        this.getServer().getCommandMap().register("spawn", new SpawnCommand());
        this.getServer().getCommandMap().register("utils", new UtilsCommand());
        this.getServer().getCommandMap().register("lang", new LangCommand());
        this.getServer().getCommandMap().register("stats", new StatsCommand());
        this.getServer().getCommandMap().register("jobs", new JobCommand());
        this.getServer().getCommandMap().register("arena", new ArenaCommand());
        this.getServer().getCommandMap().register("coords", new CoordsCommand());// TODO: Save to Database
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AbuseHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new GUIHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MessageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new KitHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LanguageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LevelHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MechanicHandlers(), this);
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
                registerTops();
                updatePlaceholders();
            }
        }, 20 * 10, 20 * 60 * 3, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                String message = "";
                try {
                    for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                        message = API.getMessageAPI().sendMobDespawnMessage(player);
                        player.sendMessage(message);
                        try {
                            Thread.sleep(1000 * 30);
                        } finally {
                            for (Level level : API.getMainAPI().getServer().getLevels().values()) {
                                for (Entity entity : level.getEntities()) {
                                    switch (entity.getNetworkId()) {
                                        case 10:
                                        case 11:
                                        case 12:
                                        case 13:
                                        case 52:
                                        case 69:
                                        case 89:
                                            entity.close();
                                            break;
                                    }
                                    if (entity instanceof EntityItem) entity.close();
                                }
                            }
                            message = API.getMessageAPI().sendMobDespawnFinishMessage(player);
                            player.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    //
                }
            }
        }, 20 * 60 * 5, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckLevelTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CombatLoggerTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreTagTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckerTask(), 10, true);
        this.getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "stop");
            }
        }, 20 * 60 * 60 * 3, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                    player.setHealth(player.getHealth());
                }
            }
        }, 1, true);
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

    public void updatePlaceholders() {
        PlaceholderAPI api = PlaceholderAPI.Companion.getInstance();
        for (int i = 1; i <= 10; i++) {
            api.updatePlaceholder("top" + i + "killsname");
            api.updatePlaceholder("top" + i + "killscount");

            api.updatePlaceholder("top" + i + "deathsname");
            api.updatePlaceholder("top" + i + "deathscount");

            api.updatePlaceholder("top" + i + "coinsname");
            api.updatePlaceholder("top" + i + "coinscount");

            api.updatePlaceholder("top" + i + "timename");
            api.updatePlaceholder("top" + i + "timecount");

            api.updatePlaceholder("top" + i + "powername");
            api.updatePlaceholder("top" + i + "powercount");
        }
    }

    private void registerPlaceHolders() {
        PlaceholderAPI api = PlaceholderAPI.Companion.getInstance();
        for (int i = 1; i <= 10; i++) {
            final int value = i;
            api.staticPlaceholder("top" + value + "killsname", () -> Database.scoreboardkillsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "killscount", () -> String.valueOf(Database.scoreboardkillsValue.getOrDefault(value, 0)));

            api.staticPlaceholder("top" + value + "deathsname", () -> Database.scoreboarddeathsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "deathscount", () -> String.valueOf(Database.scoreboarddeathsValue.getOrDefault(value, 0)));

            api.staticPlaceholder("top" + value + "coinsname", () -> Database.scoreboardcoinsName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "coinscount", () -> String.valueOf(round(Database.scoreboardcoinsValue.getOrDefault(value, 0.0), 2)));

            api.staticPlaceholder("top" + value + "timename", () -> Database.scoreboardtimeName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(value, 0L)));

            api.staticPlaceholder("top" + value + "powername", () -> Loader.scoreboardPowerName.getOrDefault(1, " "));
            api.staticPlaceholder("top" + value + "powercount", () -> String.valueOf(round(Loader.scoreboardPowerValue.getOrDefault(1, 0.0), 2)));
        }
    }
}