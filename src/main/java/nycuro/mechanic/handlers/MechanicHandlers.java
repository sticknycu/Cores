package nycuro.mechanic.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.api.UtilsAPI;
import nycuro.database.Database;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class MechanicHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Nu merge PreLoginEvent si nici Async.
        API.getMainAPI().coords.put(player.getName(), false);
        API.getMainAPI().played.put(player.getName(), System.currentTimeMillis());
        API.getDatabase().playerExist(player.getName(), bool -> {
            if (!bool) {
                API.getDatabase().addNewPlayer(player.getName());
                Database.addDatesPlayerHub(player.getName());
            } else {
                Database.addDatesPlayerHub(player.getName());
                Database.addDatesPlayerFactions(player.getName());
            }
        });
        if (Loader.startTime.getLong(player.getUniqueId()) > 0) {
            Loader.startTime.replace(player.getUniqueId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getUniqueId(), System.currentTimeMillis());
        }
        API.getMainAPI().isOnMobFarm.putIfAbsent(player, false);

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
        }, 20, 20 * 3, true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (API.getMechanicAPI().isOnSpawn(player)) {
            Location location = player.getLocation();
            if (location.getLevelBlock().getId() == BlockID.NETHER_PORTAL) {
                if (UtilsAPI.teleported) return;
                API.getUtilsAPI().handleRandomTeleport(player);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Database.saveDatesPlayerFromHub(player.getName());
        Database.saveDatesPlayerFromFactions(player.getName());
        Loader.startTime.removeLong(player.getUniqueId());
        API.getMainAPI().played.removeLong(player.getName());
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
