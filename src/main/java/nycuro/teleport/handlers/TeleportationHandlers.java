package nycuro.teleport.handlers;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import nycuro.teleport.api.TeleportationAPI;

public class TeleportationHandlers implements Listener {

    private final TeleportationAPI api;

    public TeleportationHandlers(TeleportationAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        api.removeTPRequest(event.getPlayer());
    }
}
