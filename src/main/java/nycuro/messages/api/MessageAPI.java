package nycuro.messages.api;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.messages.objects.MessagesObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MessageAPI {

    public MessagesObject messagesObject = new MessagesObject();

    public void init() {
        try {
            File file = new File(mainAPI.getDataFolder() + "/language/lang.json");
            API.log("Checking for existance of Language file... ");
            if (file.exists()) {
                API.log("I've found an file [EN]... Let's get all information..");
                //
                byte[] jsonData = Files.readAllBytes(file.toPath());

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode rootNode = objectMapper.readTree(jsonData);

                API.log("Setting messages...[EN]");
                JsonNode generic = rootNode.path("messages_en");
                Iterator<JsonNode> elementsGeneric = generic.elements();
                Iterator<String> fieldNamesGeneric = generic.fieldNames();
                while (fieldNamesGeneric.hasNext()) {
                    JsonNode value = elementsGeneric.next();
                    messagesObject.messages.put(fieldNamesGeneric.next(), value.textValue());
                }

                API.log("Messages added!");
            } else {
                API.log("I cannot find language file...");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private String STRING = "";

    public String getMethodPayMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Method of Pay\n" +
                        "1 - Money\n" +
                        "2 - Experience Level";
                break;
            case 1:
                STRING = "Metoda de Plata\n" +
                        "1 - Bani\n" +
                        "2 - Nivel de Experienta";
                break;
        }
        return STRING;
    }

    public String getShopContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "                Welcome to Shop!\n" +
                        "  Select what you want to do from now.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "             Bine ai venit la Shop!\n" +
                        "     Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getBuyContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "           Welcome to Buy Category!\n" +
                        "    Select what you want to do from now.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "        Bine ai venit la categoria Buy!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getSellContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "          Welcome to Sell Category!\n" +
                        "    Select what you want to do from now.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "       Bine ai venit la categoria Sell!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getEnchantingContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "     Select what type of Item you have.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "            Alege ce tip de Item ai.";
                break;
        }
        return STRING;
    }

    public String getEnchantingArmorContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "  Select what type of Enchant you want.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "       Alege ce tip de Enchant vrei.";
                break;
        }
        return STRING;
    }

    public String getEnchantingItemsContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "       Welcome to Enchating Category!\n" +
                        "  Select what type of Enchant you want.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "   Bine ai venit la categoria Enchanting!\n" +
                        "       Alege ce tip de Enchant vrei.";
                break;
        }
        return STRING;
    }

    public String getBuyGlassContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "           Welcome to Buy Category!\n" +
                        "    Select what you want to do from now.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "        Bine ai venit la categoria Buy!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getSellGlassContestsMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "          Welcome to Sell Category!\n" +
                        "    Select what you want to do from now.";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "       Bine ai venit la categoria Sell!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public void sendExceptionEnchantMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSorry! This enchant is not available on Server.");
                break;
            case 1:
                player.sendMessage("§7» §eScuze! Acest enchant nu este disponibil pe Server.");
                break;
        }
    }

    public void sendExceptionEnchantItemHandMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §ePlease add to your hand what do you want to enchant.");
                break;
            case 1:
                player.sendMessage("§7» §eTe rog pune in mana item-ul care vrei sa-l enchantezi.");
                break;
        }
    }

    public void sendExceptionEnchantInvalidMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSorry! You can't enchant this item with this enchantment.");
                break;
            case 1:
                player.sendMessage("§7» §eScuze! Nu poti enchanta acest item cu acest enchantment.");
                break;
        }
    }

    public void sendExceptionLevelEnchantMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSorry! The level that you have selected is too high compared to the usual one.");
                break;
            case 1:
                player.sendMessage("§7» §eScuze! Nivelul pe care l-ai selectat este prea mare fata de cel obisnuit.");
                break;
        }
    }

    public void sendExceptionLevelEnchantTypeMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSorry! To Buy Enchantments with Experience, you need to have at least level 15.");
                break;
            case 1:
                player.sendMessage("§7» §eScuze! Pentru a Cumpara Enchant-uri cu Experienta, trebuie sa ai cel putin level 15.");
                break;
        }
    }

    public void sendSuccesEnterStaffChat(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §aYou entered succesfuly on StaffChat!");
                break;
            case 1:
                player.sendMessage("§7» §aAi intrat cu succes pe StaffChat!");
                break;
        }
    }

    public void sendUnsuficientExperienceMessage(Player player, int needed) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You don't have enough experience! You have: §6$" + player.getExperienceLevel() + " §ebut you need: §6$" + needed + "!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Nu ai destui experienta! Ai exact: §6$" + player.getExperienceLevel() + " §edar ai nevoie de: §6$" + needed + "!");
                break;
        }
    }

    public void sendUnsuficientItemsMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You don't have this item!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Nu ai item-ul!");
                break;
        }
    }

    public void sendGamemodeSellExceptionMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You can sell items only in Survival Mode!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Poti vinde iteme doar in Survival Mode!");
                break;
        }
    }

    public void sendBreakedItemMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! This Item is broken!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Acest Item este stricat!");
                break;
        }
    }

    public void sendInsufficientCountMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You don't have enough quantity!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Nu ai cantitatea necesara!");
                break;
        }
    }

    public void sendExceptionShopMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWrong arguments! Please use: §6/shop.");
                break;
            case 1:
                player.sendMessage("§7» §eArgument gresit! Te rog foloseste: §6/shop.");
                break;
        }
    }

    public void sendBuyItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eYou have successfully bought: §6" + item + "§e for §6" + priceFinal + "§e!");
                break;
            case 1:
                player.sendMessage("§7» §eAi cumparat cu succes: §6" + item + "§e pentru §6" + priceFinal + "§e!");
                break;
        }
    }

    public void sendEnchantItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eYou have successfully enchanted: §6" + item + "§e for §6" + priceFinal + "§e!");
                break;
            case 1:
                player.sendMessage("§7» §eAi enchantat cu succes: §6" + item + "§e pentru §6" + priceFinal + "§e!");
                break;
        }
    }

    public void sendEnchantItemExperienceMessage(Player player, Item item, int experience) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully enchanted: §6" + item + "§e for §6" + experience + " §eexperience!");
                break;
            case 1:
                player.sendMessage("§f» §eAi enchantat cu succes: §6" + item + "§e pentru §6" + experience + " §eexperienta!");
                break;
        }
    }

    public void sendSellItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully sold: §6" + item + "§e for §6" + priceFinal + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi vandut cu succes: §6" + item + "§e pentru §6" + priceFinal + "§e!");
                break;
        }
    }
}
