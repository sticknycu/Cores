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
import cn.nukkit.player.Player;

import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class AbuseHandlers implements Listener {

    @EventHandler
    public void onInteract(InventoryOpenEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        InventoryType inventoryType = event.getInventory().getType();
        switch (inventoryType) {
            case ANVIL:
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.notwork"));
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
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                    break;
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        int itemId = event.getItem().getId();
        if (event.getItem().isArmor()) {
            for (Enchantment enchantment : event.getItem().getEnchantments()) {
                if (enchantment.getId() == Enchantment.ID_THORNS) {
                    event.setCancelled(true);
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                    break;
                }
            }
        }
        if (player.getGamemode() == Player.CREATIVE || event.getItem().getName().equals("NPC")) {
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
                case 14:
                    event.setCancelled(true);
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                    break;
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        int blockId = event.getBlock().getId();
        switch (blockId) {
            case 131:
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                break;
        }
        if (player.getGamemode() == Player.CREATIVE || event.getBlock().getName().equals("NPC")) {
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
                case 14:
                    event.setCancelled(true);
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
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
            if (damager.isOp()) return;
            if (damager.getGamemode() == Player.CREATIVE) {
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        if (player.getGamemode() == Player.CREATIVE || event.getItem().getName().equals("NPC")) {
            event.setCancelled(true);
            player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
        }
    }
}
