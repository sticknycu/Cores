package nycuro.crate.handlers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import nycuro.api.API;

import java.util.Random;

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
        if (!API.getMechanicAPI().isOnSpawn(player)) return;
        if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (block.getId() != Block.CHEST) {
                return;
            }
            event.setCancelled(true);
            if (itemHand.getCount() > 1) {
                API.getMessageAPI().sendCrateCountMessage(player);
            } else {
                switch (itemHand.getId()) {
                    case Item.TRIPWIRE_HOOK:
                        Random random = new Random();
                        int number = random.nextInt(100) + 1;
                        API.getCrateAPI().getChange(player, playerInventory, number);
                        API.getCrateAPI().addExplosion(block);
                        player.addExperience(10);
                        API.getMessageAPI().sendReceiveItemMessage(player, 10);
                        API.getMessageAPI().sendCrateMessage(player, number);
                        playerInventory.removeItem(itemHand);
                        break;
                }
            }
        }
    }
}
