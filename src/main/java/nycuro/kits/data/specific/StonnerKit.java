package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.StatusKit;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.NameKit;
import nycuro.kits.type.TypeKit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class StonnerKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.STONNER;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.SPECIFIC;
    }

    @Override
    public double getPrice() {
        return 750d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        return StatusKit.UNLOCKED;
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
        Item steak = Item.get(Item.STEAK, 0, 12);
        steak.setCustomName(symbol + getKit().getName() + empty + "Steak");
        Item grass = Item.get(Item.GRASS, 0, 32);
        grass.setCustomName(symbol + getKit().getName() + empty + "Grass");
        Item dirt = Item.get(Item.DIRT, 0, 32);
        dirt.setCustomName(symbol + getKit().getName() + empty + "Dirt");
        Item cobblestone1 = Item.get(Item.COBBLESTONE, 0, 64);
        cobblestone1.setCustomName(symbol + getKit().getName() + empty + "Cobblestone x1");
        Item cobblestone2 = Item.get(Item.COBBLESTONE, 0, 64);
        cobblestone2.setCustomName(symbol + getKit().getName() + empty + "Cobblestone x2");
        Item stone1 = Item.get(Item.COBBLESTONE, 0, 64);
        stone1.setCustomName(symbol + getKit().getName() + empty + "Stone x1");
        Item stone2 = Item.get(Item.COBBLESTONE, 0, 64);
        stone2.setCustomName(symbol + getKit().getName() + empty + "Stone x2");
        return new Item[] {
                steak,
                grass,
                dirt,
                cobblestone1,
                cobblestone2,
                stone1,
                stone2
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
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        double dollars = profileSkyblock.getDollars();
        return getPrice() < dollars;
    }

    @Override
    public boolean canAddKit(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        for (Item item : playerInventory.getArmorContents()) {
            if (item.getId() != 0) {
                return false;
            }
        }
        return (getInventoryContents().length + getOtherItems().length) < 36 - playerInventory.getContents().size();
    }

    @Override
    public long getTimer() {
        return 1000 * 60 * 30;
    }

    @Override
    public boolean passTimer(Player player) {
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        long time = profileSkyblock.getCooldown();
        return (getTimer() - (System.currentTimeMillis() - time)) <= 0;
    }

    @Override
    public void sendKit(Player player) {
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        if (passTimer(player)) {
            if (canAddKit(player)) {
                if (hasEnoughDollars(player)) {
                    player.getInventory().addItem(getInventoryContents());
                    player.getInventory().addItem(getOtherItems());
                    profileSkyblock.setCooldown(System.currentTimeMillis());
                    profileSkyblock.setDollars(profileSkyblock.getDollars() - getPrice());
                    API.getMessageAPI().sendReceiveKitMessage(player, getKit());
                } else {
                    double dollars = profileSkyblock.getDollars();
                    API.getMessageAPI().sendUnsuficientMoneyMessage(player, getPrice() - dollars);
                }
            } else {
                API.getMessageAPI().sendFullInventoryMessage(player);
            }
        } else {
            long time = profileSkyblock.getCooldown();
            API.getMessageAPI().sendCooldownMessage(player, System.currentTimeMillis() - time, getTimer());
        }
    }
}
