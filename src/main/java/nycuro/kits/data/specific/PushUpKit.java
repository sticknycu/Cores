package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.player.Player;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.NameKit;
import nycuro.kits.type.StatusKit;
import nycuro.kits.type.TypeItems;
import nycuro.kits.type.TypeKit;

import java.util.ArrayList;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class PushUpKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.PUSH_UP;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.SPECIFIC;
    }

    @Override
    public double getPrice() {
        return 650d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        return StatusKit.UNLOCKED;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.DIAMOND_AXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        ArrayList<Item> itemslog1 = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            Item item = Item.get(Item.WOOD, i, 8);
            item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Wood Type " + i);
            itemslog1.add(item);
        }
        ArrayList<Item> itemslog2 = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            Item item = Item.get(Item.WOOD2, i, 8);
            item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Wood2 Type " + i);
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
    public long getTimer() {
        return 1000 * 60 * 60;
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
                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.inventory.get.error"));
            }
        } else {
            long time = profileSkyblock.getCooldown();
            player.sendMessage(messageAPI.messagesObject.translateMessage("generic.timegone",
                    API.time(System.currentTimeMillis() - time), mainAPI.emptyNoSpace + getTimer()));
        }
    }
}
