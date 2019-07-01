package nycuro.kits.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

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
            Item casca = Item.get(Item.CHAIN_HELMET);
            casca.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
            Item armura = Item.get(Item.CHAIN_CHESTPLATE);
            armura.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
            Item pantaloni = Item.get(Item.CHAIN_LEGGINGS);
            pantaloni.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
            Item papuci = Item.get(Item.CHAIN_BOOTS);
            papuci.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
            Item sabie = Item.get(Item.STONE_SWORD);
            Item pick = Item.get(Item.STONE_PICKAXE);
            Item axe = Item.get(Item.STONE_AXE);
            Item shovel = Item.get(Item.STONE_SHOVEL);
            Item hoe = Item.get(Item.STONE_HOE);
            Item bread = Item.get(Item.BREAD, 0, 16);
            inventory.setHelmet(casca);
            inventory.setChestplate(armura);
            inventory.setLeggings(pantaloni);
            inventory.setBoots(papuci);
            inventory.addItem(sabie);
            inventory.addItem(pick);
            inventory.addItem(axe);
            inventory.addItem(shovel);
            inventory.addItem(hoe);
            inventory.addItem(bread);
        }
    }
}
