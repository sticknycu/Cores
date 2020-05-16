package nycuro.utils.api;

import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.utils.commands.UtilsCommandManager;

import java.util.Map;
import java.util.function.Consumer;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class UtilsAPI {

    private double cost = 0;

    public void registerCommands() {
        UtilsCommandManager.registerAll(mainAPI);
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
            item.setMeta(0);
            playerInventory.addItem(item);
            player.sendMessage(messageAPI.messagesObject.translateMessage("repair.item"));
        } else if (moneyPlayer < cost) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("generic.money.enough",
                    mainAPI.emptyNoSpace + moneyPlayer, mainAPI.emptyNoSpace + insufficient));
        }
    }

    public void sendUtilsContents(Player player) {
        /*FormWindowSimple utilsMenu = new FormWindowSimple("Utils", "                       Hello!\n" +
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
        }));*/
    }
}
