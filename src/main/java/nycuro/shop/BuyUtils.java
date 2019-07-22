package nycuro.shop;

import cn.nukkit.Player;
import nycuro.API;

import java.util.Arrays;
import java.util.List;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class BuyUtils {

    public void sendWaterAndLavaOptionContents(Player player) {
        List<String> list = Arrays.asList("Water Bucket", "Lava Bucket");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendNetherOptionContents(Player player) {
        List<String> list = Arrays.asList("Blaze Rod", "Nether Wart", "Nether Star", "Block of Quartz", "Nether Quartz", "Nether Brick Block", "Nether Wart Block", "Red Nether Brick");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendWoodOptionContents(Player player) {
        List<String> list = Arrays.asList("Oak Wood", "Birch Wood", "Spruce Wood", "Jungle Wood", "Dark Oak Wood", "Acacia Wood", "Oak Wood Planks", "Birch Wood Planks", "Spruce Wood Planks", "Jungle Wood Planks", "Dark Oak Wood Planks", "Acacia Wood Planks");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendFoodOptionContents(Player player) {
        List<String> list = Arrays.asList("Potato", "Carrot", "Wheat", "Cactus", "Melon", "Pumpkin", "Seeds", "Apple", "Sugar Cane");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendOresOptionContents(Player player) {
        List<String> list = Arrays.asList("Cobblestone", "Stone", "Grass", "Dirt", "Redstone", "Coal", "Iron Ingot", "Gold Ingot", "Diamond", "Lapis Lazuli", "Emerald");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendNormalGlassOptionContents(Player player) {
        List<String> list = Arrays.asList("Glass", "White Stained Glass", "Orange Stained Glass", "Magenta Stained Glass",
                "Light Blue Stained Glass", "Yellow Stained Glass", "Lime Stained Glass", "Pink Stained Glass",
                "Gray Stained Glass", "Light Gray Stained Glass", "Cyan Stained Glass", "Purple Stained Glass",
                "Blue Stained Glass", "Brown Stained Glass", "Green Stained Glass", "Red Stained Glass", "Black Stained Glass");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendGlassPaneOptionContents(Player player) {
        List<String> list = Arrays.asList("Glass Pane", "White Stained Glass Pane", "Orange Stained Glass Pane", "Magenta Stained Glass Pane",
                "Light Blue Stained Glass Pane", "Yellow Stained Glass Pane", "Lime Stained Glass Pane", "Pink Stained Glass Pane",
                "Gray Stained Glass Pane", "Light Gray Stained Glass Pane", "Cyan Stained Glass Pane", "Purple Stained Glass Pane",
                "Blue Stained Glass Pane", "Brown Stained Glass Pane", "Green Stained Glass Pane", "Red Stained Glass Pane", "Black Stained Glass Pane");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendSpawnersOptionContents(Player player) {
        List<String> list = Arrays.asList("Zombie SpawnEgg", "Skeleton SpawnEgg", "Creeper SpawnEgg", "Pig SpawnEgg", "Sheep SpawnEgg", "Chicken SpawnEgg", "Cow SpawnEgg", "Spawner");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendSpecificsOptionContents(Player player) {
        List<String> list = Arrays.asList("Enchanted Golden Apple", "TNT", "Obsidian");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }

    public void sendOthersOptionContents(Player player) {
        List<String> list = Arrays.asList("Sand");
        List<String> countList = Arrays.asList("1", "8", "16", "32", "64");
        API.getShopAPI().sendFormWindowCustomWithShopList(player, list, countList);
    }
}
