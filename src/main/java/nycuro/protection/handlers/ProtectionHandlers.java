package nycuro.protection.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
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
import cn.nukkit.event.vehicle.VehicleCreateEvent;
import cn.nukkit.event.vehicle.VehicleDamageEvent;
import cn.nukkit.event.vehicle.VehicleDestroyEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import nycuro.api.API;
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
        if (mechanicAPI.isOnPrincipalWorld(player) && !mechanicAPI.isOnArea(player)) {
            API.log("true 8");
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.break"));
            event.setCancelled(true);
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
                damager.sendMessage(messageAPI.messagesObject.translateMessage("block.pvp"));
                event.setCancelled(true);
            }
            for (Player pl : new Player[]{player, damager}) {
                if (mechanicAPI.isOnSpawn(pl) || mechanicAPI.isOnArea(pl)) break;
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
    public void onDamageVehicle(VehicleDamageEvent event) {
        Entity entity = event.getAttacker();
        if (mechanicAPI.isOnPrincipalWorld(entity)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void emptyBucket(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void fillBucket(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
            event.setCancelled(true);
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
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            if (event.getAction() == PlayerInteractEvent.Action.PHYSICAL) {
                player.sendMessage(messageAPI.messagesObject.translateMessage("block.break"));
                event.setCancelled(true);
            }
            Item itemHand = inventory.getItemInHand();
            if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                switch (itemHand.getId()) {
                    case Item.DIAMOND_HOE:
                    case Item.GOLD_HOE:
                    case Item.IRON_HOE:
                    case Item.STONE_HOE:
                    case Item.WOODEN_HOE:
                    case Item.FLINT_AND_STEEL:
                        player.sendMessage(messageAPI.messagesObject.translateMessage("block.place"));
                        event.setCancelled(true);
                        return;
                    default:
                        event.setCancelled(false);
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onDropItemFrameItem(ItemFrameDropItemEvent event) {
        Player player = event.getPlayer();
        if (mechanicAPI.isOnPrincipalWorld(player)) {
            event.setCancelled(true);
        }
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
