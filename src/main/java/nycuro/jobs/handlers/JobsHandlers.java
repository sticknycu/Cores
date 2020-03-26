package nycuro.jobs.handlers;


import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.inventory.InventoryOpenEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerItemConsumeEvent;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemIds;
import cn.nukkit.player.Player;
import com.nukkitx.math.vector.Vector3i;
import com.nukkitx.protocol.bedrock.data.EntityData;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.NameJob;
import nycuro.jobs.objects.JobsObject;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;

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
            if (entity.getData().getBoolean(EntityData.valueOf("minerNPC"))) {
                if (mainAPI.jobsObject.get(damager.getServerId()) != null) mainAPI.jobsObject.remove(damager.getServerId());
                profileSkyblock.setJob(1);
                damager.sendMessage(messageAPI.messagesObject.translateMessage("jobs.selected", NameJob.MINER.getName()));
                event.setCancelled();
            } else if (entity.getData().getBoolean(EntityData.valueOf("butcherNPC"))) {
                if (mainAPI.jobsObject.get(damager.getServerId()) != null) mainAPI.jobsObject.remove(damager.getServerId());
                profileSkyblock.setJob(2);
                damager.sendMessage(messageAPI.messagesObject.translateMessage("jobs.selected", NameJob.BUTCHER.getName()));
                event.setCancelled();
            } else if (entity.getData().getBoolean(EntityData.valueOf("farmerNPC"))) {
                if (mainAPI.jobsObject.get(damager.getServerId()) != null) mainAPI.jobsObject.remove(damager.getServerId());
                profileSkyblock.setJob(3);
                damager.sendMessage(messageAPI.messagesObject.translateMessage("jobs.selected", NameJob.FARMER.getName()));
                event.setCancelled();
            } else if (entity.getData().getBoolean(EntityData.valueOf("fishermanNPC"))) {
                if (mainAPI.jobsObject.get(damager.getServerId()) != null) mainAPI.jobsObject.remove(damager.getServerId());
                profileSkyblock.setJob(4);
                damager.sendMessage(messageAPI.messagesObject.translateMessage("jobs.selected", NameJob.FISHERMAN.getName()));
                event.setCancelled();
            } else if (entity.getData().getBoolean(EntityData.valueOf("mechanicNPC"))) {
                mainAPI.getServer().dispatchCommand(damager, "job");
                event.setCancelled();
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        // Get Money
        JobsObject jobsObject = mainAPI.jobsObject.get(player.getServerId());
        if (jobsObject == null || jobsObject.getItems() == null) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("work.unstarted.break"));
            event.setCancelled();
            return;
        }
        if (mechanicAPI.isOnArea(player)) {
            // Job Miner
            PlayerInventory playerInventory = player.getInventory();
            /*switch (event.getBlock().getId()) {
                case Block.COBBLESTONE:
                case Block.IRON_ORE:
                case Block.GOLD_ORE:
                case Block.DIAMOND_ORE:
                case Block.COAL_ORE:
                case Block.EMERALD_ORE:
                case Block.REDSTONE_ORE:
                    if (profileSkyblock.getJob() == 1) {
                        Item[] items = event.getDrops();
                        event.setDrops(new Item[0]);
                        boolean bool = false;
                        for (Item i : jobsObject.getItems()) {
                            for (Item id : items) {
                                if (i.getId() == id.getId()) {
                                    if (mechanicAPI.checkItem(player, i)) {
                                        bool = true;
                                    }
                                }
                            }
                        }
                        if (bool) {
                            player.sendMessage(messageAPI.messagesObject.translateMessage("work.finished.item"));
                            event.setCancelled();
                        } else {
                            for (Item item : items) {
                                item.setCustomName("JOB");
                                if (!playerInventory.canAddItem(item)) {
                                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                                } else {
                                    playerInventory.addItem(item);
                                }
                            }
                            event.setCancelled();
                        }
                    } else {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("work.unstarted.break"));
                        event.setCancelled();
                    }
                    break;
                // Job Farmer
                case 59: // wheat seeds block
                case 141: // carrots seeds block
                case 142: // potatoes seeds block
                case Block.RED_FLOWER:
                case Block.DOUBLE_PLANT:
                case Block.HAY_BALE:
                case Block.DIRT:
                case Block.REDSTONE_BLOCK:
                    if (profileSkyblock.getJob() == 3) {
                        Item[] items = event.getDrops();
                        event.setDrops(new Item[0]);
                        boolean bool = false;
                        for (Item i : jobsObject.getItems()) {
                            for (Item id : items) {
                                if (i.getId() == id.getId()) {
                                    if (mechanicAPI.checkItem(player, i)) {
                                        bool = true;
                                    }
                                }
                            }
                        }
                        if (bool) {
                            player.sendMessage(messageAPI.messagesObject.translateMessage("work.finished.item"));
                            event.setCancelled();
                        } else {
                            for (Item item : items) {
                                item.setCustomName("JOB");
                                if (!playerInventory.canAddItem(item)) {
                                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                                } else {
                                    playerInventory.addItem(item);
                                }
                            }
                            event.setCancelled();
                        }
                    } else {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("work.unstarted.break"));
                        event.setCancelled();
                    }
                    break;
                default:
                    if (!player.isOp()) {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("block.break"));
                        event.setCancelled();
                    }
                    break;
            }*/
        }
    }

    // Job Fisherman
    @EventHandler
    public void onReceiveFish(InventoryPickupItemEvent event) {
        if (event.getItem().getItem().getId() == ItemIds.FISH ||
                event.getItem().getItem().getId() == ItemIds.SALMON ||
                event.getItem().getItem().getId() == ItemIds.PUFFERFISH
                ) {
            if (!(event.getInventory() instanceof PlayerInventory)) return;
            PlayerInventory playerInventory = (PlayerInventory) event.getInventory();
            Item item = event.getItem().getItem();
            for (Player player : event.getViewers()) {
                ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
                JobsObject jobsObject = mainAPI.jobsObject.get(player.getServerId());
                if (jobsObject == null || jobsObject.getItems() == null) {
                    player.sendMessage(messageAPI.messagesObject.translateMessage("work.unstarted.break"));
                    event.setCancelled();
                    return;
                }
                if (mechanicAPI.isOnArea(player)) {
                    if (profileSkyblock.getJob() == 4) {
                        boolean bool = false;
                        for (Item i : jobsObject.getItems()) {
                            if (i.getId() == event.getItem().getItem().getId()) {
                                if (mechanicAPI.checkItem(player, i)) {
                                    bool = true;
                                }
                            }
                        }
                        if (bool) {
                            player.sendMessage(messageAPI.messagesObject.translateMessage("work.finished.item"));
                            event.setCancelled();
                        } else {
                            item.setCustomName("JOB");
                            if (!playerInventory.canAddItem(item)) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                            } else {
                                event.getItem().close();
                                playerInventory.addItem(item);
                            }
                            event.setCancelled();
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity eventEntity = event.getEntity();
        if (eventEntity == null) return;
        Player killer = null;
        if (eventEntity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) eventEntity.getLastDamageCause();
            if (ev instanceof EntityDamageByChildEntityEvent) {
                EntityDamageByChildEntityEvent evc = (EntityDamageByChildEntityEvent) ev;
                if (evc.getDamager() instanceof Player) killer = (Player) evc.getDamager();
            } else if (ev.getDamager() instanceof Player) killer = (Player) ev.getDamager();

            if (killer == null) return;

            if (!mechanicAPI.isOnArea(killer)) return;
            ProfileSkyblock profile = Database.profileSkyblock.get(killer.getName());
            JobsObject jobsObject = mainAPI.jobsObject.get(killer.getServerId());
            int job = profile.getJob();
            if (job == 2) {
                int[] integers = jobsObject.getCountAnimals();
                for (int i : integers) {
                    if (integers[i] == 0) {
                        killer.sendMessage(messageAPI.messagesObject.translateMessage("work.finished.kill"));
                        event.setCancelled();
                    }
                }
                if (integers[0] == 0 && integers[1] == 0 && integers[2] == 0 && integers[3] == 0) {
                    killer.sendMessage(messageAPI.messagesObject.translateMessage("work.finished"));
                    event.setCancelled();
                }
                /*switch (eventEntity.getId()) {
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
                    default:
                        return;

                }
                for (Item item : event.getDrops()) {
                    item.setCustomName("JOB");
                    if (!killer.getInventory().canAddItem(item)) {
                        killer.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                    } else {
                        killer.getInventory().addItem(item);
                    }
                }
                event.setDrops(new Item[0]);
                jobsObject.setCountAnimals(integers);
                event.setCancelled();*/
            } else {
                killer.sendMessage(messageAPI.messagesObject.translateMessage("work.unstarted.pvp"));
                event.setCancelled();
            }
        }
    }

    @EventHandler
    public void onDigest(PlayerItemConsumeEvent event) {
        Item item = event.getItem();
        Player player = event.getPlayer();
        if (item.hasCustomName() && item.getName().equals("JOB")) {
            event.setCancelled();
            player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
        }
    }

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = event.getInventory();
        if (inventory.getType() == InventoryType.PLAYER) return;
        for (Item item : player.getInventory().getContents().values()) {
            if (item.hasCustomName() && item.getName().equals("JOB")) {
                player.sendMessage(messageAPI.messagesObject.translateMessage("work.inventory.block"));
                event.setCancelled();
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerInventory playerInventory = player.getInventory();
        for (Item item : playerInventory.getContents().values()) {
            if (item.hasCustomName() && item.getName().equals("JOB")) {
                playerInventory.remove(item);
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
                player.getLevel().dropItem(Vector3i.from(player.getX(), player.getY() + 1, player.getZ()), item);
            }
            event.setCancelled();
            player.setHealth(20);
            player.getFoodData().setLevel(20);
            player.teleport(player.getServer().getDefaultLevel().getSpawnLocation());
            player.removeAllEffects();
            player.getInventory().clearAll();
            player.sendMessage(messageAPI.messagesObject.translateMessage("kill.message"));
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
