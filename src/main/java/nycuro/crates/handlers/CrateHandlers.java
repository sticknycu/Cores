package nycuro.crates.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;

import java.util.Random;

import static nycuro.api.API.mechanicAPI;
import static nycuro.api.API.crateAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
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
            if (block.getId() != Block.CHEST) {
                return;
            }
            event.setCancelled(true);
            if (itemHand.getCount() > 1) {
                messageAPI.sendCrateCountMessage(player);
            } else {
                switch (itemHand.getId()) {
                    case Item.TRIPWIRE_HOOK:
                        Random random = new Random();
                        int number = random.nextInt(100) + 1;
                        crateAPI.getChange(player, playerInventory, number);
                        crateAPI.addExplosion(block);
                        player.addExperience(10);
                        messageAPI.sendReceiveItemMessage(player, 10);
                        messageAPI.sendCrateMessage(player, number);
                        playerInventory.removeItem(itemHand);
                        break;
                }
            }
        }
    }
}
