package nycuro.mechanic.handlers;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
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
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.Task;
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

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.databaseAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MechanicHandlers implements Listener {

    @EventHandler
    public void onInitialized(DataPacketReceiveEvent event) {
        DataPacket dataPacket = event.getPacket();
        if (dataPacket instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            mainAPI.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    int playerTime = mainAPI.timers.getOrDefault(player.getUniqueId(), 1);
                    switch (playerTime) {
                        case 1:
                            messageAPI.sendFirstJoinTitle(player);
                            break;
                        case 2:
                            messageAPI.sendSecondJoinTitle(player);
                            break;
                        case 3:
                            messageAPI.sendThreeJoinTitle(player);
                            break;
                        case 4:
                            if (player.hasPermission("core.reports")) {
                                messageAPI.sendReportsTitle(player, databaseAPI.getCountOfAllPlayersReport());
                            }
                            break;
                        default:
                            mainAPI.getServer().getScheduler().cancelTask(this.getTaskId());
                            mainAPI.timers.removeInt(player.getUniqueId());
                    }
                    mainAPI.timers.put(player.getUniqueId(), playerTime + 1);
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
        mainAPI.settings.put(player.getUniqueId(), new SettingsObject(true, true, 5));
        // Nu merge PreLoginEvent si nici Async.
        mainAPI.coords.put(player.getUniqueId(), false);
        mainAPI.isOnSpawn.put(player.getUniqueId(), true);
        mainAPI.isOnArena.put(player.getUniqueId(), false);
        mainAPI.isOnArea.put(player.getUniqueId(), false);
        mainAPI.mechanicObject.put(player.getUniqueId(), new MechanicObject(player.getUniqueId(), new HashMap<>(), new HashMap<>()));
        mainAPI.played.put(player.getUniqueId(), System.currentTimeMillis());
        // Async?!
        mainAPI.getServer().getScheduler().scheduleAsyncTask(mainAPI, new AsyncTask() {
            @Override
            public void onRun() {
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
            }
        });
        if (Loader.startTime.getLong(player.getUniqueId()) > 0) {
            Loader.startTime.replace(player.getUniqueId(), System.currentTimeMillis());
        } else {
            Loader.startTime.put(player.getUniqueId(), System.currentTimeMillis());
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
        Loader.startTime.removeLong(player.getUniqueId());
        mainAPI.played.removeLong(player.getUniqueId());
        mainAPI.isOnSpawn.removeBoolean(player.getUniqueId());
        mainAPI.isOnArena.removeBoolean(player.getUniqueId());
        mainAPI.isOnArea.removeBoolean(player.getUniqueId());
        mainAPI.settings.remove(player.getUniqueId());
        mainAPI.mechanicObject.remove(player.getUniqueId());
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
            MechanicObject mechanicObject = mainAPI.mechanicObject.get(damager.getUniqueId());

            mechanicObject.getKills().put(player.getUniqueId(), mechanicObject.getKills().getOrDefault(player.getUniqueId(), 0) + 1);

            int kp = mechanicObject.getKills().getOrDefault(player.getUniqueId(), 0);

            long lp = mechanicObject.getPlayers().getOrDefault(player.getUniqueId(), 0L);

            if (kp == 5) {
                mechanicObject.getPlayers().put(player.getUniqueId(), System.currentTimeMillis());
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

    private static int i = 80;
    /* optimise tnt */
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block block = event.getBlock();
        Item item = event.getItem();
        if (block instanceof BlockTNT && event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && item instanceof ItemFlintSteel) {
            long key = Level.blockHash((int) block.getX(), (int) block.getY(), (int) block.getZ());
            Long2ObjectMap<Block> map = new Long2ObjectOpenHashMap<>();
            map.put(key, block);
            searchForTNT(map, (BlockTNT) block);
            for (Long2ObjectMap.Entry<Block> it : map.long2ObjectEntrySet()) {
                ((BlockTNT) it.getValue()).prime(i);
                i = i + 5;
            }
            event.setCancelled();
        }
    }

    private void searchForTNT(Long2ObjectMap<Block> tnt, BlockTNT current) {
        for (BlockFace blockFace : BlockFace.values()) {
            Block side = current.getSide(blockFace);
            long hash = Level.blockHash((int) side.getX(), (int) side.getY(), (int) side.getZ());
            if (side instanceof BlockTNT && !tnt.containsKey(hash)) {
                tnt.put(hash, side);
                searchForTNT(tnt, (BlockTNT) side);
            } else {
                i = 80;
            }
        }
    }
}