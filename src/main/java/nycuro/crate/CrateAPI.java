package nycuro.crate;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.DyeColor;
import nycuro.API;
import nycuro.crate.item.EntityFirework;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class CrateAPI {

    public void getChange(Player player, PlayerInventory playerInventory, int number) {
        Enchantment damage = Enchantment.getEnchantment(Enchantment.ID_DAMAGE_ALL)
                .setLevel(1);
        Enchantment durability = Enchantment.getEnchantment(Enchantment.ID_DURABILITY)
                .setLevel(1);
        Enchantment efficiency = Enchantment.getEnchantment(Enchantment.ID_EFFICIENCY)
                .setLevel(1);
        Item diamondSword = Item.get(Item.DIAMOND_SWORD);
        Item diamondPickaxe = Item.get(Item.DIAMOND_PICKAXE);
        Item[] items = new Item[0];
        if (number >= 1) {
            if (number <= 10) {
                items = new Item[]{
                        Item.get(Item.TNT, 0, 8),
                        Item.get(Item.GOLDEN_APPLE, 0, 1),
                        Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 1),
                        Item.get(Item.OBSIDIAN, 0, 12)
                };
            }
            if (number > 10) {
                if (number <= 20) {
                    items = new Item[]{
                            Item.get(Item.TNT, 0, 5),
                            Item.get(Item.GOLDEN_APPLE, 0, 3),
                            Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 1),
                            Item.get(Item.OBSIDIAN, 0, 16)
                    };
                }
            }
            if (number > 20) {
                if (number <= 30) {
                    items = new Item[]{
                            Item.get(Item.TNT, 0, 8),
                            Item.get(Item.GOLDEN_APPLE, 0, 1),
                            Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 1),
                            Item.get(Item.OBSIDIAN, 0, 12)
                    };
                }
            }
            if (number > 30) {
                if (number <= 50) {
                    items = new Item[]{
                            Item.get(Item.TNT, 0, 8),
                            Item.get(Item.GOLDEN_APPLE, 0, 1),
                            Item.get(Item.GOLDEN_APPLE_ENCHANTED, 0, 1),
                            Item.get(Item.OBSIDIAN, 0, 12)
                    };
                }
            }
            if (number > 50) {
                if (number <= 60) {
                    diamondSword.addEnchantment(damage);
                    diamondPickaxe.addEnchantment(durability);
                    diamondPickaxe.addEnchantment(efficiency);
                    items = new Item[0];
                }
            }
            if (number > 60) {
                if (number <= 70) {
                    diamondSword.addEnchantment(damage);
                    diamondPickaxe.addEnchantment(durability);
                    diamondPickaxe.addEnchantment(efficiency);
                    items = new Item[0];
                }
            }
            if (number > 70) {
                if (number <= 80) {
                    diamondSword.addEnchantment(damage);
                    diamondPickaxe.addEnchantment(durability);
                    diamondPickaxe.addEnchantment(efficiency);
                    items = new Item[0];
                }
            }
        }
        playerInventory.addItem(items);
    }

    public void addExplosion(Block block) {
        ItemFirework itemFirework = new ItemFirework();
        ItemFirework.FireworkExplosion fireworkExplosion = new ItemFirework.FireworkExplosion();
        fireworkExplosion.type(ItemFirework.FireworkExplosion.ExplosionType.LARGE_BALL);
        fireworkExplosion.addColor(DyeColor.LIGHT_BLUE);
        fireworkExplosion.addFade(DyeColor.YELLOW);
        itemFirework.clearExplosions();
        itemFirework.addExplosion(fireworkExplosion);
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", block.getX() + 0.5))
                        .add(new DoubleTag("", block.getY()))
                        .add(new DoubleTag("", block.getZ() + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(itemFirework));
        EntityFirework entityFirework = new EntityFirework(API.getMainAPI().getServer().getDefaultLevel().getChunk((int) block.x, (int) block.z), nbt);
        entityFirework.spawnToAll();
    }
}
