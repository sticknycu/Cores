package nycuro.protection.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.block.ItemFrameDropItemEvent;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.ExplosionPrimeEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.PlayerBucketEmptyEvent;
import cn.nukkit.event.player.PlayerBucketFillEvent;
import cn.nukkit.event.player.PlayerGameModeChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.mechanicAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.combatAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ProtectionHandlers implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        if (mechanicAPI.isOnPrincipalWorld(player)) {
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
                        Item[] items = event.getDrops();
                        for (Item item : items) {
                            CompoundTag tag = item.getNamedTag();
                            tag.putBoolean("JOB", true);
                            item.setNamedTag(tag);
                            PlayerInventory playerInventory = player.getInventory();
                            if (!playerInventory.canAddItem(item)) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                            } else {
                                playerInventory.addItem(item);
                            }
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
                        Item[] items = event.getDrops();
                        for (Item item : items) {
                            CompoundTag tag = item.getNamedTag();
                            tag.putBoolean("JOB", true);
                            item.setNamedTag(tag);
                            PlayerInventory playerInventory = player.getInventory();
                            if (!playerInventory.canAddItem(item)) {
                                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                            } else {
                                playerInventory.addItem(item);
                            }
                        }
                    }
                    return;
            }
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.break"));
        }
    }

    // Job Fisherman
    @EventHandler
    public void onReceiveFish(InventoryPickupItemEvent event) {
        Item item = event.getItem().getItem();
        for (Player player : event.getViewers()) {
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            if (mechanicAPI.isOnPrincipalWorld(player)) {
                if (profileSkyblock.getJob() == 4) {
                    CompoundTag tag = item.getNamedTag();
                    tag.putBoolean("JOB", true);
                    item.setNamedTag(tag);
                    PlayerInventory playerInventory = player.getInventory();
                    if (!playerInventory.canAddItem(item)) {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
                    } else {
                        playerInventory.addItem(item);
                    }
                    event.getItem().kill();
                }
            }
        }
    }

    @EventHandler
    public void onExplode(ExplosionPrimeEvent event) {
        // todo: creeper (AI GRIJA!)
        if (!(event.getEntity() instanceof Player)) {
            event.setCancelled(true);
        }
        Player player = (Player) event.getEntity();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHurt(EntityDamageEvent event) {
        Player player = (event.getEntity() instanceof Player) ? (Player) event.getEntity() : null;
        if (player == null) return;
        Player damager = null;
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) event;
            if (ev instanceof EntityDamageByChildEntityEvent) {
                EntityDamageByChildEntityEvent evc = (EntityDamageByChildEntityEvent) ev;
                if (evc.getDamager() instanceof Player) damager = (Player) evc.getDamager();
            } else if (ev.getDamager() instanceof Player) damager = (Player) ev.getDamager();

            if (damager == null) return;
            if (mechanicAPI.isOnSpawn(player)) {
                event.setCancelled(true);
                damager.sendMessage(messageAPI.messagesObject.translateMessage("block.pvp"));
            }
            for (Player pl : new Player[]{player, damager}) {
                if (mechanicAPI.isOnSpawn(pl)) break;
                if (!combatAPI.inCombat(pl)) {
                    if (mainAPI.bossbar.get(pl.getUniqueId()) != null) {
                        mainAPI.bossbar.get(pl.getUniqueId()).setText(messageAPI.messagesObject.translateMessage("combat.bossbar", "13"));
                        mainAPI.bossbar.get(pl.getUniqueId()).setLength(100F);
                    }
                }
            }
        }
        EntityDamageEvent.DamageCause cause = event.getCause();
        if (cause == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void emptyBucket(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            event.setCancelled(true);
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
        }
    }

    @EventHandler
    public void fillBucket(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            event.setCancelled(true);
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
        }
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clearAll();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        Item itemHand = inventory.getItemInHand();
        if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            switch (itemHand.getId()) {
                case Item.DIAMOND_HOE:
                case Item.GOLD_HOE:
                case Item.IRON_HOE:
                case Item.STONE_HOE:
                case Item.WOODEN_HOE:
                case Item.FLINT_AND_STEEL:
                    if (mechanicAPI.isOnPrincipalWorld(player)) {
                        player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
                        event.setCancelled(true);
                        return;
                    }
                default:
                    event.setCancelled(false);
                    break;
            }
        }
    }

    @EventHandler
    public void onDropItemFrameItem(ItemFrameDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            event.setCancelled(true);
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
        }
    }
}
