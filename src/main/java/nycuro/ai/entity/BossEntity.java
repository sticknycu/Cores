package nycuro.ai.entity;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import me.onebone.actaeon.entity.monster.Zombie;

import static nycuro.api.API.aiAPI;
import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class BossEntity extends Zombie {

    public BossEntity() {
        super(mainAPI.getServer().getDefaultLevel().getChunk(1139 >> 4, 1133 >> 4), aiAPI.getBossNBT());
        this.setNameTag("§a» §cThe Boss §a«§r");
        this.setMaxHealth(100);
        this.setHealth(100F);
        this.setScale(2F);
        this.namedTag.putBoolean("coreBOSS", true);
        this.spawnToAll();
    }

    @Override
    public String getName() {
        return "The Boss";
    }

    @Override
    public float getDamage() {
        return 15F;
    }

    @Override
    public Item[] getDrops() {
        Item sword = new Item(Item.DIAMOND_SWORD);
        sword.setCustomName("Boss Sword");
        sword.addEnchantment(Enchantment.get(Enchantment.ID_DAMAGE_ALL).setLevel(2));
        sword.addEnchantment(Enchantment.get(Enchantment.ID_KNOCKBACK).setLevel(2));
        return new Item[] {
                Item.get(Item.GOLDEN_APPLE, 0, 5),
                Item.get(Item.OBSIDIAN, 0, 64),
                Item.get(Item.TNT, 0, 12),
                Item.get(Item.DIAMOND_BLOCK, 0, 3),
                sword
        };
    }
}