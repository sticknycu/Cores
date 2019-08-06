package nycuro.utils.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.utils.WarpUtils;
import nycuro.utils.commands.UtilsCommandManager;

import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class UtilsAPI extends API {

    public static WarpUtils warpUtils;
    private double cost = 0;

    public static WarpUtils getWarpUtilsAPI() {
        return warpUtils;
    }

    @Override
    public void registerCommands() {
        UtilsCommandManager.registerAll(getMainAPI());
    }

    private void repairItemHand(Player player) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());
        double moneyPlayer = profile.getDollars();
        cost = 500;
        double insufficient = cost - moneyPlayer;
        PlayerInventory playerInventory = player.getInventory();
        Item item = playerInventory.getItemInHand();
        if (moneyPlayer >= cost) {
            Database.profileSkyblock.get(player.getName()).setDollars(Database.profileSkyblock.get(player.getName()).getDollars() - cost);
            playerInventory.remove(item);
            item.setDamage(0);
            playerInventory.addItem(item);
            API.getMessageAPI().sendRepairItemMessage(player, item);
        } else if (moneyPlayer < cost) {
            API.getMessageAPI().sendUnsuficientMoneyMessage(player, insufficient);
        }
    }

    public void sendUtilsContents(Player player) {
        FormWindowSimple utilsMenu = new FormWindowSimple("Utils", "                       Hello!\n" +
                "               Welcome to Utilities!\n" +
                "   Select what you want to do from now.");
        utilsMenu.addButton(new ElementButton("Warps System", new ElementButtonImageData("url", "https://i.imgur.com/GuxFWI6.png")));
        utilsMenu.addButton(new ElementButton("Repair System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
        //utilsMenu.addButton(new ElementButton("Jobs System", new ElementButtonImageData("url", "https://i.imgur.com/3dNFsme.png")));
        utilsMenu.addButton(new ElementButton("Close"));
        //utilsMenu.addButton(new ElementButton("Events System", new ElementButtonImageData("url", "https://i.imgur.com/1yfKKEm.png")));
        player.showFormWindow(new ResponseFormWindow(utilsMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getWarpUtilsAPI().sendWarptOptionOfUtils(player);
                            return;
                        case 1:
                            repairItemHand(player);
                            return;
                        case 2:
                            break;
                    }
                }
            }
        }));
    }
}
