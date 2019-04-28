package nycuro;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import nycuro.commands.list.donate.gems.GemsCommand;
import nycuro.database.Database;
import nycuro.messages.handlers.MessageHandlers;
import nycuro.tasks.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * author: NycuRO
 * ProxyCore Project
 * API 1.0.0
 */
public class Loader extends Plugin {

    public static String time(long time) {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(time);
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(time) - hours * 60);
        int MINS = (int) TimeUnit.MILLISECONDS.toMinutes(time);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time) - MINS * 60);
        return String.valueOf(hours + ":" + minutes + ":" + seconds);
    }

    @Override
    public void onLoad() {
        registerAPI();
        registerCommands();
    }

    @Override
    public void onEnable() {
        initDatabase();
        registerHandlers();
        registerTasks();
    }

    @EventHandler
    public void onDisable() {
        saveDates();
    }

    private void saveDates() {
        if (this.getProxy().getOnlineCount() == 0) return;
        for (ProxiedPlayer player : API.getMainAPI().getProxy().getPlayers()) {
            Database.saveUnAsyncDatesPlayerFromHub(player);
        }
    }

    private void initDatabase() {
        this.getProxy().getLogger().info(ChatColor.GREEN + "Init SQLite Database...");
        Database.connectToDatabaseHub();
    }

    private void registerAPI() {
        API.mainAPI = this;
        API.database = new Database();
    }

    private void registerHandlers() {
        this.getProxy().getPluginManager().registerListener(this, new MessageHandlers());
    }

    private void registerCommands() {
        this.getProxy().getPluginManager().registerCommand(this, new GemsCommand());
    }

    private void registerTasks() {
        this.getProxy().getScheduler().schedule(this, new TimerTask(), 1, 1, TimeUnit.SECONDS);
        this.getProxy().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                for (ProxiedPlayer player : API.getMainAPI().getProxy().getPlayers()) {
                    Database.saveDatesPlayerFromHub(player);
                }
            }
        }, 1, 5, TimeUnit.MINUTES);
    }
}
