package nycuro.messages.handlers;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import nycuro.API;
import nycuro.database.Database;

/**
 * author: NycuRO
 * ProxyCore Project
 * API 1.0.0
 */
public class MessageHandlers implements Listener {

    @EventHandler
    public void onLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        API.getDatabase().playerExist(player, bool -> {
            if (!bool) {
                API.getDatabase().addNewPlayer(player);
            } else {
                Database.addDatesPlayerProxy(player);
            }
        });
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        Database.saveUnAsyncDatesPlayerFromHub(player);
    }
}
