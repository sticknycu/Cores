package nycuro.kits.data.specific;


import cn.nukkit.block.BlockIds;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemIds;
import cn.nukkit.player.Player;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.NameKit;
import nycuro.kits.type.StatusKit;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class LackerKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.LACKER;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.SPECIFIC;
    }

    @Override
    public double getPrice() {
        return 1250d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        return StatusKit.UNLOCKED;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(ItemIds.STONE_SWORD);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SWORD.getType());
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(ItemIds.STONE_PICKAXE);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(ItemIds.STONE_AXE);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(ItemIds.STONE_SHOVEL);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SHOVEL.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item steak = Item.get(ItemIds.COOKED_BEEF, 0, 32);
        steak.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Steak");
        Item ice = Item.get(BlockIds.ICE, 0, 2);
        ice.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Ice");
        Item lava_bucket1 = Item.get(ItemIds.BUCKET, 10, 1);
        lava_bucket1.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Lava Bucket x1");
        Item lava_bucket2 = Item.get(ItemIds.BUCKET, 10, 1);
        lava_bucket2.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Lava Bucket x2");
        Item empty_bucket = Item.get(ItemIds.BUCKET, 10, 10);
        empty_bucket.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "mainAPI.empty Bucket");
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
        return (getInventoryContents().length + getOtherItems().length) < 36 - playerInventory.getContents().size();
    }

    @Override
    public long getTimer() {
        return 1000 * 60 * 10;
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
                    player.sendMessage(messageAPI.messagesObject.translateMessage("kits.receive", getKit().getName()));
                } else {
                    double dollars = profileSkyblock.getDollars();
                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.money.enough", mainAPI.emptyNoSpace + dollars,
                            mainAPI.emptyNoSpace + (getPrice() - dollars)));
                }
            } else {
                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));;
            }
        } else {
            long time = profileSkyblock.getCooldown();
            player.sendMessage(messageAPI.messagesObject.translateMessage("generic.timegone",
                    API.time(System.currentTimeMillis() - time), mainAPI.emptyNoSpace + getTimer()));
        }
    }
}