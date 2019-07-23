package nycuro.kits.data;

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
public class SparrowKit {

    public SparrowKit(Player player) {
        Item helmet = Item.get(Item.IRON_HELMET);
        Item chestplate = Item.get(Item.IRON_CHESTPLATE);
        Item leggings = Item.get(Item.IRON_LEGGINGS);
        Item boots = Item.get(Item.IRON_BOOTS);
        Item sword = Item.get(Item.IRON_SWORD);
        Item obsidian = Item.get(Item.OBSIDIAN, 0, 32);
        Item tnt = Item.get(Item.TNT, 0, 32);
        Item gapple = Item.get(Item.GOLDEN_APPLE, 0, 8);
        Item egapple = Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 4);
        PlayerInventory inventory = player.getInventory();
        Item itemHand = inventory.getItemInHand();
        Enchantment protection = Enchantment.get(Enchantment.ID_PROTECTION_ALL)
                .setLevel(1);
        Enchantment damage = Enchantment.get(Enchantment.ID_DAMAGE_ALL)
                .setLevel(1);
        helmet.setCustomName("§7» §bSparrow Kit Helmet §7«");
        chestplate.setCustomName("§7» §bSparrow Kit Chestplate §7«");
        leggings.setCustomName("§7» §bSparrow Kit Leggings §7«");
        boots.setCustomName("§7» §bSparrow Kit Boots §7«");
        sword.setCustomName("§7» §bSparrow Kit Sword §7«");
        obsidian.setCustomName("§7» §bSparrow Kit Obsidian §7«");
        tnt.setCustomName("§7» §bSparrow Kit TNT §7«");
        gapple.setCustomName("§7» §bSparrow Kit Golden Apple §7«");
        egapple.setCustomName("§7» §bSparrow Kit Enchanted Golden Apple §7«");
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
            inventory.addItem(gapple);
            inventory.addItem(egapple);
            inventory.addItem(obsidian);
            inventory.addItem(tnt);
            return;
        }
        API.getMessageAPI().sendFullInventoryMessage(player);
    }
}
