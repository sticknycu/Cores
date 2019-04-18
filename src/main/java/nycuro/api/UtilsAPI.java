package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileHub;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.utils.MechanicUtils;
import nycuro.utils.RandomTPUtils;
import nycuro.utils.WarpUtils;

import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class UtilsAPI {

    public static RandomTPUtils randomTPUtils;
    public static WarpUtils warpUtils;
    public static MechanicUtils mechanicUtils;
    private double cost = 0;

    public static RandomTPUtils getRandomTPUtilsAPI() {
        return randomTPUtils;
    }

    public static WarpUtils getWarpUtilsAPI() {
        return warpUtils;
    }

    public static MechanicUtils getMechanicUtilsAPI() {
        return mechanicUtils;
    }

    private void teleportRandomPlayer(Player player) {
        ProfileFactions profile = Database.profileFactions.get(player.getUniqueId());
        double moneyPlayer = profile.getDollars();
        cost = 500;
        double insuficient = cost - moneyPlayer;
        if (moneyPlayer >= cost) {
            Database.profileFactions.get(player.getUniqueId()).setDollars(Database.profileFactions.get(player.getUniqueId()).getDollars() - cost);
            getRandomTPUtilsAPI().getSafeLocationSpawn(player, 5000);
        } else if (moneyPlayer < cost) {
            API.getMessageAPI().sendUnsuficientMoneyMessage(player, insuficient);
        }
    }

    private void repairItemHand(Player player) {
        ProfileFactions profile = Database.profileFactions.get(player.getUniqueId());
        double moneyPlayer = profile.getDollars();
        cost = 500;
        double insufficient = cost - moneyPlayer;
        PlayerInventory playerInventory = player.getInventory();
        Item item = playerInventory.getItemInHand();
        if (moneyPlayer >= cost) {
            Database.profileFactions.get(player.getUniqueId()).setDollars(Database.profileFactions.get(player.getUniqueId()).getDollars() - cost);
            playerInventory.remove(item);
            item.setDamage(0);
            playerInventory.addItem(item);
            API.getMessageAPI().sendRepairItemMessage(player, item);
        } else if (moneyPlayer < cost) {
            API.getMessageAPI().sendUnsuficientMoneyMessage(player, insufficient);
        }
    }

    public void sendUtilsContents(Player player) {
        ProfileHub profile = Database.profileHub.get(player.getUniqueId());
        int lang = profile.getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple utilsMenu = new FormWindowSimple("Utils", "                       Hello!\n" +
                        "               Welcome to Utilities!\n" +
                        "   Select what you want to do from now.");
                utilsMenu.addButton(new ElementButton("RandomTP System", new ElementButtonImageData("url", "https://i.imgur.com/UdZm6QB.png")));
                utilsMenu.addButton(new ElementButton("Warps System", new ElementButtonImageData("url", "https://i.imgur.com/GuxFWI6.png")));
                utilsMenu.addButton(new ElementButton("Repair System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
                utilsMenu.addButton(new ElementButton("Jobs System", new ElementButtonImageData("url", "https://i.imgur.com/3dNFsme.png")));
                utilsMenu.addButton(new ElementButton("Close"));
                //utilsMenu.addButton(new ElementButton("Events System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
                player.showFormWindow(new ResponseFormWindow(utilsMenu, new Consumer<Map<Integer, Object>>() {
                    @Override
                    public void accept(Map<Integer, Object> response) {
                        if (!response.isEmpty()) {
                            switch (response.entrySet().iterator().next().getKey()) {
                                case 0:
                                    teleportRandomPlayer(player);
                                    return;
                                case 1:
                                    getWarpUtilsAPI().sendWarptOptionOfUtils(player);
                                    return;
                                case 2:
                                    repairItemHand(player);
                                    return;
                                case 3:
                                    API.getJobsAPI().getJob(player);
                                    break;
                                case 4:
                                    break;
                            }
                        }
                    }
                }));
                break;
            case 1:
                utilsMenu = new FormWindowSimple("Utils", "                       Salut!\n" +
                        "             Bine ai venit la Utilitati!\n" +
                        "       Alege ce doresti sa faci de acum.");
                utilsMenu.addButton(new ElementButton("RandomTP System", new ElementButtonImageData("url", "https://i.imgur.com/UdZm6QB.png")));
                utilsMenu.addButton(new ElementButton("Warps System", new ElementButtonImageData("url", "https://i.imgur.com/GuxFWI6.png")));
                utilsMenu.addButton(new ElementButton("Repair System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
                utilsMenu.addButton(new ElementButton("Jobs System", new ElementButtonImageData("url", "https://i.imgur.com/3dNFsme.png")));
                utilsMenu.addButton(new ElementButton("Close"));
                //utilsMenu.addButton(new ElementButton("Events System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
                player.showFormWindow(new ResponseFormWindow(utilsMenu, new Consumer<Map<Integer, Object>>() {
                    @Override
                    public void accept(Map<Integer, Object> response) {
                        if (!response.isEmpty()) {
                            switch (response.entrySet().iterator().next().getKey()) {
                                case 0:
                                    teleportRandomPlayer(player);
                                    return;
                                case 1:
                                    getWarpUtilsAPI().sendWarptOptionOfUtils(player);
                                    return;
                                case 2:
                                    repairItemHand(player);
                                    return;
                                case 3:
                                    API.getJobsAPI().getJob(player);
                                    break;
                                case 4:
                                    break;
                            }
                        }
                    }
                }));
                break;
        }
    }
}
