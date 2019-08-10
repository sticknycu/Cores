package nycuro.shop.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.shop.BuyUtils;
import nycuro.shop.EnchantUtils;
import nycuro.shop.MoneyUtils;
import nycuro.shop.SellUtils;
import nycuro.shop.commands.ShopCommandManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ShopAPI {

    public static BuyUtils buyUtils;

    public static SellUtils sellUtils;

    public static MoneyUtils moneyUtils;

    public static EnchantUtils enchantUtils;

    public static BuyUtils getBuyUtilsAPI() {
        return buyUtils;
    }

    public static SellUtils getSellUtilsAPI() {
        return sellUtils;
    }

    public static MoneyUtils getMoneyUtilsAPI() {
        return moneyUtils;
    }

    public static EnchantUtils getEnchantUtils() {
        return enchantUtils;
    }

    public void registerCommands() {
        ShopCommandManager.registerAll(mainAPI);
    }

    public void sendShopContents(Player player) {
        FormWindowSimple shopMenu = new FormWindowSimple("Shop", messageAPI.getShopContestsMessages(player));
        shopMenu.addButton(new ElementButton("Buy", new ElementButtonImageData("url", "https://i.imgur.com/wHiyJED.png")));
        shopMenu.addButton(new ElementButton("Sell", new ElementButtonImageData("url", "https://i.imgur.com/dHku96H.png")));
        //shopMenu.addButton(new ElementButton("Enchanting", new ElementButtonImageData("url", "https://i.imgur.com/iArKQQb.png")));
        shopMenu.addButton(new ElementButton("Close"));
        //shopMenu.addButton(new ElementButton("Trade", new ElementButtonImageData("url", "https://i.imgur.com/UeUTcJB.png")));
        //shopMenu.addButton(new ElementButton("Kits", new ElementButtonImageData("url", "https://i.imgur.com/1taD1Mk.png")));
        //shopMenu.addButton(new ElementButton("Ranks", new ElementButtonImageData("url", "https://i.imgur.com/6BUVxQn.png")));
        player.showFormWindow(new ResponseFormWindow(shopMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendBuyContents(player);
                            return;
                        case 1:
                            sendSellContents(player);
                            return;
                        case 2:
                            //sendEnchantingContents(player);
                            return;
                        case 3:
                            break;
                        /*case 2:
                            sendTradeContents(player);
                            return;
                        case 3:
                            sendKitsContents(player);
                            return;
                        case 4:
                            sendDonateContents(player);*/
                    }
                }
            }
        }));
    }

    private void sendBuyContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Buy", messageAPI.getBuyContestsMessages(player));
        buyMenu.addButton(new ElementButton("Water & Lava", new ElementButtonImageData("url", "https://i.imgur.com/PMCvP7P.png")));
        buyMenu.addButton(new ElementButton("Nether Objects", new ElementButtonImageData("url", "https://i.imgur.com/CS2NgWw.png")));
        buyMenu.addButton(new ElementButton("Wood", new ElementButtonImageData("url", "https://i.imgur.com/2b2pc4h.png")));
        buyMenu.addButton(new ElementButton("Food & Grow", new ElementButtonImageData("url", "https://i.imgur.com/RxCVU8B.png")));
        buyMenu.addButton(new ElementButton("Ores", new ElementButtonImageData("url", "https://i.imgur.com/QuGy0cl.png")));
        buyMenu.addButton(new ElementButton("Glass", new ElementButtonImageData("url", "https://i.imgur.com/IPEeI3U.png")));
        buyMenu.addButton(new ElementButton("SpawnEgg & Spawner", new ElementButtonImageData("url", "https://i.imgur.com/h6zfZsL.png")));
        buyMenu.addButton(new ElementButton("Specifics", new ElementButtonImageData("url", "https://i.imgur.com/wIJaCgM.png")));
        buyMenu.addButton(new ElementButton("Others", new ElementButtonImageData("url", "https://i.imgur.com/moWQExI.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getBuyUtilsAPI().sendWaterAndLavaOptionContents(player);
                            return;
                        case 1:
                            getBuyUtilsAPI().sendNetherOptionContents(player);
                            return;
                        case 2:
                            getBuyUtilsAPI().sendWoodOptionContents(player);
                            return;
                        case 3:
                            getBuyUtilsAPI().sendFoodOptionContents(player);
                            return;
                        case 4:
                            getBuyUtilsAPI().sendOresOptionContents(player);
                            return;
                        case 5:
                            sendBuyGlassContents(player);
                            return;
                        case 6:
                            getBuyUtilsAPI().sendSpawnersOptionContents(player);
                            return;
                        case 7:
                            getBuyUtilsAPI().sendSpecificsOptionContents(player);
                            return;
                        case 8:
                            getBuyUtilsAPI().sendOthersOptionContents(player);
                            return;
                        case 9:
                            break;
                    }
                }
            }
        }));
    }


    private void sendSellContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Sell", messageAPI.getSellContestsMessages(player));
        buyMenu.addButton(new ElementButton("Nether Objects", new ElementButtonImageData("url", "https://i.imgur.com/CS2NgWw.png")));
        buyMenu.addButton(new ElementButton("Wood", new ElementButtonImageData("url", "https://i.imgur.com/2b2pc4h.png")));
        buyMenu.addButton(new ElementButton("Food & Grow", new ElementButtonImageData("url", "https://i.imgur.com/RxCVU8B.png")));
        buyMenu.addButton(new ElementButton("Ores", new ElementButtonImageData("url", "https://i.imgur.com/QuGy0cl.png")));
        buyMenu.addButton(new ElementButton("Glass", new ElementButtonImageData("url", "https://i.imgur.com/mcxNRI9.png")));
        buyMenu.addButton(new ElementButton("Others", new ElementButtonImageData("url", "https://i.imgur.com/moWQExI.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getSellUtilsAPI().sendNetherOptionContents(player);
                            return;
                        case 1:
                            getSellUtilsAPI().sendWoodOptionContents(player);
                            return;
                        case 2:
                            getSellUtilsAPI().sendFoodOptionContents(player);
                            return;
                        case 3:
                            getSellUtilsAPI().sendOresOptionContents(player);
                            return;
                        case 4:
                            sendSellGlassContents(player);
                            return;
                        case 5:
                            getSellUtilsAPI().sendOtherOptionContents(player);
                            return;
                        case 6:
                            break;
                    }
                }
            }
        }));
    }

    private void sendEnchantingContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", messageAPI.getEnchantingContestsMessages(player));
        buyMenu.addButton(new ElementButton("Armor", new ElementButtonImageData("url", "https://i.imgur.com/SuyfcCz.png")));
        buyMenu.addButton(new ElementButton("Items", new ElementButtonImageData("url", "https://i.imgur.com/xqLpmCa.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            sendEnchantingItemsArmorContents(player);
                            return;
                        case 1:
                            sendEnchantingItemsItemsContents(player);
                            return;
                        case 2:
                            break;
                    }
                }
            }
        }));
    }

    private void sendEnchantingItemsArmorContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", messageAPI.getEnchantingArmorContestsMessages(player));
        buyMenu.addButton(new ElementButton("Helmet", new ElementButtonImageData("url", "https://i.imgur.com/p9yJkzX.png")));
        buyMenu.addButton(new ElementButton("Chestplate", new ElementButtonImageData("url", "https://i.imgur.com/8ktHiL8.png")));
        buyMenu.addButton(new ElementButton("Leggings", new ElementButtonImageData("url", "https://i.imgur.com/f35xLVR.png")));
        buyMenu.addButton(new ElementButton("Boots", new ElementButtonImageData("url", "https://i.imgur.com/5TUPw4N.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getEnchantUtils().sendHelmetOptionContents(player);
                            return;
                        case 1:
                            getEnchantUtils().sendChestplateOptionContents(player);
                            return;
                        case 2:
                            getEnchantUtils().sendLeggingsOptionContents(player);
                            return;
                        case 3:
                            getEnchantUtils().sendBootsOptionContents(player);
                            return;
                        case 4:
                            break;
                    }
                }
            }
        }));
    }

    private void sendEnchantingItemsItemsContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", messageAPI.getEnchantingItemsContestsMessages(player));
        buyMenu.addButton(new ElementButton("Sword", new ElementButtonImageData("url", "https://i.imgur.com/IpYDgEw.png")));
        buyMenu.addButton(new ElementButton("Pickaxe", new ElementButtonImageData("url", "https://i.imgur.com/hHlCEJC.png")));
        buyMenu.addButton(new ElementButton("Axe", new ElementButtonImageData("url", "https://i.imgur.com/bBSdOUl.png")));
        buyMenu.addButton(new ElementButton("Shovel", new ElementButtonImageData("url", "https://i.imgur.com/qImyRDY.png")));
        buyMenu.addButton(new ElementButton("Bow", new ElementButtonImageData("url", "https://i.imgur.com/ipIY2Lb.png")));
        buyMenu.addButton(new ElementButton("Fishing Rod", new ElementButtonImageData("url", "https://i.imgur.com/tLoHJhm.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getEnchantUtils().sendSwordOptionContents(player);
                            return;
                        case 1:
                            getEnchantUtils().sendPickaxeOptionContents(player);
                            return;
                        case 2:
                            getEnchantUtils().sendAxeOptionContents(player);
                            return;
                        case 3:
                            getEnchantUtils().sendShovelOptionContents(player);
                            return;
                        case 4:
                            getEnchantUtils().sendBowOptionContents(player);
                            return;
                        case 5:
                            getEnchantUtils().sendFishingRodOptionContents(player);
                            return;
                        case 6:
                            break;
                    }
                }
            }
        }));
    }

    private void sendTradeContents(Player player) {

    }

    private void sendKitsContents(Player player) {

    }

    private void sendDonateContents(Player player) {

    }

    public void sendFormWindowCustomWithShopList(Player player, List<String> list, List<String> countList) {
        FormWindowCustom buyMenu = new FormWindowCustom("Buy Object");
        buyMenu.addElement(new ElementDropdown("Available Objects", list, 0));
        buyMenu.addElement(new ElementDropdown("Number of Object", countList, 0));
        sendFormWindowBuy(player, buyMenu);
    }

    public void sendFormWindowCustomWithSellList(Player player, List<String> list, List<String> countList) {
        FormWindowCustom sellMenu = new FormWindowCustom("Sell Object");
        sellMenu.addElement(new ElementDropdown("Available Objects", list, 0));
        sellMenu.addElement(new ElementDropdown("Number of Object", countList, 0));
        sendFormWindowSell(player, sellMenu);
    }

    public void sendFormWindowCustomWithEnchantArmorList(Player player, List<String> list, List<String> countList, List<String> type) {
        FormWindowCustom enchantMenu = new FormWindowCustom("Buy Enchant");
        enchantMenu.addElement(new ElementDropdown("Available Enchants", list, 0));
        enchantMenu.addElement(new ElementDropdown("Enchant Level", countList, 0));
        enchantMenu.addElement(new ElementDropdown(messageAPI.getMethodPayMessage(player), type, 0));
        sendFormWindowEnchantBuy(player, enchantMenu);
    }

    private void sendFormWindowBuy(Player player, FormWindowCustom buyMenu) {
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                Iterator it = response.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    it.remove();
                    String firstDropDownType = pair.getValue().toString();
                    ShopAPI.getMoneyUtilsAPI().buyAction(response, player, firstDropDownType);
                }
            }
        }));
    }

    private void sendFormWindowSell(Player player, FormWindowCustom sellMenu) {
        player.showFormWindow(new ResponseFormWindow(sellMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                Iterator it = response.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    it.remove();
                    String firstDropDownType = pair.getValue().toString();
                    ShopAPI.getMoneyUtilsAPI().sellAction(response, player, firstDropDownType);
                }
            }
        }));
    }

    private void sendFormWindowEnchantBuy(Player player, FormWindowCustom buyMenu) {
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                Iterator it = response.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    it.remove();
                    String firstDropDownType = pair.getValue().toString();
                    //ShopAPI.getMoneyUtilsAPI().enchantBuyAction(response, player, firstDropDownType);
                }
            }
        }));
    }

    private void sendBuyGlassContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Shop", messageAPI.getBuyGlassContestsMessages(player));
        buyMenu.addButton(new ElementButton("Normal Colored Glass", new ElementButtonImageData("url", "https://i.imgur.com/mcxNRI9.png")));
        buyMenu.addButton(new ElementButton("Colored Glass Pane", new ElementButtonImageData("url", "https://i.imgur.com/OZXmJxw.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getBuyUtilsAPI().sendNormalGlassOptionContents(player);
                            return;
                        case 1:
                            getBuyUtilsAPI().sendGlassPaneOptionContents(player);
                            return;
                        case 2:
                            break;
                    }
                }
            }
        }));
    }

    private void sendSellGlassContents(Player player) {
        FormWindowSimple buyMenu = new FormWindowSimple("Sell", messageAPI.getSellGlassContestsMessages(player));
        buyMenu.addButton(new ElementButton("Normal Colored Glass", new ElementButtonImageData("url", "https://i.imgur.com/mcxNRI9.png")));
        buyMenu.addButton(new ElementButton("Colored Glass Pane", new ElementButtonImageData("url", "https://i.imgur.com/OZXmJxw.png")));
        buyMenu.addButton(new ElementButton("Close"));
        player.showFormWindow(new ResponseFormWindow(buyMenu, new Consumer<Map<Integer, Object>>() {
            @Override
            public void accept(Map<Integer, Object> response) {
                if (!response.isEmpty()) {
                    switch (response.entrySet().iterator().next().getKey()) {
                        case 0:
                            getSellUtilsAPI().sendNormalGlassOptionContents(player);
                            return;
                        case 1:
                            getSellUtilsAPI().sendGlassPaneOptionContents(player);
                            return;
                        case 2:
                            break;
                    }
                }
            }
        }));
    }
}