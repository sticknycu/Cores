package nycuro.mechanic.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
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

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        enterThings(player);
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        enterThings(player);
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

    private void enterThings(Player player) {
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
        if (Loader.startTime.get(player.getUniqueId()) != null) {
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
                        startItems(player);
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

    private void startItems(Player player) {
        Item COMPASS = Item.get(Item.COMPASS);
        COMPASS.setCustomName(API.getMessageAPI().getCompassMessage(player));
        Item DYE = Item.get(Item.DYE, 8, 1);
        DYE.setCustomName(API.getMessageAPI().getDyeStageOneMessage(player));
        Item FEATHER = Item.get(Item.FEATHER);
        FEATHER.setCustomName(API.getMessageAPI().getFeatherMessage(player));
        Item NETHER_STAR = Item.get(Item.NETHER_STAR);
        NETHER_STAR.setCustomName(API.getMessageAPI().getNetherStarMessage(player));
        Item BOW = Item.get(Item.BOW);
        BOW.setCustomName(API.getMessageAPI().getBowMessage(player));
        Item ARROW = Item.get(Item.ARROW, 0 , 64);
        PlayerInventory playerInventory = player.getInventory();
        playerInventory.setItem(0, DYE);
        playerInventory.setItem(2, FEATHER);
        playerInventory.setItem(4, COMPASS);
        playerInventory.setItem(6, BOW);
        playerInventory.setItem(8, NETHER_STAR);
        playerInventory.setItem(9, ARROW);
    }

}
