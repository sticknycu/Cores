package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import nycuro.API;
import nycuro.Core;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class MessageAPI {

    public void sendAbuseMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7You abuse! That's not good..");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Eroare: §7Hopa...Abuzezi! Nu e bine asa..");
                break;
        }
    }

    public void sendNotWorkServiceMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7This function not work! Use §b/utils §7options.");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Eroare: §7Aceasta functie nu merge inca! Foloseste optiunile de la §b/utils§7.");
                break;
        }
    }

    public void sendSmecherieMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §3Walk with the tricks? That's not good for you..");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §3Umblii cu smecherii? Nu e bine pentru tine..");
                break;
        }
    }

    public void sendBreakMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7You can't break blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Eroare: §7Nu poti sparge blocuri aici!");
                break;
        }
    }

    public void sendPlaceMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7You can't place blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Eroare: §7Nu poti pune blocuri aici!");
                break;
        }
    }

    public void sendDeadMessage(Player victim, Entity killer) {
        int lang = Database.profile.get(victim.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                victim.sendMessage("§7(§3!§7) §rYou killed by §b" + killer.getName() + "§r!");
                break;
            case 1:
                victim.sendMessage("§7(§3!§7) §rAi fost omorat de §b" + killer.getName() + "§r!");
                break;
        }
    }

    public void sendPvPOffMessage(Player damager) {
        int lang = Database.profile.get(damager.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§7(§3!§7) §4Error: §7You can't PvP here!");
                break;
            case 1:
                damager.sendMessage("§7(§3!§7) §4Eroare: §7Nu poti face PvP aici!");
                break;
        }
    }

    public void sendBorderMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7You are on border. Please go back!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Eroare: §7Ai ajuns la Border. Te rugam sa te indepartezi!");
                break;
        }
    }

    public void sendReceiveKitMessage(Player player, String kit) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Wow! You got §b" + kit + " §3!");
                break;
            case 1:
                player.sendMessage("§7» §3Wow! Ai primit §b" + kit + " §3!");
                break;
        }
    }

    public void sendFullInventoryMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §eInfo: §bYour inventory it's full for do this!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §eInfo: §bAi inventarul plin pentru a face aceasta actiune!");
                break;
        }
    }

    public void sendCommandSpawnMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §eInfo: §bYou teleported succesfuly to Spawn!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §eInfo: §bTe-ai teleportat cu succes la Spawn!");
                break;
        }
    }

    public void sendCommandCooldownSpawnMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You will teleported to Spawn in §b3 §3seconds..");
                break;
            case 1:
                player.sendMessage("§7» §3Vei fi teleportat la Spawn in §b3 §3secunde..");
                break;
        }
    }

    public void sendLangMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You selected English! Now, All Server are in English!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai selectat Romana! Acum, Tot Serverul este in Romana!");
                break;
        }
    }

    public void sendTeleportWarpMessage(Player player, String warp) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You teleported to Warp: §b" + warp + " §3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai fost teleportat la Warp-ul: §b" + warp + " §3!");
                break;
        }
    }

    public void sendKitsMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Kits: §bEnchantedStarter, Sparrow, Knight, VIP, VIP+, MVP, MVP+, Paladin, Guardian.");
                break;
            case 1:
                player.sendMessage("§7» §3Kit-uri: §bEnchantedStarter, Sparrow, Knight, VIP, VIP+, MVP, MVP+, Paladin, Guardian.");
                break;
        }
    }

    public void sendExceptionKitsMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Wrong arguments! Please use: §b/kits.");
                break;
            case 1:
                player.sendMessage("§7» §3Argument gresit! Te rog foloseste: §b/kits.");
                break;
        }
    }

    public void sendExceptionKitMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Wrong arguments! Please use: §b/kit {name}.");
                break;
            case 1:
                player.sendMessage("§7» §3Argument gresit! Te rog foloseste: §b/kit {name}.");
                break;
        }
    }

    public void sendExceptionServersMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Wrong arguments! Please use: §b/servers.");
                break;
            case 1:
                player.sendMessage("§7» §3Argument gresit! Te rog foloseste: §b/servers.");
                break;
        }
    }

    public void sendExceptionEnchantMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Sorry! This enchant is not available on Server.");
                break;
            case 1:
                player.sendMessage("§7» §3Scuze! Acest Enchant nu este disponibil pe Server.");
                break;
        }
    }

    public void sendExceptionEnchantItemHandMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Please add to your hand item you want to enchant.");
                break;
            case 1:
                player.sendMessage("§7» §3Te rog pune in mana item-ul care vrei sa-l enchantezi.");
                break;
        }
    }

    public void sendExceptionEnchantInvalidMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Sorry! You can't enchant this item with this enchantment.");
                break;
            case 1:
                player.sendMessage("§7» §3Scuze! Nu poti enchanta acest item cu acest enchantment.");
                break;
        }
    }

    public void sendExceptionLevelEnchantMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Sorry! The level you have selected is too high compared to the usual one.");
                break;
            case 1:
                player.sendMessage("§7» §3Scuze! Nivelul pe care l-ai selectat este prea mare fata de cel obisnuit.");
                break;
        }
    }

    public void sendExceptionLevelEnchantTypeMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Sorry! For Buy Enchantments with Experience, you need have at least level 40.");
                break;
            case 1:
                player.sendMessage("§7» §3Scuze! Pentru a Cumpara Enchant-uri cu Experienta, trebuie sa ai cel putin nivelul 40.");
                break;
        }
    }

    public void sendUnsuficientMoneyMessage(Player player, double needed) {
        double coins = Database.profile.get(player.getUniqueId()).getCoins();
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! You don't have so much money! You have: §b$" + coins + " §3and you need: §b$" + needed + "!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Nu ai destui bani! Ai exact: §b$" + coins + " §3si ai nevoie de: §b$" + needed + "!");
                break;
        }
    }

    public void sendUnsuficientExperienceMessage(Player player, int needed) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! You don't have so much experience! You have: §b$" + player.getExperienceLevel() + " §3and you need: §b$" + needed + "!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Nu ai destui experienta! Ai exact: §b$" + player.getExperienceLevel() + " §3si ai nevoie de: §b$" + needed + "!");
                break;
        }
    }

    public void sendUnsuficientItemsMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! You don't have item!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Nu ai item-ul!");
                break;
        }
    }

    public void sendGamemodeSellExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! You can't sell items only in Survival Gamemode!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Nu poti vinde iteme decat in Modul Supravietuitor!");
                break;
        }
    }

    public void sendBreakedItemMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! This Item is broken!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Acest Item este stricat!");
                break;
        }
    }

    public void sendInsufficientCountMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Opsss!!! You don't have enought count!");
                break;
            case 1:
                player.sendMessage("§7» §3Opsss!!! Nu ai cantitatea necesara!");
                break;
        }
    }

    public void sendCustomPermissionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7You don't have permission for do this action!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Error: §7Nu ai permisiunea de a face acest lucru!");
                break;
        }
    }

    public void sendCooldownMessage(Player player, long timeGone) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §4Error: §7It hasn't been 24 hours before you can use the kits! Has gone only §b" + Loader.time(timeGone) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §4Error: §7Nu au trecut 24 ore pentru a putea folosi kit-urile! S-au dus doar §b" + Loader.time(timeGone) + " §7!");
                break;
        }
    }

    public void sendExceptionShopMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Wrong arguments! Please use: §b/shop.");
                break;
            case 1:
                player.sendMessage("§7» §3Argument gresit! Te rog foloseste: §b/shop.");
                break;
        }
    }

    public void sendBuyItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully bought: §b" + item + "§3 for §b" + priceFinal + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai cumparat cu succes: §b" + item + "§3 pentru §b" + priceFinal + "§3!");
                break;
        }
    }

    public void sendEnchantItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully enchanted: §b" + item + "§3 for §b" + priceFinal + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai enchantat cu succes: §b" + item + "§3 pentru §b" + priceFinal + "§3!");
                break;
        }
    }

    public void sendEnchantItemExperienceMessage(Player player, Item item, int experience) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully enchanted: §b" + item + "§3 for §b" + experience + " §3experience!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai enchantat cu succes: §b" + item + "§3 pentru §b" + experience + " §3experienta!");
                break;
        }
    }

    public void sendSellItemMessage(Player player, Item item, double priceFinal) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully sell: §b" + item + "§3 for §b" + priceFinal + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai vandut cu succes: §b" + item + "§3 pentru §b" + priceFinal + "§3!");
                break;
        }
    }

    public void sendRandomTPMessage(Player player, int x, int z) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully teleported to coordonates: §b" + "X: " + x + ", Z:" + z + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai fost teleportat cu succes la coordonatele: §b" + "X: " + x + ", Z:" + z + "§3!");
                break;
        }
    }

    public void sendRepairItemMessage(Player player, Item item) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully repaired: §b" + item + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai reparat cu succes: §b" + item + "§3!");
                break;
        }
    }

    public void sendReceiveItemMessage(Player player, int experience) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§6§k  §r§5Yoahh?! §6§k!", "§dYou got §6" + experience + " §dExperience!");
                break;
            case 1:
                player.sendTitle("§6§k  §r§5Yoahh?! §6§k!", "§dAi primit §6" + experience + " §dExperienta!");
                break;
        }
    }

    public void sendReceiveJobMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        int job = Database.profile.get(player.getUniqueId()).getJob();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have successfully selected Job: §b" + JobsAPI.jobs.get(job) + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §3Ai selectat cu succes Job-ul: §b" + JobsAPI.jobs.get(job) + "§3!");
                break;
        }
    }

    public void sendWithoutJobMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3From now, you don't have One Job §3!");
                break;
            case 1:
                player.sendMessage("§7» §3De acum, nu mai ai nici un Job§3!");
                break;
        }
    }

    public void sendCrateMessage(Player player, int number) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Your lucky number it's §b" + number + "§3!");
                player.sendMessage("§7» §3Congratulations for reward!");
                break;
            case 1:
                player.sendMessage("§7» §3Numarul tau norocos este §b" + number + "§3!");
                player.sendMessage("§7» §3Felicitari pentru castig!");
                break;
        }
    }

    public void sendCrateCountMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3Please use only 1 Key!");
                break;
            case 1:
                player.sendMessage("§7» §3Te rog foloseste doar 1 Cheie!");
                break;
        }
    }

    public void sendHitBowMessage(Player player, Player damager) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§7» §b" + player.getName() + " §3is now at §b" + player.getHealth() + "§e/§b" + player.getMaxHealth() + "§3!");
                break;
            case 1:
                damager.sendMessage("§7» §b" + player.getName() + " §3are acum §b" + player.getHealth() + "§e/§b" + player.getMaxHealth() + "§3!");
                break;
        }
    }

    public void getPlayerMoneyMessage(CommandSender commandSender, Player getter, double money) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3have now §e$" + money + "§3!");
                    break;
                case 1:
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3are acum §e$" + money + "§3!");
                    break;
            }
        } else {
            commandSender.sendMessage("§7» §b" + getter.getName() + " §3are acum §e$" + money + "§3!");
        }
    }

    public void getPlayerTimeMessage(CommandSender commandSender, Player getter, long time, long totalTime) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3was active on this session for §e" + Loader.time(time) + " §7!");
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3was active on this server for §e" + Loader.time(totalTime) + " §7!");
                    break;
                case 1:
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3a fost activ pe aceasta sesiune pentru §e" + Loader.time(time) + " §7!");
                    commandSender.sendMessage("§7» §b" + getter.getName() + " §3a fost activ pe acest server pentru §e" + Loader.time(totalTime) + " §7!");
                    break;
            }
        } else {
            commandSender.sendMessage("§7» §b" + getter.getName() + " §3a fost activ pe aceasta sesiune pentru §e" + Loader.time(time) + " §7!");
            commandSender.sendMessage("§7» §b" + getter.getName() + " §3a fost activ pe acest server pentru §e" + Loader.time(totalTime) + " §7!");
        }
    }

    public void getSelfMoneyMessage(Player player, double money) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bNow you have §e$" + money + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §bAi acum §e$" + money + "§3!");
                break;
        }
    }


    public void getSelfTimeMessage(Player player, long time, long totalTime) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §bYou was active on this session for §e" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§3!§7) §bYou was active on server for §e" + Loader.time(totalTime) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §bAi fost activ pe aceasta sesiune timp de §e" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§3!§7) §bAi fost activ pe server timp de §e" + Loader.time(totalTime) + " §7!");
                break;
        }
    }


    public void addPlayerMoneyMessage(CommandSender commandSender, Player giver, double money, double count) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§7» §bYou added §e$" + count + " §bto " + giver.getName() + "§3!");
                    commandSender.sendMessage("§7» §b" + giver.getName() + " §3have now §e$" + money + "§3!");
                    API.getDatabase().getLanguage(giver, language -> {
                        switch (language) {
                            case 0:
                                giver.sendMessage("§7» §bYou bought §e$" + count + " §bfrom " + commandSender.getName() + "§3!");
                                break;
                            case 1:
                                giver.sendMessage("§7» §bAi primit §e$" + count + " §bde la " + commandSender.getName() + "§3!");
                                break;
                        }
                    });
                    break;
                case 1:
                    commandSender.sendMessage("§7» §bAi adaugat §e$" + count + " §blui " + giver.getName() + "§3!");
                    commandSender.sendMessage("§7» §b" + giver.getName() + " §3are acum §e$" + money + "§3!");
                    API.getDatabase().getLanguage(giver, language -> {
                        switch (language) {
                            case 0:
                                giver.sendMessage("§7» §bYou bought §e$" + count + " §bfrom " + commandSender.getName() + "§3!");
                                break;
                            case 1:
                                giver.sendMessage("§7» §bAi primit §e$" + count + " §bde la " + commandSender.getName() + "§3!");
                                break;
                        }
                    });
                    break;
            }
        } else {
            commandSender.sendMessage("§7» §bAi adaugat §e$" + count + " §blui " + giver.getName() + "§3!");
            commandSender.sendMessage("§7» §b" + giver.getName() + " §3are acum §e$" + money + "§3!");
            int language = Database.profile.get(giver.getUniqueId()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§7» §bYou bought §e$" + count + " §bfrom CONSOLE§3!");
                    break;
                case 1:
                    giver.sendMessage("§7» §bAi primit §e$" + count + " §bde la CONSOLE§3!");
                    break;
            }
        }
    }

    public void setPlayerMoneyMessage(CommandSender commandSender, Player giver, double count) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            int language = Database.profile.get(giver.getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§7» §bYou setted §e$" + count + " §bto " + giver.getName() + "§3!");
                    commandSender.sendMessage("§7» §b" + giver.getName() + " §3have now §e$" + count + "§3!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§7» §bYour coins setted to §e$" + count + " §bby " + commandSender.getName() + "§3!");
                            break;
                        case 1:
                            giver.sendMessage("§7» §bBanutii tai au fost setati la §e$" + count + " §bde " + commandSender.getName() + "§3!");
                            break;
                    }
                    break;
                case 1:
                    commandSender.sendMessage("§7» §bAi setat §e$" + count + " banuti §blui " + giver.getName() + "§3!");
                    commandSender.sendMessage("§7» §b" + giver.getName() + " §3are acum §e$" + count + "§3!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§7» §bYour coins setted to §e$" + count + " §bby " + commandSender.getName() + "§3!");
                            break;
                        case 1:
                            giver.sendMessage("§7» §bBanutii tai au fost setati la §e$" + count + " §bde " + commandSender.getName() + "§3!");
                            break;
                    }
                    break;
            }
        } else {
            commandSender.sendMessage("§7» §bAi setat §e$" + count + " banuti §blui " + giver.getName() + "§3!");
            commandSender.sendMessage("§7» §b" + giver.getName() + " §3are acum §e$" + count + "§3!");
            int language = Database.profile.get(giver.getUniqueId()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§7» §bYour coins setted to §e$" + count + " §bby CONSOLE§3!");
                    break;
                case 1:
                    giver.sendMessage("§7» §bBanutii tai au fost setati la §e$" + count + " §bde CONSOLE§3!");
                    break;
            }
        }
    }

    public void addSelfMoneyMessage(Player player, double money, double count) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou got §e$" + count + " from yourself§3!");
                player.sendMessage("§7» §bYou have now §e$" + money + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §bAi primit §e$" + count + " de la tine§3!");
                player.sendMessage("§7» §bAcum ai§e$" + money + "§3!");
                break;
        }
    }

    public void setSelfMoneyMessage(Player player, double count) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYour coins setted to §e$" + count + " by yourself§3!");
                player.sendMessage("§7» §bYou have now §e$" + count + "§3!");
                break;
            case 1:
                player.sendMessage("§7» §bTi-ai setat banutii la §e$" + count + "§3!");
                player.sendMessage("§7» §bAcum ai§e$" + count + "§3!");
                break;
        }
    }

    public void addMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/addcoins <player> <count>§3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/addcoins <player> <count>§3!");
                break;
        }
    }

    public void getMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/coins <player>§3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/coins <player>§3!");
                break;
        }
    }

    public void getTimeExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/onlinetime <player> §3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/onlinetime <player>§3!");
                break;
        }
    }

    public void topMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/topcoins §3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/topcoins §3!");
                break;
        }
    }

    public void topKillsExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/topkills §3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/topkills §3!");
                break;
        }
    }

    public void topTimeExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/toptime §3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/toptime §3!");
                break;
        }
    }

    public void topDeathsExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bYou use too much arguments! Use: §c/topdeaths §3!");
                break;
            case 1:
                player.sendMessage("§7» §bFolosesti prea multe argumente! Foloseste: §c/topdeaths §3!");
                break;
        }
    }

    public String sendJobPrincipalModal(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "           Welcome to Job Category!\n" +
                        "    Select what you want to do from now.";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "        Bine ai venit la categoria Job!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return string;
    }

    public String sendInfoMessageJobs(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "            Welcome to Info Jobs!\n\n" +
                        "§c» §aLumberJack:\n" +
                        "§eWith that Job you can get money if you cut wood, any type of wood.\n" +
                        "§eThe sum received per block: §72.5$\n\n" +
                        "§c» §aMiner: \n" +
                        "§eYou can make money if you are going to work with this job.\n" +
                        "§eEncount money only if you are mining CobbleStone and Precious Ores.\n" +
                        "§eThe sum received per block: §72.5$\n\n" +
                        "§c» §aFarmer: \n" +
                        "§eYou can make money with this job if you plant.\n" +
                        "§eAnd You only accept money if you Plant or Cultivate Seeds, any kind of Seeds, Saplings and Flowers.\n" +
                        "§eThe sum received per block: §71$\n\n" +
                        "§c» §aKiller:\n" +
                        "§eWith this Job you can make money if you kill or hit non-spawn players.\n" +
                        "§eThe sum received per hit: §70.4$\n" +
                        "§eSum received per kill: §73$\n\n" +
                        "§c» §aHunter:\n" +
                        "§eWith this Job you can make money if you kill or hit animals.\n" +
                        "§eThe sum received per hit: §70.4$\n" +
                        "§eSum received per kill: §71.4$";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Info Jobs!\n\n" +
                        "§c» §aLumberJack:\n" +
                        "§eCu acest Job poti face bani daca tai lemn, orice tip de lemn.\n" +
                        "§eSuma primita per block: §72.5$\n\n" +
                        "§c» §aMiner:\n" +
                        "§eCu acest Job poti face bani daca minezi.\n" +
                        "§ePrimesti bani doar daca minezi CobbleStone si Minereuri Pretioase.\n" +
                        "§eSuma primita per block: §72.5$\n\n" +
                        "§c» §aFarmer:\n" +
                        "§eCu acest Job poti face bani daca plantezi.\n" +
                        "§ePrimesti bani doar daca Plantezi sau Cultivezi Seminte, orice tip de Seminte, Sapling-uri si Flori.\n" +
                        "§eSuma primita per block: §71$\n\n" +
                        "§c» §aKiller:\n" +
                        "§eCu acest Job poti face bani daca omori sau lovesti jucatori din afara spawn-ului.\n" +
                        "§eSuma primita per hit: §70.4$\n" +
                        "§eSuma primita per kill: §73$\n\n" +
                        "§c» §aHunter:\n" +
                        "§eCu acest Job poti face bani daca omori sau lovesti animale.\n" +
                        "§eSuma primita per hit: §70.4$\n" +
                        "§eSuma primita per kill: §71$";
                break;
        }
        return string;
    }

    public void sendDropPartyEventMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                    The DropParty was released in 1 minute!§r\n" +
                        "                         Fireworks will appear in Spawn.§r\n" +
                        "             Because you were Online you will get a Special Key!§r" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
            case 1:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                       DropParty-ul va aparea in 1 de minut!§r\n" +
                        "                          Artificile vor aparea in Spawn.§r\n" +
                        "               Pentru ca ai fost Online, vei primii o Cheie Speciala!§r\n" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
        }
    }

    public void sendDropPartySpawnedMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                          The DropParty was released!§r\n" +
                        "                The fireworks and The Boss appeared in Spawn.§r\n" +
                        "                     Everyone received a DropParty Key!§r" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
            case 1:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                              DropParty-ul a aparut!§r\n" +
                        "                    Artificile si The Boss au aparut in Spawn.§r\n" +
                        "                      Toata lumea a primit DropParty Key!§r\n" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
        }
    }

    public void sendShutDownSoonMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                           Server will restart soon!§r\n" +
                        "                    Please accord attention to this message!§r\n" +
                        "                  You can enter again on 10 seconds after restart!§r" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
            case 1:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                           Serverul se va restarta curand!§r\n" +
                        "                        Va rog acordati atentie acestui mesaj!§r\n" +
                        "                     Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
        }
    }

    public void sendShutDownInTenSecondsMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                         Server will restart §cin 10 seconds!§r\n" +
                        "                       Please accord attention to this message!§r\n" +
                        "                   You can enter again on 10 seconds after restart!§r" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
            case 1:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n" +
                        "                                                                                                            \n" +
                        "                        Serverul se va restarta §cin 10 secunde!§r\n" +
                        "                            Va rog acordati atentie acestui mesaj!§r\n" +
                        "                      Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r\n");
                break;
        }
    }

    public void sendDropPartyReceiveKeyMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §3You have now +1 DropParty Key!§r");
                break;
            case 1:
                player.sendMessage("§7» §3De acum ai +1 DropParty Key!§r");
                break;
        }
    }

    public void sendCoordsSwitchMessage(Player player, boolean bool) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                if (!bool) {
                    player.sendMessage("§7» §3You set to §4NOT §3show coordonates!§r");
                } else {
                    player.sendMessage("§7» §3You set to show your coordonates!§r");
                }
                break;
            case 1:
                if (!bool) {
                    player.sendMessage("§7» §3Ai setat sa ti se §4NU §3vada coordonatele!§r");
                } else {
                    player.sendMessage("§7» §3Ai setat sa ti se vada coordonatele!§r");
                }
                break;
        }
    }

    public void sendFirstJoinTitle(Player player) {
        int lang = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            lang = Database.profile.get(player.getUniqueId()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aFactions Classic §l§c«§r", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aFactions Classic §l§c«§r", 1, 1, 1);
                break;
        }
    }

    public void sendSecondJoinTitle(Player player) {
        int lang = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            lang = Database.profile.get(player.getUniqueId()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aUnique Systems §l§c«§r", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aSisteme Unice §l§c«§r", 1, 1, 1);
                break;
        }
    }

    public void sendThreeJoinTitle(Player player) {
        int lang = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            lang = Database.profile.get(player.getUniqueId()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aNo Lag GamePlay §l§c«§r", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§8» §r§bNycuRO Factions §l§8«§r", "§l§c» §r§aUn GamePlay Fara Lag §l§c«§r", 1, 1, 1);
                break;
        }
    }

    public void sendJoinMessages(Player player) {
        int lang = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            lang = Database.profile.get(player.getUniqueId()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("                     Don't forgot vote on §b§lvote.nycuro.us§r");
                player.sendMessage("              If you want donate, visit §3§lnycuro.buycraft.net§r");
                player.sendMessage("                                                                                                            ");
                break;
            case 1:
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("                     Nu uita sa votezi la §b§lvote.nycuro.us§r");
                player.sendMessage("        Daca doresti sa donezi, viziteaza §3§lnycuro.buycraft.net§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("§7»§l§m--------------- §8( §3§lNycuRO §r§l» §bFactions §r§8) §7---------------§7«§r");
                break;
        }
    }
}
