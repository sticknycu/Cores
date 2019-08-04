package nycuro.kits.data.specific;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
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
public class DiggerKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.DIGGER;
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
    public Item getPickaxe() {
        Item item = Item.get(Item.DIAMOND_PICKAXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item grass = Item.get(Item.GRASS, 0, 8);
        grass.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Grass");
        Item dirt = Item.get(Item.DIRT, 0, 8);
        dirt.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Dirt");
        Item cobblestone = Item.get(Item.COBBLESTONE, 0, 8);
        cobblestone.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Cobblestone");
        Item stone = Item.get(Item.COBBLESTONE, 0, 8);
        stone.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Stone");
        return new Item[] {
                grass,
                dirt,
                cobblestone,
                stone
        };
    }

    @Override
    public Item[] getInventoryContents() {
        return new Item[] {
                this.getPickaxe()

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
