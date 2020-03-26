package nycuro.kits.data.premium;


import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemIds;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.player.Player;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.KitsObject;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.CommonKit;
import nycuro.kits.type.*;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class DetonatorKit extends CommonKit {

    @Override
    public NameKit getKit() {
        return NameKit.DETONATOR;
    }

    @Override
    public TypeKit getType() {
        return TypeKit.PREMIUM;
    }

    @Override
    public double getPrice() {
        return 9000d;
    }

    @Override
    public StatusKit getStatus(Player player) {
        KitsObject kitsObject = Database.kitsSkyblock.get(player.getName());
        if (kitsObject.isPremium4()) return StatusKit.UNLOCKED;
        else return StatusKit.LOCKED;
    }

    @Override
    public Item getHelmet() {
        Item item = Item.get(ItemIds.CHAINMAIL_HELMET);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL).setLevel(3));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeClothes.HELMET.getType());
        return item;
    }

    @Override
    public Item getArmor() {
        Item item = Item.get(ItemIds.CHAINMAIL_CHESTPLATE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL).setLevel(3));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeClothes.ARMOR.getType());
        return item;
    }

    @Override
    public Item getPants() {
        Item item = Item.get(ItemIds.CHAINMAIL_LEGGINGS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL).setLevel(3));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeClothes.PANTS.getType());
        return item;
    }

    @Override
    public Item getBoots() {
        Item item = Item.get(ItemIds.CHAINMAIL_BOOTS);
        item.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL).setLevel(3));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeClothes.BOOTS.getType());
        return item;
    }

    @Override
    public Item getSword() {
        Item item = Item.get(ItemIds.DIAMOND_SWORD);
        item.addEnchantment(Enchantment.get(Enchantment.ID_KNOCKBACK).setLevel(1));
        item.addEnchantment(Enchantment.get(Enchantment.ID_LOOTING).setLevel(1));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SWORD.getType());
        return item;
    }

    @Override
    public Item getPickaxe() {
        Item item = Item.get(ItemIds.DIAMOND_PICKAXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.addEnchantment(Enchantment.get(Enchantment.ID_FORTUNE_DIGGING).setLevel(2));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.PICKAXE.getType());
        return item;
    }

    @Override
    public Item getAxe() {
        Item item = Item.get(ItemIds.DIAMOND_AXE);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.AXE.getType());
        return item;
    }

    @Override
    public Item getShovel() {
        Item item = Item.get(ItemIds.DIAMOND_SHOVEL);
        item.addEnchantment(Enchantment.get(Enchantment.ID_EFFICIENCY).setLevel(2));
        item.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + TypeItems.SHOVEL.getType());
        return item;
    }

    @Override
    public Item[] getOtherItems() {
        Item steak = Item.get(ItemIds.COOKED_BEEF, 0, 64);
        steak.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Steak");
        Item potion_speed2 = Item.get(ItemIds.POTION, 16, 10);
        potion_speed2.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Speed 2 Potion");
        Item splashpotion_poison2 = Item.get(ItemIds.SPLASH_POTION, 27, 10);
        splashpotion_poison2.setCustomName(mainAPI.symbol + getKit().getName() + mainAPI.empty + "Poison 2 Splash Potion");
        return new Item[] {
                steak,
                potion_speed2,
                splashpotion_poison2
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
        return 1000 * 60 * 60 * 48;
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
        if (getStatus(player).equals(StatusKit.LOCKED)) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("kits.locked"));
        } else {
            if (passTimer(player)) {
                if (canAddKit(player)) {
                    if (hasEnoughDollars(player)) {
                        player.getInventory().setArmorContents(getArmorContents());
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
}
