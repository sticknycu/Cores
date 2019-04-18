package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.gui.list.ResponseFormWindow;
import nycuro.shop.BuyUtils;
import nycuro.shop.EnchantUtils;
import nycuro.shop.MoneyUtils;
import nycuro.shop.SellUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * author: NycuRO
 * FactionsCore Project
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

    public void sendShopContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple shopMenu = new FormWindowSimple("Shop", "                       Hello!\n" +
                        "                Welcome to Shop!\n" +
                        "  Select what you want to do from now.");
                shopMenu.addButton(new ElementButton("Buy", new ElementButtonImageData("url", "https://i.imgur.com/wHiyJED.png")));
                shopMenu.addButton(new ElementButton("Sell", new ElementButtonImageData("url", "https://i.imgur.com/dHku96H.png")));
                shopMenu.addButton(new ElementButton("Enchanting", new ElementButtonImageData("url", "https://i.imgur.com/iArKQQb.png")));
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
                                    sendEnchantingContents(player);
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
                break;
            case 1:
                shopMenu = new FormWindowSimple("Shop", "                      Salut!\n" +
                        "             Bine ai venit la Shop!\n" +
                        "     Alege ce doresti sa faci de acum.");
                shopMenu.addButton(new ElementButton("Buy", new ElementButtonImageData("url", "https://i.imgur.com/wHiyJED.png")));
                shopMenu.addButton(new ElementButton("Sell", new ElementButtonImageData("url", "https://i.imgur.com/dHku96H.png")));
                shopMenu.addButton(new ElementButton("Enchanting", new ElementButtonImageData("url", "https://i.imgur.com/iArKQQb.png")));
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
                                    sendEnchantingContents(player);
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
                break;
        }
    }

    private void sendBuyContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Buy", "                       Hello!\n" +
                        "           Welcome to Buy Category!\n" +
                        "    Select what you want to do from now.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Buy", "                      Salut!\n" +
                        "        Bine ai venit la categoria Buy!\n" +
                        "      Alege ce doresti sa faci de acum.");
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
                break;
        }
    }


    private void sendSellContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Sell", "                       Hello!\n" +
                        "          Welcome to Sell Category!\n" +
                        "    Select what you want to do from now.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Sell", "                      Salut!\n" +
                        "       Bine ai venit la categoria Sell!\n" +
                        "      Alege ce doresti sa faci de acum.");
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
                break;
        }
    }

    private void sendEnchantingContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "     Select what type of Item you have.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Enchanting", "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "            Alege ce tip de Item ai.");
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
                break;
        }
    }

    private void sendEnchantingItemsArmorContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "  Select what type of Enchant you want.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Enchanting", "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "       Alege ce tip de Enchant vrei.");
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
                break;
        }
    }

    private void sendEnchantingItemsItemsContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Enchanting", "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "  Select what type of Enchant you want.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Enchanting", "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "       Alege ce tip de Enchant vrei.");
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
                break;
        }
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
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                enchantMenu.addElement(new ElementDropdown("Method of Pay\n" +
                        "1 - Money\n" +
                        "2 - Experience Level", type, 0));
                break;
            case 1:
                enchantMenu.addElement(new ElementDropdown("Metoda de Plata\n" +
                        "1 - Bani\n" +
                        "2 - Nivel de Experienta", type, 0));
                break;
        }
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
                    Loader.log(firstDropDownType);
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
                    ShopAPI.getMoneyUtilsAPI().enchantBuyAction(response, player, firstDropDownType);
                }
            }
        }));
    }

    private void sendBuyGlassContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Shop", "                       Hello!\n" +
                        "           Welcome to Buy Category!\n" +
                        "    Select what you want to do from now.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Shop", "                      Salut!\n" +
                        "        Bine ai venit la categoria Buy!\n" +
                        "      Alege ce doresti sa faci de acum.");
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
                break;
        }
    }

    private void sendSellGlassContents(Player player) {
        int lang = Database.profileHub.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                FormWindowSimple buyMenu = new FormWindowSimple("Sell", "                       Hello!\n" +
                        "          Welcome to Sell Category!\n" +
                        "    Select what you want to do from now.");
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
                break;
            case 1:
                buyMenu = new FormWindowSimple("Sell", "                      Salut!\n" +
                        "       Bine ai venit la categoria Sell!\n" +
                        "      Alege ce doresti sa faci de acum.");
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
                break;
        }
    }
}