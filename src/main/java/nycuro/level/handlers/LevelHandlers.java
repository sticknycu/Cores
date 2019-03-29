package nycuro.level.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.API;


/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LevelHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        startItems(player);
        //player.setNameTag("§7[§e" + JobsAPI.jobs.get(job) + "§7] " + "§a[§c" + level + "§a] §7" + player.getName());
    }

    private void startItems(Player player) {
        Item COMPASS = Item.get(Item.COMPASS);
        COMPASS.setCustomName(API.getMessageAPI().getCompassMessage(player));
        Item DYE = Item.get(Item.DYE);
        DYE.setCustomName(API.getMessageAPI().getDyeStageOneMessage(player));
        Item FEATHER = Item.get(Item.FEATHER);
        FEATHER.setCustomName(API.getMessageAPI().getFeatherMessage(player));
        Item NETHER_STAR = Item.get(Item.NETHER_STAR);
        NETHER_STAR.setCustomName(API.getMessageAPI().getNetherStarMessage(player));
        Item BOW = Item.get(Item.BOW);
        Enchantment enchantmentInfinity = Enchantment.getEnchantment(Enchantment.ID_BOW_INFINITY);
        enchantmentInfinity.setLevel(2);
        BOW.setCustomName(API.getMessageAPI().getBowMessage(player));
        Item ARROW = Item.get(Item.ARROW);
        ARROW.setCustomName(API.getMessageAPI().getArrowMessage(player));
        PlayerInventory playerInventory = player.getInventory();
        playerInventory.setItem(0, DYE);
        playerInventory.setItem(2, FEATHER);
        playerInventory.setItem(4, COMPASS);
        playerInventory.setItem(6, BOW);
        playerInventory.setItem(8, NETHER_STAR);
        playerInventory.setItem(9, ARROW);
    }
}
