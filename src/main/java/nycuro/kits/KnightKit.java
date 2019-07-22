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
public class KnightKit {

    public KnightKit(Player player) {
        Item helmet = Item.get(Item.DIAMOND_HELMET);
        Item chestplate = Item.get(Item.DIAMOND_CHESTPLATE);
        Item leggings = Item.get(Item.DIAMOND_LEGGINGS);
        Item boots = Item.get(Item.DIAMOND_BOOTS);
        Item sword = Item.get(Item.DIAMOND_SWORD);
        Item obsidian = Item.get(Item.OBSIDIAN, 0, 64);
        Item tnt = Item.get(Item.TNT, 0, 64);
        Item gapple = Item.get(Item.GOLDEN_APPLE, 0, 16);
        Item egapple = Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 8);
        PlayerInventory inventory = player.getInventory();
        Item itemHand = inventory.getItemInHand();
        Enchantment protection = Enchantment.get(Enchantment.ID_PROTECTION_ALL)
                .setLevel(1);
        Enchantment damage = Enchantment.get(Enchantment.ID_DAMAGE_ALL)
                .setLevel(1);
        helmet.setCustomName("§7» §bKnight Kit Helmet §7«");
        chestplate.setCustomName("§7» §bKnight Kit Chestplate §7«");
        leggings.setCustomName("§7» §bKnight Kit Leggings §7«");
        boots.setCustomName("§7» §bKnight Kit Boots §7«");
        sword.setCustomName("§7» §bKnight Kit Sword §7«");
        obsidian.setCustomName("§7» §bKnight Kit Obsidian §7«");
        tnt.setCustomName("§7» §bKnight Kit TNT §7«");
        gapple.setCustomName("§7» §bKnight Kit Golden Apple §7«");
        egapple.setCustomName("§7» §bKnight Kit Enchanted Golden Apple §7«");
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
            inventory.addItem(egapple);
            inventory.addItem(gapple);
            inventory.addItem(tnt);
            inventory.addItem(obsidian);
            return;
        }
        API.getMessageAPI().sendFullInventoryMessage(player);
    }
}
