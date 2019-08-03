package nycuro.kits.data.premium;

import cn.nukkit.Player;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import nycuro.api.API;
import nycuro.database.DatabaseMySQL;
import nycuro.database.objects.KitsObject;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.*;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ViperKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.VIPER;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.PREMIUM;
    }

    @Override
    public double getPrice() {
        return 5000d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        KitsObject kitsObject = DatabaseMySQL.kitsSkyblock.get(player.getName());
        if (kitsObject.isPremium1()) return StatusKit.UNLOCKED;
        else return StatusKit.LOCKED;
    }

    @Override
    public Item getHelmet() {
        Item item = Item.get(Item.IRON_HELMET);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeClothes.HELMET.getType());
        return item;
    }

    @Override
    public Item getArmor() {
        Item item = Item.get(Item.IRON_CHESTPLATE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeClothes.ARMOR.getType());
        return item;
    }

    @Override
    public Item getPants() {
        Item item = Item.get(Item.IRON_LEGGINGS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeClothes.PANTS.getType());
        return item;
    }

    @Override
    public Item getBoots() {
        Item item = Item.get(Item.IRON_BOOTS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeClothes.BOOTS.getType());
        return item;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(Item.IRON_SWORD);
        item.addEnchantment(Enchantment.get(Enchantment.ID_KNOCKBACK).setLevel(1));
        item.addEnchantment(Enchantment.get(Enchantment.ID_DAMAGE_ALL).setLevel(1));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeItems.SWORD.getType());
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(Item.IRON_PICKAXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.addEnchantment(Enchantment.get(Enchantment.ID_FORTUNE_DIGGING).setLevel(2));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(Item.IRON_AXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(Item.IRON_SHOVEL);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + TypeItems.SHOVEL.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item steak = Item.get(Item.STEAK, 0, 64);
        steak.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Steak");
        Item potion_reg2 = Item.get(Item.POTION, 30, 10);
        potion_reg2.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Regeneration 2 Potion");
        Item potion_strenght1 = Item.get(Item.POTION, 31, 10);
        potion_strenght1.setCustomName(API.getMainAPI().symbol + getKit().getName() + API.getMainAPI().empty + "Strenght 1 Potion");
        return new Item[] {
                steak,
                potion_reg2,
                potion_strenght1
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
        ProfileSkyblock profileSkyblock = DatabaseMySQL.profileSkyblock.get(player.getName());
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
        return 1000 * 60 * 60 * 48;
    }

    @Override
    public boolean passTimer(Player player) {
        ProfileSkyblock profileSkyblock = DatabaseMySQL.profileSkyblock.get(player.getName());
        long time = profileSkyblock.getCooldown();
        return (getTimer() - (System.currentTimeMillis() - time)) <= 0;
    }

    @Override
    public void sendKit(Player player) {
        ProfileSkyblock profileSkyblock = DatabaseMySQL.profileSkyblock.get(player.getName());
        if (getStatus(player).equals(StatusKit.LOCKED)) {
            player.sendMessage(API.getMessageAPI().sendLockedKitStatus(player));
        } else {
            if (passTimer(player)) {
                if (canAddKit(player)) {
                    if (hasEnoughDollars(player)) {
                        player.getInventory().setArmorContents(getArmorContents());
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
}

