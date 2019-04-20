package nycuro.mechanic.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.Database;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class MechanicHandlers implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Nu merge PreLoginEvent si nici Async.
        API.getMainAPI().coords.put(player.getName(), false);
        API.getDatabase().playerExist(player, bool -> {
            if (!bool) {
                API.getDatabase().addNewPlayer(player);
            } else {
                Database.addDatesPlayerHub(player);
                Database.addDatesPlayerFactions(player);
            }
        });
        if (Loader.startTime.get(player.getUniqueId()) > 0) {
            Loader.startTime.replace(player.getUniqueId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getUniqueId(), System.currentTimeMillis());
        }

        if (Loader.startTime.get(player.getUniqueId()) != null) {
            Loader.startTime.replace(player.getUniqueId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getUniqueId(), System.currentTimeMillis());
        }

        API.getMainAPI().getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                String username = player.getName();
                Integer playerTime = API.getMainAPI().timers.getOrDefault(username, 1);
                switch (playerTime) {
                    case 1:
                        API.getMessageAPI().sendFirstJoinTitle(player);
                        break;
                    case 2:
                        API.getMessageAPI().sendSecondJoinTitle(player);
                        break;
                    case 3:
                        API.getMessageAPI().sendThreeJoinTitle(player);
                        break;
                    default:
                        API.getMainAPI().getServer().getScheduler().cancelTask(this.getTaskId());
                }
                API.getMainAPI().timers.put(username, playerTime + 1);
            }
        }, 20 * 7, 20 * 3, true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Loader.startTime.remove(player.getUniqueId());
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (message.equalsIgnoreCase("జ్ఞ\u200Cా")) {
            API.getMessageAPI().sendAbuseMessage(event.getPlayer());
            event.setCancelled(true);
        }
    }
}
