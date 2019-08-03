package nycuro.jobs.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import cn.nukkit.scheduler.Task;
import nycuro.api.API;
import nycuro.database.DatabaseMySQL;
import nycuro.database.objects.ProfileSkyblock;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public class JobsHandlers implements Listener {

    /* First Time Playrs get a little mission on Miner.
        Added that because idk another way in database lmao (evitate 0 item in database)
     */
    @EventHandler
    public void onInitialized(DataPacketReceiveEvent event) {
        DataPacket dataPacket = event.getPacket();
        if (dataPacket instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            if (!player.hasPlayedBefore()) {
                API.getMainAPI().getServer().getScheduler().scheduleDelayedTask(new Task() {
                    @Override
                    public void onRun(int i) {

                        API.getMessageAPI().sendNewMissionTitle(player);
                        API.getMainAPI().getServer().getScheduler().cancelTask(this.getTaskId());
                    }
                }, 20 * 3 * 4 + 20, true);
            }
        }
    }

    @EventHandler
    public void onTouch(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity == null) return;
        Player damager = null;
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) event;
            if (ev instanceof EntityDamageByChildEntityEvent) {
                EntityDamageByChildEntityEvent evc = (EntityDamageByChildEntityEvent) ev;
                if (evc.getDamager() instanceof Player) damager = (Player) evc.getDamager();
            } else if (ev.getDamager() instanceof Player) damager = (Player) ev.getDamager();
            if (damager == null) return;

            ProfileSkyblock profileSkyblock = DatabaseMySQL.profileSkyblock.get(damager.getName());
            if (entity.namedTag.getBoolean("minerNPC")) {
                profileSkyblock.setJob(1);
            } else if (entity.namedTag.getBoolean("butcherNPC")) {
                profileSkyblock.setJob(2);
            } else if (entity.namedTag.getBoolean("farmerNPC")) {
                profileSkyblock.setJob(3);
            } else if (entity.namedTag.getBoolean("fishermanNPC")) {
                profileSkyblock.setJob(4);
            } else if (entity.namedTag.getBoolean("mobfarmerNPC")) {
                // TODO: Mob farm
            }
        }
    }

    /* Credits: @Nora. Thanks!
       FIX: When adding mobs */
    private void sendToRespawn(Entity entity, Player damager, EntityDamageEvent event) {
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        if (event.getDamage() > player.getHealth() && (damager != null)) {
            for (Item item : player.getInventory().getContents().values()) {
                player.getLevel().dropItem(new Vector3(player.getX(), player.getY() + 1, player.getZ()), item);
            }
            event.setCancelled();
            player.setHealth(20);
            player.getFoodData().setLevel(20);
            player.teleport(player.getServer().getDefaultLevel().getSpawnLocation());
            player.removeAllEffects();
            player.getInventory().clearAll();
            API.getMessageAPI().sendDeadMessage(player, damager);
            ProfileSkyblock profilePlayer = DatabaseMySQL.profileSkyblock.get(player.getName());
            ProfileSkyblock profileDamager = DatabaseMySQL.profileSkyblock.get(damager.getName()); // Todo: Zombies, Monsters.
            profilePlayer.setDeaths(profilePlayer.getDeaths() + 1);
            profileDamager.setKills(profileDamager.getKills() + 1);

        }
        if (player.getPosition().getY() < -3) {
            event.setCancelled();
            player.setHealth(20);
            player.getFoodData().setLevel(20);
            player.teleport(player.getServer().getDefaultLevel().getSpawnLocation());
            player.removeAllEffects();
            player.getInventory().clearAll();
            ProfileSkyblock profilePlayer = DatabaseMySQL.profileSkyblock.get(player.getName());
            profilePlayer.setDeaths(profilePlayer.getDeaths() + 1);
        }
    }
}
