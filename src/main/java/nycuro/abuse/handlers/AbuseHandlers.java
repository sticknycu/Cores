package nycuro.abuse.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.inventory.InventoryOpenEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.API;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class AbuseHandlers implements Listener {

    @EventHandler
    public void onInteract(InventoryOpenEvent event) {
        Player player = event.getPlayer();
        InventoryType inventoryType = event.getInventory().getType();
        switch (inventoryType) {
            case ENCHANT_TABLE:
            case ANVIL:
                event.setCancelled(true);
                API.getMessageAPI().sendNotWorkServiceMessage(player);
                break;
        }
        if (player.getGamemode() == Player.CREATIVE) {
            switch (inventoryType) {
                case CHEST:
                case DOUBLE_CHEST:
                case CRAFTING:
                case FURNACE:
                case ENDER_CHEST:
                case HOPPER:
                case DROPPER:
                case BREWING_STAND:
                case ENCHANT_TABLE:
                case ANVIL:
                case DISPENSER:
                case WORKBENCH:
                    event.setCancelled(true);
                    API.getMessageAPI().sendAbuseMessage(player);
                    break;
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int itemId = event.getItem().getId();
        switch (itemId) {
            case Item.ENCHANTING_TABLE:
                event.setCancelled(true);
                API.getMessageAPI().sendAbuseMessage(player);
                break;
        }
        if (event.getItem().isArmor()) {
            for (Enchantment enchantment : event.getItem().getEnchantments()) {
                if (enchantment.getId() == Enchantment.ID_THORNS) {
                    event.setCancelled(true);
                    API.getMessageAPI().sendAbuseMessage(player);
                    break;
                }
            }
        }
        if (player.getGamemode() == Player.CREATIVE) {
            switch (itemId) {
                case 384:
                case 15:
                case 16:
                case 21:
                case 46:
                case 56:
                case 73:
                case 129:
                case 153:
                case 22:
                case 41:
                case 42:
                case 57:
                case 152:
                    event.setCancelled(true);
                    API.getMessageAPI().sendAbuseMessage(player);
                    break;
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        int blockId = event.getBlock().getId();
        switch (blockId) {
            case Item.ENCHANTING_TABLE:
            case 131:
                event.setCancelled(true);
                API.getMessageAPI().sendAbuseMessage(player);
                break;
        }
        if (player.getGamemode() == Player.CREATIVE) {
            switch (blockId) {
                case 15:
                case 16:
                case 21:
                case 46:
                case 56:
                case 73:
                case 129:
                case 153:
                case 22:
                case 41:
                case 42:
                case 57:
                case 152:
                    event.setCancelled(true);
                    API.getMessageAPI().sendAbuseMessage(player);
                    break;
            }
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
            if (damager.getGamemode() == Player.CREATIVE) {
                event.setCancelled(true);
                API.getMessageAPI().sendAbuseMessage(damager);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGamemode() == Player.CREATIVE) {
            event.setCancelled(true);
            API.getMessageAPI().sendAbuseMessage(player);
        }
    }
}
