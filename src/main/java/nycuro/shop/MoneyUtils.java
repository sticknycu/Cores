package nycuro.shop;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

import java.util.Map;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MoneyUtils {

    private static final Object2IntMap<String> id = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<String> meta = new Object2IntOpenHashMap<>();
    private static final Object2DoubleMap<String> cost = new Object2DoubleOpenHashMap<>();
    private static final Object2IntMap<String> enchant = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<String> exp = new Object2IntOpenHashMap<>();

    static {
        id.put("Galeata cu Apa", Item.BUCKET);
        id.put("Water Bucket", Item.BUCKET);
        id.put("Galeata cu Lava", Item.BUCKET);
        id.put("Lava Bucket", Item.BUCKET);
        id.put("Blaze Rod", Item.BLAZE_ROD);
        id.put("Nether Wart", Item.NETHER_WART);
        id.put("Nether Star", Item.NETHER_STAR);
        id.put("Block of Quartz", Item.QUARTZ_BLOCK);
        id.put("Nether Quartz", Item.NETHER_QUARTZ);
        id.put("Nether Brick Block", Item.NETHER_BRICK_BLOCK);
        id.put("Nether Wart Block", Item.NETHER_WART_BLOCK);
        id.put("Red Nether Brick", Item.RED_NETHER_BRICK);
        id.put("Oak Wood", Item.WOOD);
        id.put("Spruce Wood", Item.WOOD);
        id.put("Birch Wood", Item.WOOD);
        id.put("Jungle Wood", Item.WOOD);
        id.put("Acacia Wood", Item.WOOD2);
        id.put("Dark Oak Wood", Item.WOOD2);
        id.put("Oak Wood Planks", Item.PLANKS);
        id.put("Spruce Wood Planks", Item.PLANKS);
        id.put("Birch Wood Planks", Item.PLANKS);
        id.put("Jungle Wood Planks", Item.PLANKS);
        id.put("Dark Oak Wood Planks", Item.PLANKS);
        id.put("Acacia Wood Planks", Item.PLANKS);
        id.put("Potato", Item.POTATO);
        id.put("Carrot", Item.CARROT);
        id.put("Wheat", Item.WHEAT);
        id.put("Cactus", Item.CACTUS);
        id.put("Melon", Item.MELON);
        id.put("Pumpkin", Item.PUMPKIN);
        id.put("Seeds", Item.SEEDS);
        id.put("Apple", Item.APPLE);
        id.put("Sugar Cane", Item.SUGAR_CANE);
        id.put("Cobblestone", Item.COBBLESTONE);
        id.put("Stone", Item.STONE);
        id.put("Grass", Item.GRASS);
        id.put("Dirt", Item.DIRT);
        id.put("Redstone", Item.REDSTONE);
        id.put("Coal", Item.COAL);
        id.put("Iron Ingot", Item.IRON_INGOT);
        id.put("Gold Ingot", Item.GOLD_INGOT);
        id.put("Diamond", Item.DIAMOND);
        id.put("Lapis Lazuli", Item.DYE);
        id.put("Emerald", Item.EMERALD);
        id.put("Zombie SpawnEgg", Item.SPAWN_EGG);
        id.put("Skeleton SpawnEgg", Item.SPAWN_EGG);
        id.put("Creeper SpawnEgg", Item.SPAWN_EGG);
        id.put("Pig SpawnEgg", Item.SPAWN_EGG);
        id.put("Sheep SpawnEgg", Item.SPAWN_EGG);
        id.put("Chicken SpawnEgg", Item.SPAWN_EGG);
        id.put("Cow SpawnEgg", Item.SPAWN_EGG);
        id.put("Spawner", Item.MONSTER_SPAWNER);

        id.put("Enchanted Golden Apple", Item.GOLDEN_APPLE_ENCHANTED);
        id.put("TNT", Item.TNT);
        id.put("Obsidian", Item.OBSIDIAN);

        id.put("Glass", Item.GLASS);
        id.put("White Stained Glass", Item.STAINED_GLASS);
        id.put("Orange Stained Glass", Item.STAINED_GLASS);
        id.put("Magenta Stained Glass", Item.STAINED_GLASS);
        id.put("Light Blue Stained Glass", Item.STAINED_GLASS);
        id.put("Yellow Stained Glass", Item.STAINED_GLASS);
        id.put("Lime Stained Glass", Item.STAINED_GLASS);
        id.put("Pink Stained Glass", Item.STAINED_GLASS);
        id.put("Gray Stained Glass", Item.STAINED_GLASS);
        id.put("Light Gray Stained Glass", Item.STAINED_GLASS);
        id.put("Cyan Stained Glass", Item.STAINED_GLASS);
        id.put("Purple Stained Glass", Item.STAINED_GLASS);
        id.put("Blue Stained Glass", Item.STAINED_GLASS);
        id.put("Brown Stained Glass", Item.STAINED_GLASS);
        id.put("Green Stained Glass", Item.STAINED_GLASS);
        id.put("Red Stained Glass", Item.STAINED_GLASS);
        id.put("Black Stained Glass", Item.STAINED_GLASS);

        id.put("Glass Pane", Item.GLASS_PANE);
        id.put("White Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Orange Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Magenta Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Light Blue Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Yellow Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Lime Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Pink Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Gray Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Light Gray Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Cyan Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Purple Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Blue Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Brown Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Green Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Red Stained Glass Pane", Item.STAINED_GLASS_PANE);
        id.put("Black Stained Glass Pane", Item.STAINED_GLASS_PANE);

        id.put("Sand", Item.SAND);

        meta.put("Galeata cu Apa", 8);
        meta.put("Water Bucket", 8);
        meta.put("Galeata cu Lava", 10);
        meta.put("Lava Bucket", 10);
        meta.put("Blaze Rod", 0);
        meta.put("Nether Wart", 0);
        meta.put("Nether Star", 0);
        meta.put("Block of Quartz", 0);
        meta.put("Nether Quartz", 0);
        meta.put("Nether Brick Block", 0);
        meta.put("Nether Wart Block", 0);
        meta.put("Red Nether Brick", 0);
        meta.put("Oak Wood", 0);
        meta.put("Spruce Wood", 1);
        meta.put("Birch Wood", 2);
        meta.put("Jungle Wood", 3);
        meta.put("Acacia Wood", 0);
        meta.put("Dark Oak Wood", 1);
        meta.put("Oak Wood Planks", 0);
        meta.put("Spruce Wood Planks", 1);
        meta.put("Birch Wood Planks", 2);
        meta.put("Jungle Wood Planks", 3);
        meta.put("Dark Oak Wood Planks", 4);
        meta.put("Acacia Wood Planks", 5);
        meta.put("Potato", 0);
        meta.put("Carrot", 0);
        meta.put("Wheat", 0);
        meta.put("Cactus", 0);
        meta.put("Melon", 0);
        meta.put("Pumpkin", 0);
        meta.put("Seeds", 0);
        meta.put("Apple", 0);
        meta.put("Sugar Cane", 0);
        meta.put("Cobblestone", 0);
        meta.put("Stone", 0);
        meta.put("Grass", 0);
        meta.put("Dirt", 0);
        meta.put("Redstone", 0);
        meta.put("Iron Ingot", 0);
        meta.put("Gold Ingot", 0);
        meta.put("Diamond", 0);
        meta.put("Lapis Lazuli", 4);
        meta.put("Emerald", 0);
        meta.put("Chicken SpawnEgg", 10);
        meta.put("Cow SpawnEgg", 11);
        meta.put("Pig SpawnEgg", 12);
        meta.put("Sheep SpawnEgg", 13);
        meta.put("Zombie SpawnEgg", 32);
        meta.put("Creeper SpawnEgg", 33);
        meta.put("Skeleton SpawnEgg", 34);
        meta.put("Spawner", 0);
        meta.put("Enchanted Golden Apple", 0);
        meta.put("TNT", 0);
        meta.put("Obsidian", 0);

        meta.put("Glass", 0);
        meta.put("White Stained Glass", 0);
        meta.put("Orange Stained Glass", 1);
        meta.put("Magenta Stained Glass", 2);
        meta.put("Light Blue Stained Glass", 3);
        meta.put("Yellow Stained Glass", 4);
        meta.put("Lime Stained Glass", 5);
        meta.put("Pink Stained Glass", 6);
        meta.put("Gray Stained Glass", 7);
        meta.put("Light Gray Stained Glass", 8);
        meta.put("Cyan Stained Glass", 9);
        meta.put("Purple Stained Glass", 10);
        meta.put("Blue Stained Glass", 11);
        meta.put("Brown Stained Glass", 12);
        meta.put("Green Stained Glass", 13);
        meta.put("Red Stained Glass", 14);
        meta.put("Black Stained Glass", 15);

        meta.put("Glass Pane", 0);
        meta.put("White Stained Glass Pane", 0);
        meta.put("Orange Stained Glass Pane", 1);
        meta.put("Magenta Stained Glass Pane", 2);
        meta.put("Light Blue Stained Glass Pane", 3);
        meta.put("Yellow Stained Glass Pane", 4);
        meta.put("Lime Stained Glass Pane", 5);
        meta.put("Pink Stained Glass Pane", 6);
        meta.put("Gray Stained Glass Pane", 7);
        meta.put("Light Gray Stained Glass Pane", 8);
        meta.put("Cyan Stained Glass Pane", 9);
        meta.put("Purple Stained Glass Pane", 10);
        meta.put("Blue Stained Glass Pane", 11);
        meta.put("Brown Stained Glass Pane", 12);
        meta.put("Green Stained Glass Pane", 13);
        meta.put("Red Stained Glass Pane", 14);
        meta.put("Black Stained Glass Pane", 15);

        meta.put("Sand", 0);

        cost.put("Galeata cu Apa", 175);
        cost.put("Water Bucket", 175);
        cost.put("Galeata cu Lava", 175);
        cost.put("Lava Bucket", 175);
        cost.put("Blaze Rod", 15.625);
        cost.put("Nether Wart", 3.90625);
        cost.put("Nether Star", 1000);
        cost.put("Block of Quartz", 15.625);
        cost.put("Nether Quartz", 3.90625);
        cost.put("Nether Brick Block", 15.625);
        cost.put("Nether Wart Block", 15.625);
        cost.put("Red Nether Brick", 15.625);
        cost.put("Oak Wood", 3.90625 * 2);
        cost.put("Spruce Wood", 3.90625 * 2);
        cost.put("Birch Wood", 3.90625 * 2);
        cost.put("Jungle Wood", 3.90625 * 2);
        cost.put("Acacia Wood", 3.90625 * 2);
        cost.put("Dark Oak Wood", 3.90625 * 2);
        cost.put("Oak Wood Planks", 3.90625 / 2);
        cost.put("Spruce Wood Planks", 3.90625 / 2);
        cost.put("Birch Wood Planks", 3.90625 / 2);
        cost.put("Jungle Wood Planks", 3.90625 / 2);
        cost.put("Dark Oak Wood Planks", 3.90625 / 2);
        cost.put("Acacia Wood Planks", 3.90625 / 2);
        cost.put("Potato", 12.5);
        cost.put("Carrot", 12.5);
        cost.put("Wheat", 12.5);
        cost.put("Cactus", 12.5);
        cost.put("Melon", 12.5);
        cost.put("Pumpkin", 50);
        cost.put("Seeds", 12.5);
        cost.put("Apple", 12.5);
        cost.put("Sugar Cane", 12.5);
        cost.put("Cobblestone", 3.90625 * 2);
        cost.put("Stone", 3.90625 * 3);
        cost.put("Grass", 3.90625 * 2);
        cost.put("Dirt", 3.90625 / 2);
        cost.put("Redstone", 6.25);
        cost.put("Coal", 6.25);
        cost.put("Iron Ingot", 50);
        cost.put("Gold Ingot", 75);
        cost.put("Diamond", 350);
        cost.put("Lapis Lazuli", 37.5);
        cost.put("Emerald", 175);
        cost.put("Zombie SpawnEgg", 25000);
        cost.put("Skeleton SpawnEgg", 25000);
        cost.put("Creeper SpawnEgg", 25000);
        cost.put("Pig SpawnEgg", 50000);
        cost.put("Sheep SpawnEgg", 50000);
        cost.put("Chicken SpawnEgg", 50000);
        cost.put("Cow SpawnEgg", 50000);
        cost.put("Spawner", 50000);
        cost.put("Enchanted Golden Apple", 500);
        cost.put("TNT", 100);
        cost.put("Obsidian", 50);

        cost.put("Aqua Affinity", 1000);
        cost.put("Bane of Arthropods", 1500);
        cost.put("Blast Protection", 1500);
        cost.put("Depth Strider", 1500);
        cost.put("Efficiency", 3500);
        cost.put("Feather Falling", 2500);
        cost.put("Fire Aspect", 1500);
        cost.put("Fire Protection", 1500);
        cost.put("Flame", 5000);
        cost.put("Fortune", 3500);
        cost.put("Frost Walker", 1000);
        cost.put("Infinity", 5000);
        cost.put("Knockback", 3500);
        cost.put("Looting", 4000);
        cost.put("Luck of the Sea", 1000);
        cost.put("Lure", 3000);
        cost.put("Mending", -1);
        cost.put("Power", 3500);
        cost.put("Projectile Protection", 1500);
        cost.put("Protection", 3000);
        cost.put("Punch", 5000);
        cost.put("Respiration", 1500);
        cost.put("Sharpness", 3500);
        cost.put("Silk Touch", 5000);
        cost.put("Smite", 3000);
        cost.put("Unbreaking", 3500);

        cost.put("Glass", 3.90625);
        cost.put("White Stained Glass", 3.90625);
        cost.put("Orange Stained Glass", 3.90625);
        cost.put("Magenta Stained Glass", 3.90625);
        cost.put("Light Blue Stained Glass", 3.90625);
        cost.put("Yellow Stained Glass", 3.90625);
        cost.put("Lime Stained Glass", 3.90625);
        cost.put("Pink Stained Glass", 3.90625);
        cost.put("Gray Stained Glass", 3.90625);
        cost.put("Light Gray Stained Glass", 3.90625);
        cost.put("Cyan Stained Glass", 3.90625);
        cost.put("Purple Stained Glass", 3.90625);
        cost.put("Blue Stained Glass", 3.90625);
        cost.put("Brown Stained Glass", 3.90625);
        cost.put("Green Stained Glass", 3.90625);
        cost.put("Red Stained Glass", 3.90625);
        cost.put("Black Stained Glass", 3.90625);

        cost.put("Glass Pane", 3.90625);
        cost.put("White Stained Glass Pane", 3.90625);
        cost.put("Orange Stained Glass Pane", 3.90625);
        cost.put("Magenta Stained Glass Pane", 3.90625);
        cost.put("Light Blue Stained Glass Pane", 3.90625);
        cost.put("Yellow Stained Glass Pane", 3.90625);
        cost.put("Lime Stained Glass Pane", 3.90625);
        cost.put("Pink Stained Glass Pane", 3.90625);
        cost.put("Gray Stained Glass Pane", 3.90625);
        cost.put("Light Gray Stained Glass Pane", 3.90625);
        cost.put("Cyan Stained Glass Pane", 3.90625);
        cost.put("Purple Stained Glass Pane", 3.90625);
        cost.put("Blue Stained Glass Pane", 3.90625);
        cost.put("Brown Stained Glass Pane", 3.90625);
        cost.put("Green Stained Glass Pane", 3.90625);
        cost.put("Red Stained Glass Pane", 3.90625);
        cost.put("Black Stained Glass Pane", 3.90625);

        cost.put("Sand", 3.90625);

        exp.put("Aqua Affinity", 5);
        exp.put("Bane of Arthropods", 6);
        exp.put("Blast Protection", 6);
        exp.put("Depth Strider", 5);
        exp.put("Efficiency", 10);
        exp.put("Feather Falling", 6);
        exp.put("Fire Aspect", 20);
        exp.put("Fire Protection", 6);
        exp.put("Flame", 20);
        exp.put("Fortune", 12);
        exp.put("Frost Walker", 5);
        exp.put("Infinity", 20);
        exp.put("Knockback", 12);
        exp.put("Looting", 15);
        exp.put("Luck of the Sea", 5);
        exp.put("Lure", 7);
        exp.put("Mending", -1);
        exp.put("Power", 12);
        exp.put("Projectile Protection", 6);
        exp.put("Protection", 15);
        exp.put("Punch", 17);
        exp.put("Respiration", 5);
        exp.put("Sharpness", 15);
        exp.put("Silk Touch", 10);
        exp.put("Smite", 15);
        exp.put("Unbreaking", 15);

        enchant.put("Aqua Affinity", Enchantment.ID_WATER_WORKER);
        enchant.put("Bane of Arthropods", Enchantment.ID_DAMAGE_ARTHROPODS);
        enchant.put("Blast Protection", Enchantment.ID_PROTECTION_EXPLOSION);
        enchant.put("Depth Strider", Enchantment.ID_WATER_WALKER);
        enchant.put("Efficiency", Enchantment.ID_EFFICIENCY);
        enchant.put("Feather Falling", Enchantment.ID_PROTECTION_FALL);
        enchant.put("Fire Aspect", Enchantment.ID_FIRE_ASPECT);
        enchant.put("Fire Protection", Enchantment.ID_PROTECTION_FIRE);
        enchant.put("Flame", Enchantment.ID_BOW_FLAME);
        enchant.put("Fortune", Enchantment.ID_FORTUNE_DIGGING);
        enchant.put("Frost Walker", Enchantment.ID_WATER_WALKER);
        enchant.put("Infinity", Enchantment.ID_BOW_INFINITY);
        enchant.put("Knockback", Enchantment.ID_KNOCKBACK);
        enchant.put("Looting", Enchantment.ID_LOOTING);
        enchant.put("Luck of the Sea", Enchantment.ID_FORTUNE_FISHING);
        enchant.put("Lure", Enchantment.ID_LURE);
        enchant.put("Mending", -1);
        enchant.put("Power", Enchantment.ID_BOW_POWER);
        enchant.put("Projectile Protection", Enchantment.ID_PROTECTION_PROJECTILE);
        enchant.put("Protection", Enchantment.ID_PROTECTION_ALL);
        enchant.put("Punch", Enchantment.ID_BOW_KNOCKBACK);
        enchant.put("Respiration", Enchantment.ID_WATER_BREATHING);
        enchant.put("Sharpness", Enchantment.ID_DAMAGE_ALL);
        enchant.put("Silk Touch", Enchantment.ID_SILK_TOUCH);
        enchant.put("Smite", Enchantment.ID_DAMAGE_SMITE);
        enchant.put("Unbreaking", Enchantment.ID_DURABILITY);
    }

    public void buyAction(Map<Integer, Object> response, Player player, String firstDropDownType) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());
        double moneyCount = profile.getDollars();
        if (response.get(1) == null) return;
        int itemId = id.getInt(firstDropDownType);
        int itemMeta = meta.getInt(firstDropDownType);
        int count = Integer.parseInt(response.get(1).toString());
        Item item = Item.get(itemId, itemMeta, count);
        double priceFinal = cost.getDouble(firstDropDownType) * count;
        double needed = priceFinal - moneyCount;
        if (!response.isEmpty()) {
            if (moneyCount >= priceFinal) {
                profile.setDollars(profile.getDollars() - priceFinal);
                player.getInventory().addItem(item);
                API.getMessageAPI().sendBuyItemMessage(player, item, priceFinal);
            } else if (moneyCount < priceFinal) {
                API.getMessageAPI().sendUnsuficientMoneyMessage(player, needed);
            }
        }
    }

    public void sellAction(Map<Integer, Object> response, Player player, String firstDropDownType) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());
        if (response.get(1) == null) return;
        int itemId = id.getInt(firstDropDownType);
        int itemMeta = meta.getInt(firstDropDownType);
        int count = Integer.parseInt(response.get(1).toString());
        Item item = Item.get(itemId, itemMeta, count);
        double priceFinal = cost.getDouble(firstDropDownType) * count;
        if (!response.isEmpty()) {
            if (player.getGamemode() != Player.SURVIVAL) {
                API.getMessageAPI().sendGamemodeSellExceptionMessage(player);
                return;
            }
            if (player.getInventory().contains(item)) {
                if (item.getDamage() == itemMeta) {
                    if (item.getCount() == count) {
                        profile.setDollars(profile.getDollars() + priceFinal);
                        player.getInventory().removeItem(item);
                        API.getMessageAPI().sendSellItemMessage(player, item, priceFinal);
                    } else if (item.getCount() != count) {
                        API.getMessageAPI().sendInsufficientCountMessage(player);
                    }
                } else if (item.getCount() != itemMeta) {
                    API.getMessageAPI().sendBreakedItemMessage(player);
                }
            } else if (!player.getInventory().contains(item)) {
                API.getMessageAPI().sendUnsuficientItemsMessage(player);
            }
        }
    }

    public void enchantBuyAction(Map<Integer, Object> response, Player player, String firstDropDownType) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());
        if (response.get(1) == null) return;
        if (response.get(2) == null) return;
        double moneyCount = profile.getDollars();
        int experiencePlayer = player.getExperienceLevel();
        int enchantId = enchant.getInt(firstDropDownType);
        if (enchantId == -1) {
            API.getMessageAPI().sendExceptionEnchantMessage(player);
            return;
        }
        int enchantLevel = Integer.parseInt(response.get(1).toString());
        int typePay = Integer.parseInt(response.get(2).toString());
        Enchantment enchantment = Enchantment.get(enchantId);
        Item item = player.getInventory().getItemInHand();
        if (!enchantment.canEnchant(item)) {
            API.getMessageAPI().sendExceptionEnchantInvalidMessage(player);
            return;
        }
        int index = player.getInventory().getHeldItemIndex();
        if (item.getId() == 0) {
            API.getMessageAPI().sendExceptionEnchantItemHandMessage(player);
            return;
        }
        double priceMoney = cost.getDouble(firstDropDownType);
        if (priceMoney == -1) return;
        double priceFinalMoney = priceMoney * enchantLevel;
        double neededMoney = priceFinalMoney - moneyCount;
        int priceLevel = exp.getInt(firstDropDownType);
        if (priceLevel == -1) return;
        int priceFinalExperience = priceLevel * enchantLevel;
        int neededExperience = priceFinalExperience - experiencePlayer;
        if (!response.isEmpty()) {
            if (typePay == 1) {
                if (enchantLevel <= enchantment.getMaxLevel()) {
                    if (moneyCount >= priceFinalMoney) {
                        profile.setDollars(profile.getDollars() - priceFinalMoney);
                        if (item.hasEnchantments()) {
                            Enchantment[] enchantments = item.getEnchantments();
                            item.addEnchantment(enchantments);
                        }
                        item.addEnchantment(enchantment
                                .setLevel(enchantLevel));
                        player.getInventory().setItem(index, item);
                        API.getMessageAPI().sendEnchantItemMessage(player, item, priceFinalMoney);
                    } else if (moneyCount < priceFinalMoney) {
                        API.getMessageAPI().sendUnsuficientMoneyMessage(player, neededMoney);
                    }
                } else if (enchantLevel > enchantment.getMaxLevel()) {
                    API.getMessageAPI().sendExceptionLevelEnchantMessage(player);
                }
            } else if (typePay == 2) {
                if (experiencePlayer < 40) {
                    API.getMessageAPI().sendExceptionLevelEnchantTypeMessage(player);
                    return;
                }
                if (enchantLevel <= enchantment.getMaxLevel()) {
                    if (experiencePlayer >= priceFinalExperience) {
                        player.setExperience(0, experiencePlayer - priceFinalExperience);
                        if (item.hasEnchantments()) {
                            Enchantment[] enchantments = item.getEnchantments();
                            item.addEnchantment(enchantments);
                        }
                        item.addEnchantment(enchantment
                                .setLevel(enchantLevel));
                        player.getInventory().setItem(index, item);
                        API.getMessageAPI().sendEnchantItemExperienceMessage(player, item, priceFinalExperience);
                    } else if (experiencePlayer < priceFinalExperience) {
                        API.getMessageAPI().sendUnsuficientExperienceMessage(player, neededExperience);
                    }
                } else if (enchantLevel > enchantment.getMaxLevel()) {
                    API.getMessageAPI().sendExceptionLevelEnchantMessage(player);
                }
            }
        }
    }
}
