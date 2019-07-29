package nycuro;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.*;
import nycuro.abuse.handlers.AbuseHandlers;
import nycuro.ai.AiAPI;
import nycuro.api.API;
import nycuro.api.data.MechanicAPI;
import nycuro.chat.api.MessageAPI;
import nycuro.chat.handlers.ChatHandlers;
import nycuro.combat.api.CombatAPI;
import nycuro.commands.list.boss.SpawnBossCommand;
import nycuro.commands.list.economy.AddCoinsCommand;
import nycuro.commands.list.economy.GetCoinsCommand;
import nycuro.commands.list.economy.SetCoinsCommand;
import nycuro.commands.list.homes.HomesCommand;
import nycuro.commands.list.jobs.JobCommand;
import nycuro.commands.list.kits.KitsCommand;
import nycuro.commands.list.mechanic.DropPartyMessageCommand;
import nycuro.commands.list.mechanic.player.*;
import nycuro.commands.list.mechanic.tops.TopCoinsCommand;
import nycuro.commands.list.mechanic.tops.TopDeathsCommand;
import nycuro.commands.list.mechanic.tops.TopKillsCommand;
import nycuro.commands.list.mechanic.tops.TopTimeCommand;
import nycuro.commands.list.reports.ReportsCommand;
import nycuro.commands.list.shop.ShopCommand;
import nycuro.commands.list.spawning.ArenaCommand;
import nycuro.commands.list.spawning.WitherCommand;
import nycuro.commands.list.stats.StatsCommand;
import nycuro.commands.list.time.GetTimeCommand;
import nycuro.commands.list.utils.UtilsCommand;
import nycuro.crate.CrateAPI;
import nycuro.crate.handlers.CrateHandlers;
import nycuro.database.Database;
import nycuro.database.objects.KitsObject;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.dropparty.api.DropPartyAPI;
import nycuro.gui.handlers.GUIHandlers;
import nycuro.home.api.HomeAPI;
import nycuro.jobs.api.JobsAPI;
import nycuro.jobs.handlers.JobsHandlers;
import nycuro.kits.api.KitsAPI;
import nycuro.kits.handlers.KitHandlers;
import nycuro.level.handlers.LevelHandlers;
import nycuro.mechanic.handlers.MechanicHandlers;
import nycuro.mechanic.objects.SettingsObject;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.protection.handlers.ProtectionHandlers;
import nycuro.reports.api.ReportAPI;
import nycuro.shop.BuyUtils;
import nycuro.shop.EnchantUtils;
import nycuro.shop.MoneyUtils;
import nycuro.shop.SellUtils;
import nycuro.shop.api.ShopAPI;
import nycuro.tasks.*;
import nycuro.utils.WarpUtils;
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class Loader extends PluginBase {

    public static Object2LongMap<UUID> startTime = new Object2LongOpenHashMap<>();
    public Object2ObjectMap<UUID, DummyBossBar> bossbar = new Object2ObjectOpenHashMap<>();
    public Object2ObjectMap<UUID, FakeScoreboard> scoreboard = new Object2ObjectOpenHashMap<>();
    public Object2IntMap<UUID> timers = new Object2IntOpenHashMap<>();
    public Object2BooleanMap<UUID> coords = new Object2BooleanOpenHashMap<>();
    public Object2LongMap<UUID> played = new Object2LongOpenHashMap<>();
    public Object2BooleanMap<UUID> isOnSpawn = new Object2BooleanOpenHashMap<>();
    public Object2BooleanMap<UUID> isOnArena = new Object2BooleanOpenHashMap<>();
    public Map<UUID, SettingsObject> settings = new HashMap<>();
    public Collection<UUID> staffChat = new ArrayList<>();

    public static long dropPartyTime;
    public static int dropPartyVotes;

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
        API.getMechanicAPI().spawnNPC("mobfarmerNPC", EntityCreeper.NETWORK_ID, 14, 172, -11);
        API.getMechanicAPI().spawnNPC("minerNPC", EntityCreeper.NETWORK_ID, 51, 168, -10);
        API.getMechanicAPI().spawnNPC("butcherNPC", EntityCreeper.NETWORK_ID, 51, 168, 10);
        API.getMechanicAPI().spawnNPC("farmerNPC", EntityCreeper.NETWORK_ID, 42, 168, 15);
        API.getMechanicAPI().spawnNPC("fishermanNPC", EntityCreeper.NETWORK_ID, 42, 168, -12);
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
        API.getMechanicAPI().handleTransferHub(this.getServer().getOnlinePlayers().values().iterator().next(), 1);
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
        for (ProfileSkyblock profileSkyblock : Database.profileSkyblock.values()) {
            Database.saveUnAsyncDatesPlayerFromFactions(profileSkyblock.getName());
        }
        for (KitsObject kitsObject : Database.kitsSkyblock.values()) {
            Database.saveUnAsyncDatesPlayerFromKits(kitsObject.getName());
        }
    }

    private void removeAllFromMaps() {
        startTime.clear();
        played.clear();
    }

    private void initDatabase() {
        log("Init MySQL Database...");
        Database.connectToDatabaseHub();
        Database.connectToDatabaseFactions();
        Database.connectToDatabaseReports();
        Database.connectToDatabaseHomesF();
        Database.connectToDatabaseSKits();
    }

    private void registerAPI() {
        API.mainAPI = this;
        API.mechanicAPI = new MechanicAPI();
        API.utilsAPI = new UtilsAPI();
        UtilsAPI.warpUtils = new WarpUtils();
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
        API.reportAPI = new ReportAPI();
        API.homeAPI = new HomeAPI();
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
        this.getServer().getCommandMap().register("kit", new KitsCommand());
        this.getServer().getCommandMap().register("homes", new HomesCommand());
        this.getServer().getCommandMap().register("shop", new ShopCommand());
        this.getServer().getCommandMap().register("spawn", new SpawnCommand());
        this.getServer().getCommandMap().register("utils", new UtilsCommand());
        this.getServer().getCommandMap().register("lang", new LangCommand());
        this.getServer().getCommandMap().register("stats", new StatsCommand());
        this.getServer().getCommandMap().register("jobs", new JobCommand());
        this.getServer().getCommandMap().register("arena", new ArenaCommand());
        this.getServer().getCommandMap().register("reports", new ReportsCommand());
        this.getServer().getCommandMap().register("coords", new CoordsCommand());// TODO: Save to Database
        this.getServer().getCommandMap().register("settings", new SettingsCommand());
        this.getServer().getCommandMap().register("hub", new HubCommand());
        this.getServer().getCommandMap().register("staffchat", new StaffChatCommand());
        this.getServer().getCommandMap().register("helpop", new HelpOpCommand());
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AbuseHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new GUIHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MessageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new KitHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LevelHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MechanicHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ProtectionHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new JobsHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new CrateHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ChatHandlers(), this);
    }

    private void registerTasks() {
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(new RegisterTopsTask(), 20 * 10, 20 * 60 * 3); // ASYNC: FUCKING EVITATE GC SPAMMING CONSOLE
        this.getServer().getScheduler().scheduleRepeatingTask(new ClearLagTask(), 20 * 60 * 5, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckLevelTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CombatLoggerTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreTagTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckerTask(), 20, true);
        this.getServer().getScheduler().scheduleDelayedTask(new RestartTask(), 20 * 60 * 60 * 3);
        this.getServer().getScheduler().scheduleRepeatingTask(new FixBugHealthTask(), 1, true); // Todo: Bullshit incompetent Nukkit Codders -- Using resources useless.
    }

    private void removeNPC() {
        for (Entity entity : API.getMainAPI().getServer().getDefaultLevel().getEntities()) {
            if (entity.namedTag.getBoolean("mobfarmerNPC")) entity.close();
            if (entity.namedTag.getBoolean("minerNPC")) entity.close();
            if (entity.namedTag.getBoolean("butcherNPC")) entity.close();
            if (entity.namedTag.getBoolean("farmerNPC")) entity.close();
            if (entity.namedTag.getBoolean("fishermanNPC")) entity.close();
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
        }
    }
}