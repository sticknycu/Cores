package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class LackerKit extends CommonKit {

    @Override
    public TypeKit getKit() {
        return TypeKit.LACKER;
    }

    @Override
    public double getPrice() {
        return 1250d;
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
        Item steak = Item.get(Item.STEAK, 0, 32);
        steak.setCustomName(symbol + getKit().getName() + empty + "Steak");
        Item ice = Item.get(Item.ICE, 0, 2);
        ice.setCustomName(symbol + getKit().getName() + empty + "Ice");
        Item lava_bucket1 = Item.get(Item.BUCKET, 10, 1);
        lava_bucket1.setCustomName(symbol + getKit().getName() + empty + "Lava Bucket x1");
        Item lava_bucket2 = Item.get(Item.BUCKET, 10, 1);
        lava_bucket2.setCustomName(symbol + getKit().getName() + empty + "Lava Bucket x2");
        Item empty_bucket = Item.get(Item.BUCKET, 10, 10);
        empty_bucket.setCustomName(symbol + getKit().getName() + empty + "Empty Bucket");
        return new Item[] {
                steak,
                ice,
                lava_bucket1,
                lava_bucket2,
                empty_bucket
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
    public boolean passTimer(Player player) {
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        long time = profileSkyblock.getCooldown();
        return (1000 * 60 * 10 - (System.currentTimeMillis() - time)) <= 0;
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
            API.getMessageAPI().sendCooldownMessage(player, System.currentTimeMillis() - time);
        }
    }
}