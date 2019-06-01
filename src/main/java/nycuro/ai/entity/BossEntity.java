package nycuro.ai.entity;

import cn.nukkit.item.Item;
import me.onebone.actaeon.entity.monster.Zombie;
import nycuro.API;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class BossEntity extends Zombie {

    public BossEntity() {
        super(API.getMainAPI().getServer().getDefaultLevel().getChunk(127 >> 4, 50 >> 4), API.getAiAPI().getBossNBT());
        this.setNameTag("§a» §cThe Boss §a«§r");
        this.setMaxHealth(100);
        this.setHealth(100F);
        this.setScale(2F);
        this.spawnToAll();
    }

    @Override
    public String getName() {
        return "BossEntity";
    }

    @Override
    public float getDamage() {
        return 15F;
    }

    @Override
    public Item[] getDrops() {
        return new Item[]{
                Item.get(Item.GOLDEN_APPLE, 0, 3),
                Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 1),
                Item.get(Item.OBSIDIAN, 0, 16),
                Item.get(Item.TNT, 0, 8)
        };
    }
}