package nycuro.mechanic.handlers;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockTNT;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.LeavesDecayEvent;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemFlintSteel;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.player.IPlayer;
import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import com.nukkitx.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;
import io.pocketvote.event.VoteEvent;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.objects.MechanicObject;
import nycuro.mechanic.objects.SettingsObject;

import java.util.HashMap;
import java.util.Random;

import static nycuro.api.API.*;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class MechanicHandlers implements Listener {

    @EventHandler
    public void onInitialized(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            mainAPI.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    int playerTime = mainAPI.timers.getOrDefault(player.getServerId(), 1);
                    switch (playerTime) {
                        case 1:
                            player.sendTitle(messageAPI.messagesObject.translateMessage("title.join"),
                                    messageAPI.messagesObject.translateMessage("subtitle.join.first"), 20, 20, 20);
                            break;
                        case 2:
                            player.sendTitle(messageAPI.messagesObject.translateMessage("title.join"),
                                    messageAPI.messagesObject.translateMessage("subtitle.join.second"), 20, 20, 20);
                            break;
                        case 3:
                            player.sendTitle(messageAPI.messagesObject.translateMessage("title.join"),
                                    messageAPI.messagesObject.translateMessage("subtitle.join.third"), 20, 20, 20);
                            break;
                        default:
                            mainAPI.getServer().getScheduler().cancelTask(this.getTaskId());
                            mainAPI.timers.removeInt(player.getServerId());
                    }
                    mainAPI.timers.put(player.getServerId(), playerTime + 1);
                }
            }, 20, 20 * 3, true);
        }
    }

    @EventHandler
    public void onDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(PlayerPreLoginEvent event) {
        event.getPlayer().setCheckMovement(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        mainAPI.settings.put(player.getServerId(), new SettingsObject(true, true, 5));
        // Nu merge PreLoginEvent si nici Async.
        mainAPI.coords.put(player.getServerId(), false);
        mainAPI.isOnSpawn.put(player.getServerId(), true);
        mainAPI.isOnArena.put(player.getServerId(), false);
        mainAPI.isOnArea.put(player.getServerId(), false);
        mainAPI.mechanicObject.put(player.getServerId(), new MechanicObject(player.getServerId(), new HashMap<>(), new HashMap<>()));
        mainAPI.played.put(player.getServerId(), System.currentTimeMillis());
        databaseAPI.playerExist(player.getName(), bool -> {
            if (!bool) {
                databaseAPI.addNewPlayer(player.getName());
                Database.addDatesPlayerHub(player.getName());
            } else {
                Database.addDatesPlayerHub(player.getName());
                Database.addDatesPlayerFactions(player.getName());
            }
        });
        databaseAPI.playerKitsExist(player.getName(), bool -> {
            if (!bool) {
                databaseAPI.addNewPlayerToKits(player.getName());
            } else {
                Database.addDatesKitsPlayer(player.getName());
            }
        });
        if (Loader.startTime.getLong(player.getServerId()) > 0) {
            Loader.startTime.replace(player.getServerId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getServerId(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onVoteReceive(VoteEvent event) {
        Loader.dropPartyVotes++;
        IPlayer offlinePlayer = mainAPI.getServer().getOfflinePlayer(event.getPlayer());
        Random r = new Random();
        int low = 200;
        int high = 250;
        int result = r.nextInt(high-low) + low;
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(offlinePlayer.getName());
        profileSkyblock.setExperience(profileSkyblock.getExperience() + result);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Database.saveDatesPlayerFromHub(player.getName());
        Database.saveDatesPlayerFromFactions(player.getName());
        Database.saveDatesPlayerFromKits(player.getName());
        Loader.startTime.removeLong(player.getServerId());
        mainAPI.played.removeLong(player.getServerId());
        mainAPI.isOnSpawn.removeBoolean(player.getServerId());
        mainAPI.isOnArena.removeBoolean(player.getServerId());
        mainAPI.isOnArea.removeBoolean(player.getServerId());
        mainAPI.settings.remove(player.getServerId());
        mainAPI.mechanicObject.remove(player.getServerId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player == null) return;
        Player damager = null;
        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
            if (ev instanceof EntityDamageByChildEntityEvent) {
                EntityDamageByChildEntityEvent evc = (EntityDamageByChildEntityEvent) ev;
                if (evc.getDamager() instanceof Player) damager = (Player) evc.getDamager();
            } else if (ev.getDamager() instanceof Player) damager = (Player) ev.getDamager();

            if (damager == null) return;

            // Kills Checker
            MechanicObject mechanicObject = mainAPI.mechanicObject.get(damager.getServerId());

            mechanicObject.getKills().put(player.getServerId(), mechanicObject.getKills().getOrDefault(player.getServerId(), 0) + 1);

            int kp = mechanicObject.getKills().getOrDefault(player.getServerId(), 0);

            long lp = mechanicObject.getPlayers().getOrDefault(player.getServerId(), 0L);

            if (kp == 5) {
                mechanicObject.getPlayers().put(player.getServerId(), System.currentTimeMillis());
            }

            if (kp >= 5) {
                if (System.currentTimeMillis() - lp >= 1000 * 60 * 10) {
                    ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(damager.getName());
                    profileSkyblock.setDollars(profileSkyblock.getDollars() + 50d);
                    profileSkyblock.setExperience(profileSkyblock.getExperience() + 50d);
                }
            } else {
                ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(damager.getName());
                profileSkyblock.setDollars(profileSkyblock.getDollars() + 50d);
                profileSkyblock.setExperience(profileSkyblock.getExperience() + 50d);
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (message.equalsIgnoreCase("జ్ఞ\u200Cా")) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
            event.setCancelled(true);
        }
    }
}