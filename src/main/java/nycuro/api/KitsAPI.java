package nycuro.api;

import cn.nukkit.Player;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.Profile;
import nycuro.kits.*;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class KitsAPI {

    private static final Object2IntMap<String> kits = new Object2IntOpenHashMap<>();
    //private static final Int2DoubleMap cost = new Int2DoubleOpenHashMap();

    static {
        kits.put("EnchantedStarter", 1);
        kits.put("Sparrow", 2);
        kits.put("Knight", 3);
        kits.put("VIP", 4);
        kits.put("VIP+", 5);
        kits.put("Paladin", 6);
        kits.put("Guardian", 7);
        kits.put("MVP", 8);
        kits.put("MVP+", 9);

        //cost.put(500, kits.getInt(1));
    }

    /*private boolean isKitWithMoney(String[] argument) {
        return kits.getInt("EnchantedStarter") == 1;
    }*/

    public void sendKit(Player player, String[] argument) {
        //double moneyPlayer = EconomyAPI.getInstance().myMoney(player);
        //double price = cost.get(1);
        //double insuficient = price - moneyPlayer;
        long time = System.currentTimeMillis();
        Profile profile = Database.profile.get(player.getUniqueId());
        long cooldown = profile.getCooldown();
        long timeGone = time - cooldown;
        if (timeGone >= 24 * 60 * 60 * 1000 || timeGone == 0) {
            if (!player.hasPermission("core." + kits.getInt(argument[0]))) {
                API.getMessageAPI().sendCustomPermissionMessage(player);
            } else {
                switch (kits.getInt(argument[0])) {
                    case 1:
                        new EnchantedStarterKit(player);
                        return;
                    case 2:
                        new SparrowKit(player);
                        profile.setCooldown(time);
                        break;
                    case 3:
                        new KnightKit(player);
                        profile.setCooldown(time);
                        break;
                    case 4:
                        new VIPKit(player);
                        profile.setCooldown(time);
                        break;
                    case 5:
                        new VIPPlusKit(player);
                        profile.setCooldown(time);
                        break;
                    case 6:
                        new PaladinKit(player);
                        profile.setCooldown(time);
                        break;
                    case 7:
                        new GuardianKit(player);
                        profile.setCooldown(time);
                        break;
                    case 8:
                        new MVPKit(player);
                        profile.setCooldown(time);
                        break;
                    case 9:
                        new MVPPlusKit(player);
                        profile.setCooldown(time);
                        break;
                }
                API.getMessageAPI().sendReceiveKitMessage(player, argument[0]);
            }
        } else {
            API.getMessageAPI().sendCooldownMessage(player, timeGone);
        }
    }
}
