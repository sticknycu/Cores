package nycuro.kits.data;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.TypeClothes;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class GuardianKit extends CommonKit {

    @Override
    public TypeKit getKit() {
        return TypeKit.GUARDIAN;
    }

    @Override
    public Item getHelmet() {
        Item item = Item.get(Item.GOLD_HELMET);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(symbol + getKit() + empty + TypeClothes.HELMET);
        return item;
    }

    @Override
    public Item getArmor() {
        Item item = Item.get(Item.GOLD_CHESTPLATE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(symbol + getKit() + empty + TypeClothes.ARMOR);
        return item;
    }

    @Override
    public Item getPants() {
        Item item = Item.get(Item.GOLD_LEGGINGS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(symbol + getKit() + empty + TypeClothes.PANTS);
        return item;
    }

    @Override
    public Item getBoots() {
        Item item = Item.get(Item.GOLD_BOOTS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(symbol + getKit() + empty + TypeClothes.BOOTS);
        return item;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(Item.IRON_SWORD);
        item.setCustomName(symbol + getKit() + empty + TypeItems.SWORD);
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(Item.IRON_PICKAXE);
        item.setCustomName(symbol + getKit() + empty + TypeItems.PICKAXE);
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.IRON_AXE);
        item.setCustomName(symbol + getKit() + empty + TypeItems.AXE);
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(Item.IRON_SWORD);
        item.setCustomName(symbol + getKit() + empty + TypeItems.SHOVEL);
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item obsidian = Item.get(Item.OBSIDIAN, 0, 64);
        Item tnt = Item.get(Item.TNT, 0, 16);
        Item goldenApple = Item.get(Item.GOLDEN_APPLE, 0, 3);
        Item bread = Item.get(Item.BREAD, 0, 32);
        bread.setCustomName(symbol + getKit() + empty + "Bread");
        tnt.setCustomName(symbol + getKit() + empty + "TNT");
        goldenApple.setCustomName(symbol + getKit() + empty + "Golden Apple");
        obsidian.setCustomName(symbol + getKit() + empty + "Obsidian");
        return new Item[] {
                obsidian,
                tnt,
                goldenApple,
                bread
        };
    }

    @Override
    public Item[] getArmorContents() {
        return new Item[] {
                this.getHelmet(),
                this.getArmor(),
                this.getPants(),
                this.getBoots()

        };
    }

    @Override
    public Item[] getInventoryContents() {
        return new Item[] {
                this.getSword(),
                this.getPickaxe(),
                this.getAxe(),
                this.getShovel()

        };
    }

    @Override
    public boolean canAddKit(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        for (Item item : playerInventory.getArmorContents()) {
            if (item.getId() != 0) {
                return false;
            }
        }
        return (getArmorContents().length + getInventoryContents().length + getOtherItems().length) < 36 - playerInventory.getContents().size() && passTimer(player);
    }

    @Override
    public boolean passTimer(Player player) {
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        long time = profileSkyblock.getCooldown();
        return (1000 * 60 * 60 * 24 - (System.currentTimeMillis() - time)) <= 0;
    }

    @Override
    public void sendKit(Player player) {
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        if (passTimer(player)) {
            if (canAddKit(player)) {
                player.getInventory().setArmorContents(getArmorContents());
                player.getInventory().addItem(getOtherItems());
                profileSkyblock.setCooldown(System.currentTimeMillis());
                API.getMessageAPI().sendReceiveKitMessage(player, getKit());
            } else {
                API.getMessageAPI().sendFullInventoryMessage(player);
            }
        } else {
            long time = profileSkyblock.getCooldown();
            API.getMessageAPI().sendCooldownMessage(player, System.currentTimeMillis() - time);
        }
    }
}
