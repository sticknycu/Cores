package nycuro.messages.api;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
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
            File file = new File(mainAPI.getDataFolder() + "/config.json");
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
                    while (fieldNamesGeneric.hasNext()) {
                        JsonNode value = elementsGeneric.next();
                        messagesObject.messages.put(fieldNamesGeneric.next(), value.textValue());
                    }
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

    public String sendArenaException(Player player, int level) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§e» §6You need have level " + level + "+ to teleport to Arena!";
                break;
            case 1:
                STRING = "§e» §6Ai nevoie de nivelul " + level + "+ pentru a te teleporta la Arena!";
                break;
        }
        return STRING;
    }

    public String sendTeleportArena(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§e» §6You need been teleported to Arena!";
                break;
            case 1:
                STRING = "§e» §6Ai fost teleportat la Arena!";
                break;
        }
        return STRING;
    }

    public String sendLockedKitStatus(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§e» §6This kit is LOCKED! Please unlock this kit using Premium Shop (/shop)!";
                break;
            case 1:
                STRING = "§e» §6Acest kit este BLOCAT! Te rog deblocheaza acest kit folosind Premium Shop (/shop)!";
                break;
        }
        return STRING;
    }

    public String getMessageDuringCombat(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§cYou cannot use this command during combat!";
                break;
            case 1:
                STRING = "§cNu poti folosi aceasta comanda cand esti in lupta!";
                break;
        }
        return STRING;
    }

    public String getMethodPayMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String spawnWitherMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0, 0, 0, 0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                      Hello!\n" +
                        "         Welcome to Spawn Wither!\n\n" +
                        "§c» §aHow can i spawn a Wither?\n" +
                        "§eFor spawning a Wither is enough to press the button.\n" +
                        "§eYou can cancel that pushing X button from up, right side." +
                        "§c» §aWhat can i do with Wither: \n" +
                        "§eWith Wither you can raid bases and get more experience.\n" +
                        "§c» §aPrice: §610k$ \n" +
                        "§eA little information: Maximum of Withers on Server is 10, so if there exists 10 Withers spawned you need to wait to minimum 1 to be despawned!\n" +
                        "§eAnd about despawning of entities, entities are despawned every 5 minutes, so be careful!\n" +
                        "§eThank you for purchase!\n" +
                        "§eHave a nice day!";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "      Bine ai venit la Wither Spawn!\n\n" +
                        "§c» §aCum pot spawna un Wither:\n" +
                        "§ePentru a spawna un wither este de ajuns pentru a apasa pe buton.\n" +
                        "§ePoti anula acest lucru apasand pe butonul din dreapta sus, X." +
                        "§c» §aCe pot face cu Wither?: \n" +
                        "§eEi bine, cu Wither poti da raid bazelor si poti castiga mult exp.\n" +
                        "§c» §aPretul: §610k$ \n" +
                        "§eO mica informatie: Maximul de Witheri pe server este de 10, deci daca exista deja 10 Witheri spawnati pe server, trebuie sa astepti ca cel putin 1 sa fie despawnat!\n" +
                        "§eSi despre spawnarea entitatilor, entitatile se despawneaza odata la 5 minute, deci fi atent!\n" +
                        "§eMultumim pentru achizitie si te mai asteptam!\n" +
                        "§eSa ai o zi buna!";
                break;
        }
        return STRING;
    }

    public void sendSmecherieMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eWalk with tricks? That's not good for you..");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eUmblii cu smecherii? Nu e bine pentru tine..");
                break;
        }
    }

    public void sendBreakMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You can't break blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Nu poti sparge blocuri aici!");
                break;
        }
    }

    public void sendPlaceMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You can't place blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Nu poti pune blocuri aici!");
                break;
        }
    }

    public void sendPvPOffMessage(Player damager) {
        int lang = Database.profileProxy.getOrDefault(damager.getName(), new ProfileProxy(damager.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§7(§e!§7) §4Error: §7You can't PvP here!");
                break;
            case 1:
                damager.sendMessage("§7(§e!§7) §4Eroare: §7Nu poti face PvP aici!");
                break;
        }
    }

    public void sendAbandonedStaffchatMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eInfo: §6You abandonated StaffChat!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eInfo: §6Ai abandonat StaffChat!");
                break;
        }
    }

    public void sendLangMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eYou selected english language!");
                break;
            case 1:
                player.sendMessage("§7» §eAi selectat limba romana!");
                break;
        }
    }

    public void sendTeleportWarpMessage(Player player, String warp) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSuccesfully teleported to Warp: §6" + warp + " §e!");
                break;
            case 1:
                player.sendMessage("§7» §eAi fost teleportat la Warp-ul: §6" + warp + " §e!");
                break;
        }
    }

    public void sendExceptionEnchantMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully sold: §6" + item + "§e for §6" + priceFinal + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi vandut cu succes: §6" + item + "§e pentru §6" + priceFinal + "§e!");
                break;
        }
    }

    public void sendRepairItemMessage(Player player, Item item) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully repaired: §6" + item + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi reparat cu succes: §6" + item + "§e!");
                break;
        }
    }

    public void sendWithoutJobMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eFrom now, you don't have a CommonJob §e!");
                break;
            case 1:
                player.sendMessage("§f» §eDe acum, nu mai ai un CommonJob§e!");
                break;
        }
    }

    public void sendNoJobMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou don't have a Job to acces that §e!");
                break;
            case 1:
                player.sendMessage("§f» §eNu ai un Job pentru a accesa asta §e!");
                break;
        }
    }

    public void getPlayerTimeMessage(CommandSender commandSender, Player getter, long time, long totalTime) {
        if (commandSender instanceof Player) {
            int lang = Database.profileProxy.getOrDefault(commandSender.getName(), new ProfileProxy(commandSender.getName(), 0,0,0,0)).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6was active on this session for §e" + API.time(time) + " §e!");
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6was active on this server for §e" + API.time(totalTime) + " §e!");
                    break;
                case 1:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe aceasta sesiune pentru §e" + API.time(time) + " §e!");
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe acest server pentru §e" + API.time(totalTime) + " §e!");
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe aceasta sesiune pentru §e" + API.time(time) + " §e!");
            commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe acest server pentru §e" + API.time(totalTime) + " §e!");
        }
    }

    public void sendLangArrayException(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §ePlease use §6/lang §aen/ro §r§e!");
                break;
            case 1:
                player.sendMessage("§f» §eTe rog foloseste §6/lang §aen/ro §r§e!");
                break;
        }
    }


    public void getSelfTimeMessage(Player player, long time, long totalTime) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eYou was active on this session for §6" + API.time(time) + " §7!");
                player.sendMessage("§7(§e!§7) §eYou was active on server for §6" + API.time(totalTime) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eAi fost activ pe aceasta sesiune timp de §6" + API.time(time) + " §7!");
                player.sendMessage("§7(§e!§7) §eAi fost activ pe server timp de §6" + API.time(totalTime) + " §7!");
                break;
        }
    }

    public String sendReportPrincipalModal(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Report Category!\n" +
                        "           Choose what you want to do";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Reports!\n" +
                        "    Alege ce doresti sa faci de acum";
                break;
        }
        return string;
    }

    public String sendReportList(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Report List!";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Report List";
                break;
        }
        return string;
    }

    public String sendInputNameReport(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "Please put here name of Player who want to reports";
                break;
            case 1:
                string = "Te rog introdu aici numele Jucatorului pe care doresit sa il raportezi";
                break;
        }
        return string;
    }

    public String sendInputReasonReport(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "Please put here reason why you reports player with specifications what them do";
                break;
            case 1:
                string = "Te rog introdu aici motivul pentru care reportezi jucatorul cu specificarea de a spune ce a facut";
                break;
        }
        return string;
    }

    public String sendPlayerSuccesReport(Player player, String name) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou reports player " + name + " succesfully!";
                break;
            case 1:
                string = "§f» §aAi raportat pe " + name + " cu succes!";
                break;
        }
        return string;
    }

    public String sendDeleteReportMessage(Player player, String name) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou succesfully deleted " + name + " reports!";
                break;
            case 1:
                string = "§f» §aAi sters cu succes reportul lui " + name + "!";
                break;
        }
        return string;
    }

    public String sendInputContactReport(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "If we can contact you, give us a discord name with tag, or other";
                break;
            case 1:
                string = "Daca te putem contacta, te rog da-ne numele de pe discord si tag-ul sau altceva";
                break;
        }
        return string;
    }

    public String sendInfoMessageReport(Player player, String name, String reason, String contact) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§eName: §6" + name + "\n\n" +
                        "§eReason: §6\n\n\n" + reason + "\n\n\n" +
                        "§eContact: §6\n\n\n" + contact + "\n\n\n";
                break;
            case 1:
                string = "§eNume: §6" + name + "\n\n" +
                        "§eMotiv: §6\n\n\n" + reason + "\n\n\n" +
                        "§eContact: §6\n\n\n" + contact + "\n\n\n";
                break;
        }
        return string;
    }

    public String sendInfoMessageSettings(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "          Welcome to Player Settings!\n\n" +
                        "§eSelect your preference and have fun on Server!";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Setarile Jucatorului!\n\n" +
                        "§eAlege setarile tale preferate si distractie placuta pe Server!";
                break;
        }
        return string;
    }

    public String sendInfoMessageReports(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "          Welcome to Reports MechanicDropParty!\n\n" +
                        "§c» §aHow to reports a Player?\n" +
                        "§eTo reports a player just tab on 'Report'.\n" +
                        "§eYou need to complete all available fields.\n\n" +
                        "§cATENTION! \n" +
                        "§eIf you have a proof, we're waiting you on Discord\n" +
                        "§eYou can reports a player on game, but on Discord too!\n\n" +
                        "§c» §aHow to reports on Discord? \n" +
                        "§eEnter on Discord and use #reports-en #reports";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Reports MechanicDropParty!\n\n" +
                        "§c» §aCum raportez un Jucator?\n" +
                        "§ePentru a raporta trebuie sa apesi pe fereastra 'Report'.\n" +
                        "§eCompleteaza toate campurile disponibile.\n\n" +
                        "§cATENTIE! \n" +
                        "§eDaca ai o dovada te asteptam pe grupul de discord sa ne-o trimiti\n" +
                        "§eRaportul se poate face si pe Discord, dar si pe server!\n\n" +
                        "§c» §aCum raportez pe Discord? \n" +
                        "§eIntri pe grupul de discord si ai canalul #reports-ro";
                break;
        }
        return string;
    }

    public void sendCoordsSwitchMessage(Player player, boolean bool) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                if (!bool) {
                    player.sendMessage("§f» §eYou set to not show coordonates!§r");
                } else {
                    player.sendMessage("§f» §eYou set to show your coordonates!§r");
                }
                break;
            case 1:
                if (!bool) {
                    player.sendMessage("§f» §eAi setat sa ti se afiseze coordonatele!§r");
                } else {
                    player.sendMessage("§f» §eAi setat sa ti se afiseaze coordonatele!§r");
                }
                break;
        }
    }

    public void sendFirstJoinTitle(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fThe place where the adventure begin ", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fLocul in care aventura incepe", 20, 20, 20);
                break;
        }
    }

    public void sendSecondJoinTitle(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fVote for awesome rewards", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fVoteaza pentru beneficii", 20, 20, 20);
                break;
        }
    }

    public void sendReportsTitle(Player player, int count) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6Reports MechanicDropParty §l§f«§r", "§r§fThere are §c" + count + "§f reports not solved!", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6Reports MechanicDropParty §l§f«§r", "§r§fExista §c" + count + "§f raporturi nerezolvate!", 20, 20, 20);
                break;
        }
    }

    public void sendThreeJoinTitle(Player player) {
        player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fmariusmrn.com/discord", 20, 20, 20);
    }

    public void sendJoinMessages(Player player) {
        for (int i = 0; i <= 30; i++) player.sendMessage("                                                              ");
    }
}
