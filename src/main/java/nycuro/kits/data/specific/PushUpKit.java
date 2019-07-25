package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

import java.util.ArrayList;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class PushUpKit extends CommonKit {

    @Override
    public TypeKit getKit() {
        return TypeKit.PUSH_UP;
    }

    @Override
    public double getPrice() {
        return 650d;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.DIAMOND_AXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(symbol + getKit().getName() + empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        ArrayList<Item> itemslog1 = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            Item item = Item.get(Item.WOOD, i, 8);
            item.setCustomName(symbol + getKit().getName() + empty + "Wood Type " + i);
            itemslog1.add(item);
        }
        ArrayList<Item> itemslog2 = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            Item item = Item.get(Item.WOOD2, i, 8);
            item.setCustomName(symbol + getKit().getName() + empty + "Wood2 Type " + i);
            itemslog2.add(item);
        }
        Item[] item = new Item[7];
        for (int i = 0; i <= 3; i++) {
            switch (i) {
                case 0:
                    item[i + 4] = itemslog2.get(i);
                    break;
                case 1:
                    item[i + 5] = itemslog2.get(i);
                    break;
            }
            item[i] = itemslog1.get(i);
        }
        return item;
    }

    @Override
    public Item[] getInventoryContents() {
        return new Item[] {
                this.getAxe()

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
        return (1000 * 60 * 60 - (System.currentTimeMillis() - time)) <= 0;
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
