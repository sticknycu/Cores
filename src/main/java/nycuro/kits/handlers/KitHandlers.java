package nycuro.kits.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class KitHandlers implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        if (!player.hasPlayedBefore()) {
            Item casca = Item.get(298, 0, 1);
            Item armura = Item.get(299, 0, 1);
            Item pantaloni = Item.get(300, 0, 1);
            Item papuci = Item.get(301, 0, 1);
            Item sabie = Item.get(272, 0, 1);
            Item mancare = Item.get(297, 0, 16);
            inventory.setHelmet(casca);
            inventory.setChestplate(armura);
            inventory.setLeggings(pantaloni);
            inventory.setBoots(papuci);
            inventory.addItem(sabie);
            inventory.addItem(mancare);
        }
    }
}
