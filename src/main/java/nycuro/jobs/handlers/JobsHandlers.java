package nycuro.jobs.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.NameJob;
import nycuro.jobs.objects.JobsObject;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public class JobsHandlers implements Listener {

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

            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(damager.getName());
            if (entity.namedTag.getBoolean("minerNPC")) {
                if (API.getMainAPI().jobsObject.get(damager.getUniqueId()) != null) API.getMainAPI().jobsObject.remove(damager.getUniqueId());
                profileSkyblock.setJob(1);
                API.getMessageAPI().sendReceiveJobMessage(damager, NameJob.MINER);
            } else if (entity.namedTag.getBoolean("butcherNPC")) {
                if (API.getMainAPI().jobsObject.get(damager.getUniqueId()) != null) API.getMainAPI().jobsObject.remove(damager.getUniqueId());
                profileSkyblock.setJob(2);
                API.getMessageAPI().sendReceiveJobMessage(damager, NameJob.BUTCHER);
            } else if (entity.namedTag.getBoolean("farmerNPC")) {
                if (API.getMainAPI().jobsObject.get(damager.getUniqueId()) != null) API.getMainAPI().jobsObject.remove(damager.getUniqueId());
                profileSkyblock.setJob(3);
                API.getMessageAPI().sendReceiveJobMessage(damager, NameJob.FARMER);
            } else if (entity.namedTag.getBoolean("fishermanNPC")) {
                if (API.getMainAPI().jobsObject.get(damager.getUniqueId()) != null) API.getMainAPI().jobsObject.remove(damager.getUniqueId());
                profileSkyblock.setJob(4);
                API.getMessageAPI().sendReceiveJobMessage(damager, NameJob.FISHERMAN);
            } else if (entity.namedTag.getBoolean("mobfarmerNPC")) {
                // TODO: Mob farm
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        // Get Money
        JobsObject jobsObject = API.getMainAPI().jobsObject.get(player.getUniqueId());
        if (jobsObject == null) return;
        if (API.getMechanicAPI().isOnPrincipalWorld(player)) {
            event.setCancelled(true);
            // Job Miner
            switch (event.getBlock().getId()) {
                case Block.COBBLESTONE:
                case Block.IRON_ORE:
                case Block.GOLD_ORE:
                case Block.DIAMOND_ORE:
                case Block.COAL_ORE:
                case Block.EMERALD_ORE:
                case Block.REDSTONE_ORE:
                    if (profileSkyblock.getJob() == 1) {
                        event.setDrops(new Item[0]);
                        if (API.getMechanicAPI().checkItems(player, event.getDrops())) {
                            player.sendMessage(API.getMessageAPI().sendFinishedMissionMessage(player));
                            event.setCancelled();
                        }
                    }
                    return;
                // Job Farmer
                case 59: // wheat seeds block
                case 141: // carrots seeds block
                case 142: // potatoes seeds block
                case Block.RED_FLOWER:
                case Block.DOUBLE_PLANT:
                case Block.HAY_BALE:
                    if (profileSkyblock.getJob() == 3) {
                        event.setDrops(new Item[0]);
                        if (API.getMechanicAPI().checkItems(player, event.getDrops())) {
                            player.sendMessage(API.getMessageAPI().sendFinishedMissionMessage(player));
                            event.setCancelled();
                        }
                    }
                    return;
            }
            API.getMessageAPI().sendBreakMessage(player);
        }
    }

    // Job Fisherman
    @EventHandler
    public void onReceiveFish(InventoryPickupItemEvent event) {
        if (event.getItem().getItem().getId() != Item.RAW_FISH) event.setCancelled();
        if (event.getItem().getItem().getId() != Item.RAW_SALMON) event.setCancelled();
        if (event.getInventory() instanceof PlayerInventory) event.setCancelled();
        PlayerInventory playerInventory = (PlayerInventory) event.getInventory();
        playerInventory.getViewers().forEach( (player) -> {
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            if (API.getMechanicAPI().isOnPrincipalWorld(player)) {
                if (profileSkyblock.getJob() == 4) {
                    event.getItem().kill();
                    if (API.getMechanicAPI().checkItems(player, event.getItem().getItem())) {
                        player.sendMessage(API.getMessageAPI().sendFinishedMissionMessage(player));
                        event.setCancelled();
                    }
                }
            }
        });
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity eventEntity = event.getEntity();
        if (!(((EntityDamageByEntityEvent) eventEntity.getLastDamageCause()).getDamager() instanceof Player)) event.setCancelled();
        Player killer = (Player) ((EntityDamageByEntityEvent) eventEntity.getLastDamageCause()).getDamager();
        if (API.getMechanicAPI().isOnArea(killer)) event.setCancelled();
        ProfileSkyblock profile = Database.profileSkyblock.get(killer.getName());
        JobsObject jobsObject = API.getMainAPI().jobsObject.get(killer.getUniqueId());
        int job = profile.getJob();
        if (job == 2) {
            int[] integers = jobsObject.getCountAnimals();
            if (integers[0] == 0 && integers[1] == 0 && integers[2] == 0 && integers[3] == 0) {
                killer.sendMessage(API.getMessageAPI().sendFinishedMissionMessage(killer));
                event.setCancelled();
            }
            switch (eventEntity.getNetworkId()) {
                case 10: // chicken
                    if (integers[3] != 0) {
                        integers[3] = integers[3] - 1;
                    }
                    break;
                case 11: // cow
                    if (integers[0] != 0) {
                        integers[0] = integers[0] - 1;
                    }
                    break;
                case 12: // pig
                    if (integers[1] != 0) {
                        integers[1] = integers[1] - 1;
                    }
                    break;
                case 13: // sheep
                    if (integers[2] != 0) {
                        integers[2] = integers[2] - 1;
                    }
                    break;

            }
            jobsObject.setCountAnimals(integers);
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
            ProfileSkyblock profilePlayer = Database.profileSkyblock.get(player.getName());
            ProfileSkyblock profileDamager = Database.profileSkyblock.get(damager.getName()); // Todo: Zombies, Monsters.
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
            ProfileSkyblock profilePlayer = Database.profileSkyblock.get(player.getName());
            profilePlayer.setDeaths(profilePlayer.getDeaths() + 1);
        }
    }
}
