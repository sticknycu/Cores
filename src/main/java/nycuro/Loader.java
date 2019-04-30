package nycuro;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.*;
import nycuro.abuse.handlers.AbuseHandlers;
import nycuro.api.MechanicAPI;
import nycuro.api.MessageAPI;
import nycuro.chat.handlers.ChatHandlers;
import nycuro.commands.list.CoordsCommand;
import nycuro.commands.list.LangCommand;
import nycuro.commands.list.donate.gems.GemsCommand;
import nycuro.commands.list.donate.ranks.RankCommand;
import nycuro.commands.list.mechanic.*;
import nycuro.crate.CrateAPI;
import nycuro.crate.handlers.CrateHandlers;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.gui.handlers.GUIHandlers;
import nycuro.language.handlers.LanguageHandlers;
import nycuro.level.handlers.LevelHandlers;
import nycuro.mechanic.handlers.MechanicHandlers;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.protection.handlers.ProtectionHandlers;
import nycuro.tasks.BossBarTask;
import nycuro.tasks.CheckerTask;
import nycuro.tasks.ScoreboardTask;
import nycuro.utils.query.MCQuery;
import nycuro.utils.query.QueryResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class Loader extends PluginBase {

    public static Object2LongMap<UUID> startTime = new Object2LongOpenHashMap<>();
    public Map<String, DummyBossBar> bossbar = new Object2ObjectOpenHashMap<>();
    public Map<String, FakeScoreboard> scoreboard = new Object2ObjectOpenHashMap<>();
    public Object2IntMap<String> timers = new Object2IntOpenHashMap<>();
    public Object2BooleanMap<String> coords = new Object2BooleanOpenHashMap<>();

    public static Object2BooleanMap<String> isOnSpawn = new Object2BooleanOpenHashMap<>();

    public static void log(String s) {
        API.getMainAPI().getServer().getLogger().info(TextFormat.colorize("&a" + s));
    }

    public static void registerTops() {
        Database.getTopTime();
        Database.getTopVotes();
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
        registerPlaceHolders();
        registerEvents();
        initDatabase();
        registerTasks();
        registerTops();
    }

    @Override
    public void onDisable() {
        saveToDatabase();
        removeAllFromMaps();
    }

    private void removeAllFromMaps() {
        for (Long player : startTime.values()) {
            startTime.removeLong(player);
        }
    }

    private void saveToDatabase() {
        for (ProfileProxy profileProxy : Database.profileProxy.values()) {
            Database.saveUnAsyncDatesPlayerFromProxy(profileProxy.getName());
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
        API.messageAPI = new MessageAPI();
        API.crateAPI = new CrateAPI();
        API.database = new Database();
    }

    private void registerCommands() {
        //this.getServer().getCommandMap().register("onlinetime", new GetTimeCommand());
        this.getServer().getCommandMap().register("lang", new LangCommand());
        this.getServer().getCommandMap().register("coords", new CoordsCommand());
        this.getServer().getCommandMap().register("gems", new GemsCommand());
        this.getServer().getCommandMap().register("sf", new SpawnFireworkCommand());
        this.getServer().getCommandMap().register("ranks", new RankCommand());
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new AbuseHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new GUIHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LanguageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new LevelHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MechanicHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new MessageHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ProtectionHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new CrateHandlers(), this);
        this.getServer().getPluginManager().registerEvents(new ChatHandlers(), this);
    }

    private void registerTasks() {
        this.getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "stop");
            }
        }, 20 * 60 * 60 * 3, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 20, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new CheckerTask(), 10, true);
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

            api.staticPlaceholder("top" + value + "votesname", () -> Database.scoreboardvotesName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "votescount", () -> String.valueOf(Database.scoreboardvotesValue.getOrDefault(value, 0)));
        }
    }

    public static String getCountOnline(int type) {
        String count = "0";
        MCQuery main;
        QueryResponse response;
        switch (type) {
            case 0: // bungee
                main = new MCQuery("localhost", 19132);
                count = String.valueOf(main.basicStat().getOnlinePlayers());
                break;
            case 1: // hub
                main = new MCQuery("localhost", 19133);
                count = String.valueOf(main.basicStat().getOnlinePlayers());
                break;
            case 2: // factions
                main = new MCQuery("localhost", 19134);
                count = String.valueOf(main.basicStat().getOnlinePlayers());
                break;
            default:
                count = TextFormat.RED + "OFFLINE";
                break;
        }
        return count;
    }
}
