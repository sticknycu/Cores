package nycuro.mechanic.handlers;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.scheduler.Task;
import gt.creeperface.holograms.Holograms;
import io.pocketvote.event.VoteEvent;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class MechanicHandlers implements Listener {

    @EventHandler
    public void onInitialized(DataPacketReceiveEvent event) {
        DataPacket dataPacket = event.getPacket();
        if (dataPacket instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            API.getMechanicAPI().createBossBar(player);
            API.getMechanicAPI().createScoreboard(player);
            player.getInventory().clearAll();
            startItems(player);
            API.getMainAPI().getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    String username = player.getName();
                    int playerTime = API.getMainAPI().timers.getOrDefault(username, 1);
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
                        case 4:
                            API.getMessageAPI().sendLanguageMessage(player);
                            break;
                        default:
                            API.getMainAPI().getServer().getScheduler().cancelTask(this.getTaskId());
                    }
                    API.getMainAPI().timers.put(username, playerTime + 1);
                }
            }, 20, 20 * 3, true);

            /* test reason why nullpointer lang */
            int lang = Database.profileProxy.get(player.getName()).getLanguage();
            Holograms holograms = new Holograms();
            switch (lang) {
                case 0:
                    Holograms.setLanguageHandler( (p) -> 0);
                    break;
                case 1:
                    Holograms.setLanguageHandler( (p) -> 1);
                    break;
            }
            holograms.onLanguageChanged(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(PlayerPreLoginEvent event) {
        event.getPlayer().setCheckMovement(false);
    }

    @EventHandler
    public void onChangeItemInventory(InventoryTransactionEvent event) {
        for (InventoryAction action : event.getTransaction().getActions()) {
            if (action instanceof SlotChangeAction) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setKeepInventory(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clearAll();
        API.getMainAPI().coords.put(player.getName(), false);
        API.getDatabase().playerExist(player.getName(), bool -> {
            if (!bool) {
                API.getDatabase().addNewPlayer(player.getName());
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

        if (Loader.startTime.getLong(player.getUniqueId()) > 0) {
            Loader.startTime.replace(player.getUniqueId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getUniqueId(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Loader.startTime.removeLong(player.getUniqueId());
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (message.equalsIgnoreCase("జ్ఞ\u200Cా")) {
            API.getMessageAPI().sendAbuseMessage(event.getPlayer());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onVoteReceive(VoteEvent event) {
        try {
            IPlayer player = API.getMainAPI().getServer().getOfflinePlayer(event.getPlayer());
            ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
            int votes = profileProxy.getVotes();
            profileProxy.setVotes(votes + 1);
            API.getDatabase().setVotes(player.getName(), votes + 1);
        } catch (Exception e) {
            //
        }
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
