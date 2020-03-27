package nycuro.abuse.handlers;

import cn.nukkit.block.BlockIds;
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
import cn.nukkit.item.ItemIds;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.player.Player;
import cn.nukkit.utils.Identifier;
import nycuro.abuse.settings.data.AbuseSettings;

import java.util.List;
import java.util.Set;

import static nycuro.api.API.*;

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
        if (player.getGamemode() == Player.CREATIVE) {
            Set<InventoryType> inventoryAbuse = abuseAPI.abuseSettings.getInventoryAbuse();
            if (inventoryAbuse.contains(inventoryType)) {
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        Identifier itemId = event.getItem().getId();
        if (event.getItem().isArmor()) {
            for (Enchantment enchantment : event.getItem().getEnchantments()) {
                if (enchantment.getId() == Enchantment.ID_THORNS) {
                    event.setCancelled(true);
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                }
            }
        }
        if (player.getGamemode() == Player.CREATIVE || event.getItem().getName().equals("NPC")) {
            List<Identifier> abuseBlocks = abuseAPI.abuseSettings.getBlocksAbuse();
            List<Identifier> abuseItems = abuseAPI.abuseSettings.getItemsAbuse();
            if (abuseBlocks.contains(itemId) || abuseItems.contains(itemId)) {
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        Identifier blockId = event.getBlock().getId();
        /*switch (blockId) {
            case 131: // tripware hook
                event.setCancelled(true);
                player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
                break;
        }*/
        List<Identifier> blockAbuse = abuseAPI.abuseSettings.getBlocksAbuse();
        if (player.getGamemode() == Player.CREATIVE /*|| event.getBlock().getName().equals("NPC")*/) {
            if (blockAbuse.contains(blockId)) {
                    event.setCancelled(true);
                    player.sendMessage(messageAPI.messagesObject.translateMessage("mechanic.abuse"));
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
