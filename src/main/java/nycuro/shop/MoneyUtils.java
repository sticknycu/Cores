package nycuro.shop;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import lombok.Getter;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.utils.JSONParser;
import nycuro.utils.objects.ShopObject;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 * modific ceva ca sa dea update gitu handicapat
 */
public class MoneyUtils {

    @Getter
    private List<ShopObject> shopItems;

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void init() {
        try {
            File file = new File(mainAPI.getDataFolder() + "/shop/shop.json");
            API.log("Checking for existance of shop list file... ");
            if (file.exists()) {
                String jsonData = readFile(mainAPI.getDataFolder() + "/shop/shop.json", Charset.defaultCharset());
                API.log("Setting shop items...");

                JSONParser<ShopObject[]> shopObjectJSONParser = new JSONParser<>(ShopObject[].class);
                ShopObject shopObjects[] = shopObjectJSONParser.parse(jsonData);

                this.shopItems = new ArrayList<>(Arrays.asList(shopObjects));

                API.log("Items added!");
            } else {
                API.log("I cannot find shop list file...");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public ShopObject findItemById(int id) {
        return (ShopObject) CollectionUtils.find(this.shopItems, shopObject -> ((ShopObject) shopObject).getId() == id);
    }

    public ShopObject findItemByName(String name) {
        return (ShopObject) CollectionUtils.find(this.shopItems, shopObject -> ((ShopObject) shopObject).getName().equals(name));
    }

    public void buyAction(Map<Integer, Object> response, Player player, String itemName) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());

        double playerMoney = profile.getDollars();
        int itemCount = Integer.parseInt(response.get(1).toString());
        if (response.get(1) == null)
            return;

        ShopObject wantedItem = this.findItemByName(itemName);
        if (!response.isEmpty()) {
            double finalPrice = wantedItem.getCost() * itemCount;

            if (playerMoney >= finalPrice) {
                profile.setDollars(playerMoney - finalPrice);
                Item gameItem = Item.get(wantedItem.getId(), wantedItem.getMeta(), itemCount);
                player.getInventory().addItem(gameItem);
                messageAPI.sendBuyItemMessage(player, gameItem, finalPrice);
            } else if (playerMoney < finalPrice) {
                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.money.enough", mainAPI.emptyNoSpace + playerMoney,
                        mainAPI.emptyNoSpace + finalPrice));
            }
        }
    }

    public void sellAction(Map<Integer, Object> response, Player player, String itemName) {
        ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());

        double playerMoney = profile.getDollars();
        int itemCount = Integer.parseInt(response.get(1).toString());
        if (response.get(1) == null)
            return;

        ShopObject sellingItem = this.findItemByName(itemName);
        Item gameItem = Item.get(sellingItem.getId(), sellingItem.getMeta(), itemCount);
        double finalPrice = sellingItem.getCost() * itemCount;

        if (!response.isEmpty()) {
            if (player.getGamemode() != Player.SURVIVAL) {
                messageAPI.sendGamemodeSellExceptionMessage(player);
                return;
            }
            if (!player.getInventory().contains(gameItem)) {
                messageAPI.sendUnsuficientItemsMessage(player);
                return;
            }

            if (gameItem.getDamage() == sellingItem.getMeta() && gameItem.getCount() == itemCount) {
                profile.setDollars(playerMoney + finalPrice);
                player.getInventory().remove(gameItem);
                messageAPI.sendSellItemMessage(player, gameItem, finalPrice);
            } else if (gameItem.getCount() != itemCount) {
                messageAPI.sendUnsuficientItemsMessage(player);
            } else if (gameItem.getDamage() != sellingItem.getMeta()) {
                messageAPI.sendBreakedItemMessage(player);
            }
        }
    }

}