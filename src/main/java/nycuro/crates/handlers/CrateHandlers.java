package nycuro.crates.handlers;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockIds;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemIds;
import cn.nukkit.player.Player;
import nycuro.utils.typo.FastRandom;

import static nycuro.api.API.*;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class CrateHandlers implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        Item itemHand = playerInventory.getItemInHand();
        Block block = event.getBlock();
        if (!mechanicAPI.isOnSpawn(player)) return;
        if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (block.getId() != BlockIds.CHEST) {
                return;
            }
            event.setCancelled(true);
            if (itemHand.getId() == ItemIds.BOOK) {
                FastRandom.current().ints(1, 1, 100).findFirst().ifPresent(
                        (j) -> {
                            crateAPI.getChange(player, playerInventory, j);
                            crateAPI.addExplosion(block);
                            player.addExperience(10);
                            player.sendTitle(messageAPI.messagesObject.translateMessage("crate.title.receive.first",
                                    messageAPI.messagesObject.translateMessage("crate.title.receive.second", mainAPI.emptyNoSpace + 10)));
                            player.sendMessage(messageAPI.messagesObject.translateMessage("crate.congrats", mainAPI.emptyNoSpace + j));
                            itemHand.setCount(itemHand.getCount() - 1);
                        }
                );
            }
        }
    }
}
