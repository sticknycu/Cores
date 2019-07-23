package nycuro.kits.data.clasic;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import nycuro.kits.CommonKit;
import nycuro.kits.type.TypeClothes;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class StarterKit extends CommonKit {

    @Override
    public TypeKit getKit() {
        return TypeKit.STARTER;
    }

    @Override
    public Item getHelmet() {
        Item item = Item.get(Item.LEATHER_CAP);
        item.setCustomName(symbol + getKit().getName() + empty + TypeClothes.HELMET.getType());
        return item;
    }

    @Override
    public Item getArmor() {
        Item item = Item.get(Item.LEATHER_TUNIC);
        item.setCustomName(symbol + getKit().getName() + empty + TypeClothes.ARMOR.getType());
        return item;
    }

    @Override
    public Item getPants() {
        Item item = Item.get(Item.LEATHER_PANTS);
        item.setCustomName(symbol + getKit().getName() + empty + TypeClothes.PANTS.getType());
        return item;
    }

    @Override
    public Item getBoots() {
        Item item = Item.get(Item.LEATHER_BOOTS);
        item.setCustomName(symbol + getKit().getName() + empty + TypeClothes.BOOTS.getType());
        return item;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(Item.STONE_SWORD);
        item.setCustomName(symbol + getKit().getName() + empty + TypeItems.SWORD.getType());
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(Item.STONE_PICKAXE);
        item.setCustomName(symbol + getKit().getName() + empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.STONE_AXE);
        item.setCustomName(symbol + getKit().getName() + empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(Item.STONE_SHOVEL);
        item.setCustomName(symbol + getKit().getName() + empty + TypeItems.SHOVEL.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item bread = Item.get(Item.BREAD, 0, 32);
        bread.setCustomName(symbol + getKit().getName() + empty + "Bread");
        return new Item[] {
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
    public boolean hasEnoughDollars(Player player) {
        return true;
    }

    @Override
    public boolean passTimer(Player player) {
        return true;
    }

    @Override
    public boolean canAddKit(Player player) {
        return true;
    }

    @Override
    public void sendKit(Player player) {
        player.getInventory().setArmorContents(getArmorContents());
        player.getInventory().addItem(getInventoryContents());
        player.getInventory().addItem(getOtherItems());
    }
}

