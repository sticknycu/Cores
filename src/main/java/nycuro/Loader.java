package nycuro;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
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
import nycuro.tasks.SaveToDatabaseTask;
import nycuro.tasks.ScoreboardTask;
//import nycuro.utils.MechanicUtils;

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

    /*public static void registerTops() {
        Database.getTopCoins();
        Database.getTopKills();
        Database.getTopDeaths();
        Database.getTopTime();
    }*/

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
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelAllTasks();
        log("Cancelled All Tasks.");
    }

    private void initDatabase() {
        log("Init SQLite Database...");
        Database.connectToDatabase();
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
        this.getServer().getCommandMap().register("savetodatabase", new SaveToDatabaseCommand());
        this.getServer().getCommandMap().register("coords", new CoordsCommand());
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
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "savetodatabase");
            }
        }, 20 * 15, 20 * 60 * 5);
        this.getServer().getScheduler().scheduleRepeatingTask(new BossBarTask(), 20 * 5, true);
        this.getServer().getScheduler().scheduleRepeatingTask(new ScoreboardTask(), 10, true);
        this.getServer().getScheduler().scheduleDelayedTask(new SaveToDatabaseTask(), 20 * 60 * 60 * 3);
    }

    private void registerPlaceHolders() {
        PlaceholderAPI api = PlaceholderAPI.Companion.getInstance();
        api.staticPlaceholder("top1killsname", () -> Database.scoreboardkillsName.getOrDefault(1, " "));
        api.staticPlaceholder("top2killsname", () -> Database.scoreboardkillsName.getOrDefault(2, " "));
        api.staticPlaceholder("top3killsname", () -> Database.scoreboardkillsName.getOrDefault(3, " "));
        api.staticPlaceholder("top4killsname", () -> Database.scoreboardkillsName.getOrDefault(4, " "));
        api.staticPlaceholder("top5killsname", () -> Database.scoreboardkillsName.getOrDefault(5, " "));
        api.staticPlaceholder("top6killsname", () -> Database.scoreboardkillsName.getOrDefault(6, " "));
        api.staticPlaceholder("top7killsname", () -> Database.scoreboardkillsName.getOrDefault(7, " "));
        api.staticPlaceholder("top8killsname", () -> Database.scoreboardkillsName.getOrDefault(8, " "));
        api.staticPlaceholder("top9killsname", () -> Database.scoreboardkillsName.getOrDefault(9, " "));
        api.staticPlaceholder("top10killsname", () -> Database.scoreboardkillsName.getOrDefault(10, " "));

        api.staticPlaceholder("top1killscount", () -> Database.scoreboardkillsValue.getOrDefault(1, 0).toString());
        api.staticPlaceholder("top2killscount", () -> Database.scoreboardkillsValue.getOrDefault(2, 0).toString());
        api.staticPlaceholder("top3killscount", () -> Database.scoreboardkillsValue.getOrDefault(3, 0).toString());
        api.staticPlaceholder("top4killscount", () -> Database.scoreboardkillsValue.getOrDefault(4, 0).toString());
        api.staticPlaceholder("top5killscount", () -> Database.scoreboardkillsValue.getOrDefault(5, 0).toString());
        api.staticPlaceholder("top6killscount", () -> Database.scoreboardkillsValue.getOrDefault(6, 0).toString());
        api.staticPlaceholder("top7killscount", () -> Database.scoreboardkillsValue.getOrDefault(7, 0).toString());
        api.staticPlaceholder("top8killscount", () -> Database.scoreboardkillsValue.getOrDefault(8, 0).toString());
        api.staticPlaceholder("top9killscount", () -> Database.scoreboardkillsValue.getOrDefault(9, 0).toString());
        api.staticPlaceholder("top10killscount", () -> Database.scoreboardkillsValue.getOrDefault(10, 0).toString());

        api.staticPlaceholder("top1deathsname", () -> Database.scoreboarddeathsName.getOrDefault(1, " "));
        api.staticPlaceholder("top2deathsname", () -> Database.scoreboarddeathsName.getOrDefault(2, " "));
        api.staticPlaceholder("top3deathsname", () -> Database.scoreboarddeathsName.getOrDefault(3, " "));
        api.staticPlaceholder("top4deathsname", () -> Database.scoreboarddeathsName.getOrDefault(4, " "));
        api.staticPlaceholder("top5deathsname", () -> Database.scoreboarddeathsName.getOrDefault(5, " "));
        api.staticPlaceholder("top6deathsname", () -> Database.scoreboarddeathsName.getOrDefault(6, " "));
        api.staticPlaceholder("top7deathsname", () -> Database.scoreboarddeathsName.getOrDefault(7, " "));
        api.staticPlaceholder("top8deathsname", () -> Database.scoreboarddeathsName.getOrDefault(8, " "));
        api.staticPlaceholder("top9deathsname", () -> Database.scoreboarddeathsName.getOrDefault(9, " "));
        api.staticPlaceholder("top10deathsname", () -> Database.scoreboarddeathsName.getOrDefault(10, " "));

        api.staticPlaceholder("top1deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(1, 0).toString());
        api.staticPlaceholder("top2deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(2, 0).toString());
        api.staticPlaceholder("top3deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(3, 0).toString());
        api.staticPlaceholder("top4deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(4, 0).toString());
        api.staticPlaceholder("top5deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(5, 0).toString());
        api.staticPlaceholder("top6deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(6, 0).toString());
        api.staticPlaceholder("top7deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(7, 0).toString());
        api.staticPlaceholder("top8deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(8, 0).toString());
        api.staticPlaceholder("top9deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(9, 0).toString());
        api.staticPlaceholder("top10deathscount", () -> Database.scoreboarddeathsValue.getOrDefault(10, 0).toString());

        api.staticPlaceholder("top1coinsname", () -> Database.scoreboardcoinsName.getOrDefault(1, " "));
        api.staticPlaceholder("top2coinsname", () -> Database.scoreboardcoinsName.getOrDefault(2, " "));
        api.staticPlaceholder("top3coinsname", () -> Database.scoreboardcoinsName.getOrDefault(3, " "));
        api.staticPlaceholder("top4coinsname", () -> Database.scoreboardcoinsName.getOrDefault(4, " "));
        api.staticPlaceholder("top5coinsname", () -> Database.scoreboardcoinsName.getOrDefault(5, " "));
        api.staticPlaceholder("top6coinsname", () -> Database.scoreboardcoinsName.getOrDefault(6, " "));
        api.staticPlaceholder("top7coinsname", () -> Database.scoreboardcoinsName.getOrDefault(7, " "));
        api.staticPlaceholder("top8coinsname", () -> Database.scoreboardcoinsName.getOrDefault(8, " "));
        api.staticPlaceholder("top9coinsname", () -> Database.scoreboardcoinsName.getOrDefault(9, " "));
        api.staticPlaceholder("top10coinsname", () -> Database.scoreboardcoinsName.getOrDefault(10, " "));

        api.staticPlaceholder("top1coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(1, 0.0).toString());
        api.staticPlaceholder("top2coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(2, 0.0).toString());
        api.staticPlaceholder("top3coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(3, 0.0).toString());
        api.staticPlaceholder("top4coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(4, 0.0).toString());
        api.staticPlaceholder("top5coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(5, 0.0).toString());
        api.staticPlaceholder("top6coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(6, 0.0).toString());
        api.staticPlaceholder("top7coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(7, 0.0).toString());
        api.staticPlaceholder("top8coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(8, 0.0).toString());
        api.staticPlaceholder("top9coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(9, 0.0).toString());
        api.staticPlaceholder("top10coinscount", () -> Database.scoreboardcoinsValue.getOrDefault(10, 0.0).toString());

        api.staticPlaceholder("top1timename", () -> Database.scoreboardtimeName.getOrDefault(1, " "));
        api.staticPlaceholder("top2timename", () -> Database.scoreboardtimeName.getOrDefault(2, " "));
        api.staticPlaceholder("top3timename", () -> Database.scoreboardtimeName.getOrDefault(3, " "));
        api.staticPlaceholder("top4timename", () -> Database.scoreboardtimeName.getOrDefault(4, " "));
        api.staticPlaceholder("top5timename", () -> Database.scoreboardtimeName.getOrDefault(5, " "));
        api.staticPlaceholder("top6timename", () -> Database.scoreboardtimeName.getOrDefault(6, " "));
        api.staticPlaceholder("top7timename", () -> Database.scoreboardtimeName.getOrDefault(7, " "));
        api.staticPlaceholder("top8timename", () -> Database.scoreboardtimeName.getOrDefault(8, " "));
        api.staticPlaceholder("top9timename", () -> Database.scoreboardtimeName.getOrDefault(9, " "));
        api.staticPlaceholder("top10timename", () -> Database.scoreboardtimeName.getOrDefault(10, " "));

        api.staticPlaceholder("top1timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(1, (long) 0)));
        api.staticPlaceholder("top2timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(2, (long) 0)));
        api.staticPlaceholder("top3timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(3, (long) 0)));
        api.staticPlaceholder("top4timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(4, (long) 0)));
        api.staticPlaceholder("top5timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(5, (long) 0)));
        api.staticPlaceholder("top6timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(6, (long) 0)));
        api.staticPlaceholder("top7timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(7, (long) 0)));
        api.staticPlaceholder("top8timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(8, (long) 0)));
        api.staticPlaceholder("top9timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(9, (long) 0)));
        api.staticPlaceholder("top10timecount", () -> time(Database.scoreboardtimeValue.getOrDefault(10, (long) 0)));

        //api.visitorSensitivePlaceholder("time_player", (p) -> Database.profile.get(p.getUniqueId()).getTime());
    }
}
