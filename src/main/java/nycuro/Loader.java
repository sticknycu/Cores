package nycuro;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import gt.creeperface.holograms.entity.HologramEntity;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.*;
import nycuro.abuse.handlers.AbuseHandlers;
import nycuro.ai.AiAPI;
import nycuro.api.API;
import nycuro.api.data.MechanicAPI;
import nycuro.combat.api.CombatAPI;
import nycuro.crates.api.CrateAPI;
import nycuro.crates.handlers.CrateHandlers;
import nycuro.database.Database;
import nycuro.database.objects.KitsObject;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.dropparty.api.DropPartyAPI;
import nycuro.economy.api.EconomyAPI;
import nycuro.gui.handlers.GUIHandlers;
import nycuro.helping.api.HelpingAPI;
import nycuro.homes.api.HomeAPI;
import nycuro.jobs.api.JobsAPI;
import nycuro.jobs.handlers.JobsHandlers;
import nycuro.jobs.objects.JobsObject;
import nycuro.jobs.objects.MechanicObject;
import nycuro.kits.api.KitsAPI;
import nycuro.kits.handlers.KitHandlers;
import nycuro.level.handlers.LevelHandlers;
import nycuro.mechanic.handlers.MechanicHandlers;
import nycuro.mechanic.objects.SettingsObject;
import nycuro.messages.api.MessageAPI;
import nycuro.messages.handlers.ChatHandlers;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.protection.handlers.ProtectionHandlers;
import nycuro.reports.api.ReportAPI;
import nycuro.shop.BuyUtils;
import nycuro.shop.EnchantUtils;
import nycuro.shop.MoneyUtils;
import nycuro.shop.SellUtils;
import nycuro.shop.api.ShopAPI;
import nycuro.tasks.*;
import nycuro.teleport.api.TeleportationAPI;
import nycuro.utils.WarpUtils;
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

import java.util.*;

import static nycuro.api.API.*;


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
    public Object2BooleanMap<UUID> isOnArea = new Object2BooleanOpenHashMap<>();
    public Map<UUID, SettingsObject> settings = new HashMap<>();
    public Collection<UUID> staffChat = new ArrayList<>();
    public Map<UUID, JobsObject> jobsObject = new HashMap<>();
    public Map<UUID, MechanicObject> mechanicObject = new HashMap<>();

    public static long dropPartyTime;
    public static int dropPartyVotes;

    public String symbol = TextFormat.GOLD.toString();
    public String empty = " ";

    public static void registerTops() {
        try {
            mainAPI.saveToDatabase();
        } finally {
            Database.getTopDollars();
            Database.getTopKills();
            Database.getTopDeaths();
            Database.getTopTime();
        }
    }

    private void addEntities() {
        mechanicAPI.spawnNPC("mobfarmerNPC", EntityCreeper.NETWORK_ID, 14, 172, -11);
        mechanicAPI.spawnNPC("minerNPC", EntityCreeper.NETWORK_ID, 51, 168, -10);
        mechanicAPI.spawnNPC("butcherNPC", EntityCreeper.NETWORK_ID, 51, 168, 10);
        mechanicAPI.spawnNPC("farmerNPC", EntityCreeper.NETWORK_ID, 42, 168, 15);
        mechanicAPI.spawnNPC("fishermanNPC", EntityCreeper.NETWORK_ID, 42, 168, -12);
    }

    @Override
    public void onLoad() {
        registerAPI();
    }

    @Override
    public void onEnable() {
        createConfig();
        registerCommands();
        initDatabase();
        registerEvents();
        registerTasks();
        addEntities();
        registerPlaceHolders();
        kitsAPI.addKits();
        jobsAPI.addJobs();
    }

    @Override
    public void onDisable() {
        mechanicAPI.handleTransferHub();
        saveToDatabase();
        removeEntities();
        removeAllFromMaps();
        saveConfigs();
    }

    private void createConfig() {
        voteSettingsAPI.init();
        if (voteSettingsAPI.mechanic.getTimeDropParty() == 0) {
            dropPartyTime = System.currentTimeMillis();
        } else {
            dropPartyTime = voteSettingsAPI.mechanic.getTimeDropParty();
        }
        dropPartyVotes = voteSettingsAPI.mechanic.getDropParty();

        messageAPI.init();
    }

    private void saveConfigs() {
        voteSettingsAPI.saveConfig(dropPartyVotes, dropPartyTime);
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
        API.log("Init MySQL Database...");
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
        API.jobsAPI = new JobsAPI();
        API.aiAPI = new AiAPI();
        API.crateAPI = new CrateAPI();
        API.dropPartyAPI = new DropPartyAPI();
        API.combatAPI = new CombatAPI();
        API.shopAPI = new ShopAPI();
        ShopAPI.buyUtils = new BuyUtils();
        ShopAPI.sellUtils = new SellUtils();
        ShopAPI.moneyUtils = new MoneyUtils();
        ShopAPI.enchantUtils = new EnchantUtils();
        API.databaseAPI = new Database();
        API.voteSettingsAPI = new VoteSettings();
        API.reportAPI = new ReportAPI();
        API.homeAPI = new HomeAPI();
        API.teleportationAPI = new TeleportationAPI();
        API.economyAPI = new EconomyAPI();
        API.helpingAPI = new HelpingAPI();
    }

    private void registerCommands() {
        aiAPI.registerCommands();
        dropPartyAPI.registerCommands();
        homeAPI.registerCommands();
        jobsAPI.registerCommands();
        kitsAPI.registerCommands();
        reportAPI.registerCommands();
        shopAPI.registerCommands();
        helpingAPI.registerCommands();
        economyAPI.registerCommands();
        crateAPI.registerCommands();
        utilsAPI.registerCommands();
        teleportationAPI.registerCommands();
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
        teleportationAPI.registerHandlers();
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
        this.getServer().getScheduler().scheduleRepeatingTask(new FixBugHealthTask(), 1, true); // Todo: Bullshit incompetent Nukkit Coders -- Using resources useless.
        teleportationAPI.registerTasks();
    }

    private void removeEntities() {
        for (Entity entity : mainAPI.getServer().getDefaultLevel().getEntities()) {
            if (!(entity instanceof Player) && !(entity instanceof HologramEntity)) entity.close();
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
            api.staticPlaceholder("top" + value + "coinscount", () -> String.valueOf(API.round(Database.scoreboardcoinsValue.getOrDefault(value, 0.0), 2)));

            api.staticPlaceholder("top" + value + "timename", () -> Database.scoreboardtimeName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "timecount", () -> API.time(Database.scoreboardtimeValue.getOrDefault(value, 0L)));
        }
    }
}