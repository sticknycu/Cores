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
public class VIPPlusKit {

    public VIPPlusKit(Player player) {
        Item helmet = Item.get(Item.CHAIN_HELMET);
        Item chestplate = Item.get(Item.CHAIN_CHESTPLATE);
        Item leggings = Item.get(Item.CHAIN_LEGGINGS);
        Item boots = Item.get(Item.CHAIN_BOOTS);
        Item sword = Item.get(Item.IRON_SWORD);
        PlayerInventory inventory = player.getInventory();
        Item itemHand = inventory.getItemInHand();
        Enchantment sharpness = Enchantment.get(Enchantment.ID_DAMAGE_ALL)
                .setLevel(3);
        Enchantment protection = Enchantment.get(Enchantment.ID_PROTECTION_ALL)
                .setLevel(3);
        Enchantment unbreaking = Enchantment.get(Enchantment.ID_DURABILITY)
                .setLevel(3);
        helmet.setCustomName("§7» §bVIP Kit Helmet §7«");
        chestplate.setCustomName("§7» §bVIP Kit Chestplate §7«");
        leggings.setCustomName("§7» §bVIP Kit Leggings §7«");
        boots.setCustomName("§7» §bVIP Kit Boots §7«");
        sword.setCustomName("§7» §bVIP Kit Sword §7«");
        helmet.addEnchantment(protection);
        chestplate.addEnchantment(protection);
        leggings.addEnchantment(protection);
        boots.addEnchantment(protection);
        sword.addEnchantment(sharpness);
        sword.addEnchantment(unbreaking);
        if (inventory.canAddItem(helmet)) {
            inventory.removeItem(itemHand);
            inventory.addItem(helmet);
            inventory.addItem(chestplate);
            inventory.addItem(leggings);
            inventory.addItem(boots);
            inventory.addItem(sword);
            return;
        }
        API.getMessageAPI().sendFullInventoryMessage(player);
    }
}
