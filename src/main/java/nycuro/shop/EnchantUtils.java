package nycuro.shop;

import cn.nukkit.Player;
import cn.nukkit.player.Player;

import java.util.Arrays;
import java.util.List;

import static nycuro.api.API.shopAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class EnchantUtils {

    // TODO: Thrones is bugged and players can multiply items with them.

    public void sendHelmetOptionContents(Player player) {
        List<String> list = Arrays.asList("Aqua Affinity", "Blast Protection", "Fire Protection", "Projectile Protection", "Protection", "Respiration");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendChestplateOptionContents(Player player) {
        List<String> list = Arrays.asList("Blast Protection", "Fire Protection", "Mending", "Projectile Protection", "Protection", "Unbreaking");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendLeggingsOptionContents(Player player) {
        List<String> list = Arrays.asList("Blast Protection", "Fire Protection", "Projectile Protection", "Protection");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendBootsOptionContents(Player player) {
        List<String> list = Arrays.asList("Blast Protection", "Depth Strider", "Feather Falling", "Fire Protection", "Frost Walker", "Projectile Protection", "Protection");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendSwordOptionContents(Player player) {
        List<String> list = Arrays.asList("Bane of Arthropods", "Fire Aspect", "Knockback", "Looting", "Mending", "Sharpness", "Smite", "Unbreaking");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendPickaxeOptionContents(Player player) {
        List<String> list = Arrays.asList("Efficiency", "Fortune", "Mending", "Silk Touch", "Unbreaking");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendAxeOptionContents(Player player) {
        List<String> list = Arrays.asList("Bane of Arthropods", "Efficiency", "Fortune", "Sharpness", "Silk Touch", "Smite");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendShovelOptionContents(Player player) {
        List<String> list = Arrays.asList("Efficiency", "Fortune", "Silk Touch");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendBowOptionContents(Player player) {
        List<String> list = Arrays.asList("Flame", "Infinity", "Power", "Punch");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }

    public void sendFishingRodOptionContents(Player player) {
        List<String> list = Arrays.asList("Luck of the Sea", "Lure", "Mending", "Unbreaking");
        List<String> countList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> type = Arrays.asList("1", "2");
        shopAPI.sendFormWindowCustomWithEnchantArmorList(player, list, countList, type);
    }
}
