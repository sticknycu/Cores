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
                        "              Welcome to CHPE\n" +
                        "   Select your favourite gamemode:\n";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "            Bine ai venit pe CHPE\n" +
                        "       Selecteaza modul tau preferat de joc:\n";
                break;
        }
        return STRING;
    }

    public String getContentFormRanks(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "               Here you can see all ranks!\n " +
                        "   Select what you want to do from now.";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "             Aici poti vedea toate rank-urile!\n" +
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
                STRING = "Ranks";
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
                STRING = "Ranks";
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
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n   §7Go to our website: mariusmrn.com/servers for more!";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Intra pe site-ul: mariusmrn.com/servers pentru a afla mai multe!";
                        break;
                }
                break;
            case 2:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n         §7Have fun";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n    §7Distractie plauta!";
                        break;
                }
                break;
            case 3:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n §7Thanks for playing!";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n §7Multumim pentru activitate!";
                        break;
                }
                break;
            case 4:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n     §7Discord: mariusmrn.com/discord";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Discord: mariusmrn.com/discord";
                        break;
                }
                break;
            case 5:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n     §7Go to our website: mariusmrn.com/servers for more!";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n  §7Intra pe site-ul: mariusmrn.com/servers pentru a afla mai multe!";
                        break;
                }
                break;
            case 6:
                switch (lang) {
                    case 0:
                        STRING = "§6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n       §7Donate for awesome rewards!";
                        break;
                    case 1:
                        STRING = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n   §7Doneaza pentru recompense minunate!";
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
                player.sendMessage("§7(§3!§7) §3Now all players are invisible!");
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
                player.sendMessage("§7(§3!§7) §3Now all players are visible!");
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
                player.sendMessage("§7(§3!§7) §4Error: §7This function not work yet! Use §b/utils §7options.");
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
                player.sendMessage("§7(§3!§7) §3That's not good for you..");
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
                damager.sendMessage("§7(§3!§7) §4Error: §7You can't do PvP here!");
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
                player.sendMessage("§7(§3!§7) §4Error: §7You are at border. Please go back!");
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
                player.sendMessage("§f» §eYou selected english language!");
                break;
            case 1:
                player.sendMessage("§f» §eAi selectat limba server-ului in romana!");
                break;
        }
    }

    public void sendReceiveItemMessage(Player player, int experience) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dYou got §e" + experience + " §dExperience!");
                break;
            case 1:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dAi primit §e" + experience + " §dExperienta!");
                break;
        }
    }
    public void sendWithoutJobMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eFrom now, you don't have One Job §e!");
                break;
            case 1:
                player.sendMessage("§f» §eDe acum, nu mai ai nici un Job§e!");
                break;
        }
    }

    public void sendCrateMessage(Player player, int number) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYour lucky number it's §6" + number + "§e!");
                player.sendMessage("§f» §eCongratulations for reward!");
                break;
            case 1:
                player.sendMessage("§f» §eNumarul tau norocos este §6" + number + "§e!");
                player.sendMessage("§f» §eFelicitari pentru castig!");
                break;
        }
    }

    public void sendCrateCountMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §ePlease use only 1 key!");
                break;
            case 1:
                player.sendMessage("§f» §eTe rog foloseste doar 1 cheie!");
                break;
        }
    }

    public void sendHitBowMessage(Player player, Player damager) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§f» §e" + player.getName() + " §eis now at §6" + player.getHealth() + "§6/§e" + player.getMaxHealth() + "§3!");
                break;
            case 1:
                damager.sendMessage("§f» §e" + player.getName() + " §eare acum §6" + player.getHealth() + "§6/§e" + player.getMaxHealth() + "§3!");
                break;
        }
    }

    public void getPlayerMoneyMessage(CommandSender commandSender, Player getter, double money) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §ehave now §6$" + money + "§e!");
                    break;
                case 1:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §eare acum §6$" + money + "§e!");
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §e" + getter.getName() + " §eare acum §6$" + money + "§e!");
        }
    }

    public void getPlayerTimeMessage(CommandSender commandSender, Player getter, long time, long totalTime) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6was active on this session for §e" + Loader.time(time) + " §e!");
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6was active on this server for §e" + Loader.time(totalTime) + " §e!");
                    break;
                case 1:
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe aceasta sesiune pentru §e" + Loader.time(time) + " §e!");
                    commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe acest server pentru §e" + Loader.time(totalTime) + " §e!");
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe aceasta sesiune pentru §e" + Loader.time(time) + " §e!");
            commandSender.sendMessage("§f» §e" + getter.getName() + " §6a fost activ pe acest server pentru §e" + Loader.time(totalTime) + " §e!");
        }
    }

    public void getSelfMoneyMessage(Player player, double money) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eNow you have §6$" + money + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi acum §6$" + money + "§e!");
                break;
        }
    }

    public void sendLangArrayException(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
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
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§3!§7) §eYou was active on this session for §6" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§3!§7) §eYou was active on server for §6" + Loader.time(totalTime) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§3!§7) §eAi fost activ pe aceasta sesiune timp de §6" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§3!§7) §eAi fost activ pe server timp de §6" + Loader.time(totalTime) + " §7!");
                break;
        }
    }


    public void addPlayerMoneyMessage(CommandSender commandSender, Player giver, double money, double count) {
        if (commandSender instanceof Player) {
            int lang = Database.profile.get(((Player) commandSender).getUniqueId()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§f» §eYou added §6$" + count + " §eto " + giver.getName() + "§3!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §ehave now §6$" + money + "§3!");
                    API.getDatabase().getLanguage(giver, language -> {
                        switch (language) {
                            case 0:
                                giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom " + commandSender.getName() + "§3!");
                                break;
                            case 1:
                                giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la " + commandSender.getName() + "§3!");
                                break;
                        }
                    });
                    break;
                case 1:
                    commandSender.sendMessage("§f» §eAi adaugat §6$" + count + " §elui " + giver.getName() + "§3!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + money + "§3!");
                    API.getDatabase().getLanguage(giver, language -> {
                        switch (language) {
                            case 0:
                                giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom " + commandSender.getName() + "§3!");
                                break;
                            case 1:
                                giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la " + commandSender.getName() + "§3!");
                                break;
                        }
                    });
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §eAi adaugat §6$" + count + " §elui " + giver.getName() + "§3!");
            commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + money + "§3!");
            int language = Database.profile.get(giver.getUniqueId()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom CONSOLE§3!");
                    break;
                case 1:
                    giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la CONSOLE§3!");
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
                    commandSender.sendMessage("§f» §eYou setted §6$" + count + " §eto " + giver.getName() + "§3!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §ehave now §6$" + count + "§3!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§f» §eYour coins setted to §6$" + count + " §eby " + commandSender.getName() + "§3!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §ede " + commandSender.getName() + "§3!");
                            break;
                    }
                    break;
                case 1:
                    commandSender.sendMessage("§f» §eAi setat §e$" + count + " banii §elui " + giver.getName() + "§3!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + count + "§3!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§f» §eYour coins setted to §e$" + count + " §eby " + commandSender.getName() + "§3!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §ede " + commandSender.getName() + "§3!");
                            break;
                    }
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §eAi setat §6$" + count + " banii §elui " + giver.getName() + "§3!");
            commandSender.sendMessage("§f» §6" + giver.getName() + " §eare acum §6$" + count + "§3!");
            int language = Database.profile.get(giver.getUniqueId()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§f» §eYour coins setted to §6$" + count + " §bby CONSOLE§3!");
                    break;
                case 1:
                    giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §bde CONSOLE§3!");
                    break;
            }
        }
    }

    public void addSelfMoneyMessage(Player player, double money, double count) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou got §6$" + count + " from yourself§3!");
                player.sendMessage("§f» §eYou have now §6$" + money + "§3!");
                break;
            case 1:
                player.sendMessage("§f» §eAi primit §e$" + count + " de la tine§3!");
                player.sendMessage("§f» §eAcum ai§e$" + money + "§3!");
                break;
        }
    }

    public void setSelfMoneyMessage(Player player, double count) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYour coins setted to §6$" + count + " by yourself§3!");
                player.sendMessage("§f» §eYou have now §e$" + count + "§3!");
                break;
            case 1:
                player.sendMessage("§f» §eTi-ai setat banii la §6$" + count + "§3!");
                player.sendMessage("§f» §eAcum ai§6$" + count + "§3!");
                break;
        }
    }

    public void addMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/addcoins <player> <count>§3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/addcoins <player> <count>§3!");
                break;
        }
    }

    public void getMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/coins <player>§3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/coins <player>§3!");
                break;
        }
    }

    public void getTimeExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/onlinetime <player> §3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/onlinetime <player>§3!");
                break;
        }
    }

    public void topMoneyExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/topcoins §3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/topcoins §3!");
                break;
        }
    }

    public void topKillsExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/topkills §3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/topkills §3!");
                break;
        }
    }

    public void topTimeExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/toptime §3!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/toptime §3!");
                break;
        }
    }

    public void topDeathsExceptionMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("f» §eYou use too much arguments! Use: §c/topdeaths §3!");
                break;
            case 1:
                player.sendMessage("f» §eFolosesti prea multe argumente! Foloseste: §c/topdeaths §3!");
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
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                    The DropParty is starting in 1 minute!§r\n" +
                        "                         Fireworks will appear in Spawn.§r\n" +
                        "             Because you were Online you will get a Special Key!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                       DropParty-ul va aparea in 1 de minut!§r\n" +
                        "                          Artificile vor aparea in Spawn.§r\n" +
                        "               Pentru ca ai fost Online, vei primii o Cheie Speciala!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendDropPartySpawnedMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                          The DropParty started!§r\n" +
                        "                The fireworks and The Boss appeared in Spawn.§r\n" +
                        "                     Everyone received a DropParty Key!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                              DropParty-ul a aparut!§r\n" +
                        "                    Artificile si The Boss au aparut in Spawn.§r\n" +
                        "                      Toata lumea a primit DropParty Key!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendShutDownSoonMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                           Server will restart soon!§r\n" +
                        "                    Please accord attention to this message!§r\n" +
                        "                  You can enter again in 10 seconds after restart!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                           Serverul se va restarta curand!§r\n" +
                        "                        Va rog acordati atentie acestui mesaj!§r\n" +
                        "                     Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendShutDownInTenSecondsMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                         Server will restart §cin 10 seconds!§r\n" +
                        "                       Please accord attention to this message!§r\n" +
                        "                           You can enter again in 10 seconds!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                        Serverul se va restarta §cin 10 secunde!§r\n" +
                        "                            Va rog acordati atentie acestui mesaj!§r\n" +
                        "                      Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendDropPartyReceiveKeyMessage(Player player) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§e+1 DropParty Key!§r");
                break;
            case 1:
                player.sendMessage("§e+1 DropParty Key!§r");
                break;
        }
    }

    public void sendCoordsSwitchMessage(Player player, boolean bool) {
        int lang = Database.profile.get(player.getUniqueId()).getLanguage();
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
        int lang = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            lang = Database.profile.get(player.getUniqueId()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rThe place where fun begins", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rLocul unde distractia incepe", 1, 1, 1);
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
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rUnique Systems", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rSisteme Unice", 1, 1, 1);
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
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§r§fWelcome", 1, 1, 1);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§r§fBun venit", 1, 1, 1);
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
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("            Vote our server daily for awesome rewards §e§lmariusmrn.com/servers§r");
                player.sendMessage("                     Join with us on discord §e§lmariusmrn.com/discord§r");
                player.sendMessage("                                                                                                            ");
                break;
            case 1:
                player.sendMessage("§l§6»§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("            Voteaza-ne serverul zilnic pentru beneficii §e§lmariusmrn.com/servers§r");
                player.sendMessage("                  Intra cu noi pe discord §3§lmariusmrn.com/discord§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("§l§6»§r--------------- §8( §6§lCHPE §r§l» §eHUB §r§8) §r---------------§6§l«§r");
                break;
        }
    }
}
