package nycuro;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.FakeScoreboard;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
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
import nycuro.gui.handlers.GUIHandlers;
import nycuro.language.handlers.LanguageHandlers;
import nycuro.level.handlers.LevelHandlers;
import nycuro.mechanic.handlers.MechanicHandlers;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.protection.handlers.ProtectionHandlers;
import nycuro.tasks.BossBarTask;
import nycuro.tasks.ScoreboardTask;
import nycuro.utils.query.MCQuery;
import nycuro.utils.query.QueryResponse;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class Loader extends PluginBase {

    public static Object2ObjectMap<UUID, Long> startTime = new Object2ObjectOpenHashMap<>();
    public Object2ObjectMap<String, DummyBossBar> bossbar = new Object2ObjectOpenHashMap<>();
    public Object2ObjectMap<String, FakeScoreboard> scoreboard = new Object2ObjectOpenHashMap<>();
    public Object2IntMap<String> timers = new Object2IntOpenHashMap<>();
    public Object2ObjectMap<String, Boolean> coords = new Object2ObjectOpenHashMap<>();

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
        return String.valueOf(hours + ":" + minutes + ":" + seconds);
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
        registerTops();
    }

    @Override
    public void onDisable() {
        removeAllFromMaps();
    }

    private void removeAllFromMaps() {
        for (Player player : this.getServer().getOnlinePlayers().values()) {
            Loader.startTime.remove(player.getUniqueId());
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
        /*this.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                MechanicUtils.getTops();
            }
        }, 20 * 10, 20 * 60 * 3, true);*/
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20 * 3, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 10, true);
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

            api.staticPlaceholder("top" + value + "votesname", () -> Database.scoreboardvotesName.getOrDefault(value, " "));
            api.staticPlaceholder("top" + value + "votescount", () -> Database.scoreboardvotesValue.getOrDefault(value, 0).toString());
        }
    }

    public static int getCountOnline(int type) {
        int count = 0;
        MCQuery main;
        QueryResponse response;
        switch (type) {
            case 0: // bungee
                main = new MCQuery("localhost", 19132);
                count = main.basicStat().getOnlinePlayers();
                break;
            case 1: // hub
                main = new MCQuery("localhost", 19133);
                count = main.basicStat().getOnlinePlayers();
                break;
            case 2: // factions
                main = new MCQuery("localhost", 19134);
                count = main.basicStat().getOnlinePlayers();
                break;
            case 3: // skyblock
                main = new MCQuery("localhost", 19135);
                count = main.basicStat().getOnlinePlayers();
                break;
            case 4: // skypvp
                main = new MCQuery("localhost", 19136);
                count = main.basicStat().getOnlinePlayers();
                break;
        }
        return count;
    }
}
