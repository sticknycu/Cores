package nycuro;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityTypes;
import cn.nukkit.player.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
//import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
//import gt.creeperface.holograms.entity.HologramEntity;
//import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.*;
import nycuro.abuse.handlers.AbuseHandlers;
import nycuro.abuse.settings.AbuseAPI;
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
import nycuro.tasks.*;
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

import java.util.*;

import static nycuro.api.API.*;


/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class Loader extends PluginBase {

    public static Object2LongMap<UUID> startTime = new Object2LongOpenHashMap<>();
    public static long dropPartyTime;
    public static int dropPartyVotes;
    public Object2ObjectMap<UUID, DummyBossBar> bossbar = new Object2ObjectOpenHashMap<>();
    //public Object2ObjectMap<UUID, FakeScoreboard> scoreboard = new Object2ObjectOpenHashMap<>();
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
    public String symbol = TextFormat.GOLD.toString();
    public String empty = " ";
    public String emptyNoSpace = "";

    public static void registerTops() {
        /*try {
            mainAPI.saveToDatabase();
        } finally {
            Database.getTopDollars();
            Database.getTopKills();
            Database.getTopDeaths();
            Database.getTopTime();
        }*/
    }

    private void addEntities() {
        mechanicAPI.spawnNPC("minerNPC", EntityTypes.CREEPER, -25, 164, -144, mainAPI.getServer().getDefaultLevel());
        mechanicAPI.spawnNPC("butcherNPC", EntityTypes.CREEPER, 6, 162, -185, mainAPI.getServer().getDefaultLevel());
        mechanicAPI.spawnNPC("farmerNPC", EntityTypes.CREEPER, 37, 162, -175, mainAPI.getServer().getDefaultLevel());
        mechanicAPI.spawnNPC("fishermanNPC", EntityTypes.CREEPER, 7, 162, -133, mainAPI.getServer().getDefaultLevel());
        mechanicAPI.spawnNPC("mechanicNPC", EntityTypes.CREEPER, -19, 162, -122, mainAPI.getServer().getDefaultLevel());
    }

    @Override
    public void onEnable() {
        registerAPI();
        abuseAPI.init();
        createConfig();
        registerCommands();
        //initDatabase();
        registerEvents();
        registerTasks();
        addEntities();
        kitsAPI.addKits();
        jobsAPI.addJobs();
        //registerPlaceHolders();
    }

    @Override
    public void onDisable() {
        mechanicAPI.handleTransferHub();
        //saveToDatabase();
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
        /*Database.connectToDatabaseHub();
        Database.connectToDatabaseFactions();
        Database.connectToDatabaseReports();
        Database.connectToDatabaseHomesF();
        Database.connectToDatabaseSKits();*/
    }

    private void registerAPI() {
        API.mainAPI = this;
        API.abuseAPI = new AbuseAPI();
        API.mechanicAPI = new MechanicAPI();
        API.utilsAPI = new UtilsAPI();
        API.kitsAPI = new KitsAPI();
        API.messageAPI = new MessageAPI();
        API.jobsAPI = new JobsAPI();
        API.aiAPI = new AiAPI();
        API.crateAPI = new CrateAPI();
        API.dropPartyAPI = new DropPartyAPI();
        API.combatAPI = new CombatAPI();
        API.databaseAPI = new Database();
        API.voteSettingsAPI = new VoteSettings();
        API.reportAPI = new ReportAPI();
        API.homeAPI = new HomeAPI();
        API.economyAPI = new EconomyAPI();
        API.helpingAPI = new HelpingAPI();
    }

    private void registerCommands() {
        dropPartyAPI.registerCommands();
        homeAPI.registerCommands();
        jobsAPI.registerCommands();
        kitsAPI.registerCommands();
        reportAPI.registerCommands();
        helpingAPI.registerCommands();
        economyAPI.registerCommands();
        utilsAPI.registerCommands();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AbuseHandlers(), this);
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
        this.getServer().getScheduler().scheduleRepeatingTask(new FixBugHealthTask(), 1, true); // Todo: Bullshit incompetent Nukkit Coders -- Using resources useless.
    }

    private void removeEntities() {
        for (Entity entity : mainAPI.getServer().getDefaultLevel().getEntities()) {
            if (!(entity instanceof Player) /*&& !(entity instanceof HologramEntity)*/) entity.close();
        }
    }

    /*private void registerPlaceHolders() {
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
    }*/
}