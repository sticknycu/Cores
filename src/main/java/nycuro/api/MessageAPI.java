package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

/**
 * author: NycuRO
 * HubLoader Project
 * API 1.0.0
 */
public class MessageAPI {

    private String STRING = "";

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

    public String getCompassMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Servers";
                break;
            case 1:
                STRING = "Servere";
                break;
            default:
                STRING = "Servers";
        }
        return STRING;
    }

    public String getDyeStageOneMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Set Invisible Players";
                break;
            case 1:
                STRING = "Seteaza Jucatorii Invizibili";
                break;
            default:
                STRING = "Set Invisible Players";
        }
        return STRING;
    }

    public String getDyeStageTwoMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Set Visible Players";
                break;
            case 1:
                STRING = "Seteaza Jucatorii Vizibili";
                break;
        }
        return STRING;
    }

    public String getContentFormServers(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "              Welcome to Servers!\n" +
                        "   Select what you want to do from now.";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "            Bine ai venit la Servere!\n" +
                        "       Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getContentFormRanks(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "               Welcome to Ranks!\n" +
                        "   Select what you want to do from now.";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "             Bine ai venit la Grade!\n" +
                        "       Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getTitleFormServers(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Servers";
                break;
            case 1:
                STRING = "Servere";
                break;
        }
        return STRING;
    }

    public String getTitleFormRanks(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Ranks";
                break;
            case 1:
                STRING = "Grade";
                break;
        }
        return STRING;
    }

    public String getFeatherMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Jump";
                break;
            case 1:
                STRING = "Saritura";
                break;
            default:
                STRING = "Jump";
        }
        return STRING;
    }

    public String getNetherStarMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Ranks";
                break;
            case 1:
                STRING = "Grade";
                break;
            default:
                STRING = "Ranks";
        }
        return STRING;
    }

    public String getBowMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Bow Teleport";
                break;
            case 1:
                STRING = "Teleportare Arc";
                break;
            default:
                STRING = "Bow Teleport";
        }
        return STRING;
    }

    public String getMessageBossBar(Player player, int playerTime) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (playerTime) {
            case 1:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n   §7Facebook on @ §efacebook.nycuro.us";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Facebook la @ §efacebook.nycuro.us";
                        break;
                }
                break;
            case 2:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n         §7Vote on @ §evote.nycuro.us";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n    §7Voteaza la @ §evote.nycuro.us";
                        break;
                }
                break;
            case 3:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n §7Messenger on @ §emessenger.nycuro.us";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n §7Messenger la @ §emessenger.nycuro.us";
                        break;
                }
                break;
            case 4:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n     §7Discord on @ §ediscord.nycuro.us";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Discord la @ §ediscord.nycuro.us";
                        break;
                }
                break;
            case 5:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n     §7Store on @ §enycuro.buycraft.net";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Magazin la @ §enycuro.buycraft.net";
                        break;
                }
                break;
            case 6:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n       §7Have Fun @ §eNycuRO Factions";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n   §7Distractie placuta @ §eNycuRO Factions";
                        break;
                }
                break;
        }
        return STRING;
    }

    public String getRankMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "PLAYER";
                break;
            case 1:
                STRING = "JUCATOR";
                break;
        }
        return STRING;
    }

    public void sendHidePlayersMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §3Now all the players are invisible!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §3Acum toti jucatorii sunt invizibili!");
                break;
        }
    }

    public void sendShowPlayersMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §3Now all the players are visible!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §3Acum toti jucatorii sunt vizibili!");
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

    public void sendLangArrayException(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §bPlease use §c/lang §aen/ro §r§3!");
                break;
            case 1:
                player.sendMessage("§7» §bTe rog foloseste §c/lang §aen/ro §r§3!");
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
