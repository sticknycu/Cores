package nycuro.kits;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.API;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class EnchantedStarterKit {

    public EnchantedStarterKit(Player player) {
        Item helmet = Item.get(Item.LEATHER_CAP);
        Item chestplate = Item.get(Item.LEATHER_TUNIC);
        Item leggings = Item.get(Item.LEATHER_PANTS);
        Item boots = Item.get(Item.LEATHER_BOOTS);
        Item sword = Item.get(Item.STONE_SWORD);
        PlayerInventory inventory = player.getInventory();
        Item itemHand = inventory.getItemInHand();
        Enchantment protection = Enchantment.get(Enchantment.ID_PROTECTION_ALL)
                .setLevel(1);
        Enchantment damage = Enchantment.get(Enchantment.ID_DAMAGE_ALL)
                .setLevel(1);
        helmet.setCustomName("§7» §bEnchanted Starter Kit Helmet §7«");
        chestplate.setCustomName("§7» §bEnchanted Starter Kit Chestplate §7«");
        leggings.setCustomName("§7» §bEnchanted Starter Kit Leggings §7«");
        boots.setCustomName("§7» §bEnchanted Starter Kit Boots §7«");
        sword.setCustomName("§7» §bEnchanted Starter Kit Sword §7«");
        helmet.addEnchantment(protection);
        chestplate.addEnchantment(protection);
        leggings.addEnchantment(protection);
        boots.addEnchantment(protection);
        sword.addEnchantment(damage);
        if (inventory.canAddItem(helmet)) {
            inventory.removeItem(itemHand);
            inventory.addItem(helmet);
            inventory.addItem(chestplate);
            inventory.addItem(leggings);
            inventory.addItem(boots);
            inventory.addItem(sword);
        } else {
            API.getMessageAPI().sendFullInventoryMessage(player);
        }
    }
}
