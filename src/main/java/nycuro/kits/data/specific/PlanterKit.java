package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
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
public class PlanterKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.PLANTER;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.SPECIFIC;
    }

    @Override
    public double getPrice() {
        return 1500d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        return StatusKit.UNLOCKED;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(Item.STONE_SWORD);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SWORD.getType());
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(Item.STONE_PICKAXE);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.STONE_AXE);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(Item.STONE_SHOVEL);
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SHOVEL.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            Item item = Item.get(Item.SAPLING, i, 12);
            item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Sapling Type " + i);
            items.add(item);
        }
        Item grass = Item.get(Item.GRASS, 0, 32);
        grass.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Grass");
        Item dirt = Item.get(Item.DIRT, 0, 32);
        dirt.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Dirt");
        Item cobblestone = Item.get(Item.COBBLESTONE, 0, 64);
        cobblestone.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Cobblestone");
        Item bonemeal = Item.get(Item.DYE, 15, 24);
        bonemeal.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Bone Meal");
        Item[] item = new Item[10];
        for (int i = 0; i <= 5; i++) {
            item[i] = items.get(i);
        }
        item[6] = grass;
        item[7] = dirt;
        item[8] = cobblestone;
        item[9] = bonemeal;
        return item;
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
        return 1000 * 60 * 20;
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
