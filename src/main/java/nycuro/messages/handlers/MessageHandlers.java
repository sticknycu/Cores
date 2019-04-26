package nycuro.messages.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.scheduler.Task;
import nycuro.API;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class MessageHandlers implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        API.getMainAPI().getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMechanicAPI().createBossBar(player);
                API.getMainAPI().getServer().getScheduler().cancelTask(this.getTaskId());
            }
        }, 20 * 10, true);
        API.getMechanicAPI().createScoreboard(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setDeathMessage("");
    }
}
