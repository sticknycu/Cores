package nycuro.api;

import cn.nukkit.Player;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

/**
 * author: NycuRO
 * HubLoader Project
 * API 1.0.0
 */
public class MessageAPI {

    private String STRING = "";
    private String STRING_BOSSBAR = "";

    public void sendAbuseMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7You abuse! That's not good..");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Hopa...Abuzezi! Nu e bine asa..");
                break;
        }
    }

    public String getCompassMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String getDyeStageOneMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Set Invisible Players";
                break;
            case 1:
                STRING = "Seteaza Jucatorii Invizibili";
                break;
        }
        return STRING;
    }

    public String getDyeStageTwoMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "                Welcome to CHPE\n" +
                        "     Select your favourite gamemode:\n";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "             Bine ai venit pe CHPE\n" +
                        "     Selecteaza modul tau preferat:\n";
                break;
        }
        return STRING;
    }

    public String getContentFormRanks(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                       Hello!\n" +
                        "          Here you can see all ranks!\n " +
                        "  Select what you want to do from now.";
                break;
            case 1:
                STRING = "                       Salut!\n" +
                        "       Aici poti vedea toate rank-urile!\n" +
                        "      Alege ce doresti sa faci de acum.";
                break;
        }
        return STRING;
    }

    public String getTitleFormServers(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Ranks";
                break;
            case 1:
                STRING = "Rank-uri";
                break;
        }
        return STRING;
    }

    public String getFeatherMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Jump";
                break;
            case 1:
                STRING = "Saritura";
                break;
        }
        return STRING;
    }

    public String getNetherStarMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Ranks";
                break;
            case 1:
                STRING = "Rank-uri";
                break;
        }
        return STRING;
    }

    public String getBowMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "Bow Teleport";
                break;
            case 1:
                STRING = "Teleportare Arc";
                break;
        }
        return STRING;
    }

    public String getInfoScoreboard(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7--- §e§lInfo Player: " + "  ";
                break;
            case 1:
                STRING = "§7--- §e§lDetalii Jucator: " + "  ";
                break;
        }
        return STRING;
    }

    public String getVoteLangScoreboard(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        int votes = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getVotes();
        switch (lang) {
            case 0:
                STRING = "§7| §fVotes: §6" + votes + "   ";
                break;
            case 1:
                STRING = "§7| §fVoturi: §6" + votes + "   ";
                break;
        }
        return STRING;
    }


    public String getMessageBossBar(Player player, int playerTime) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (playerTime) {
            case 3:
                switch (lang) {
                    case 0:
                        STRING_BOSSBAR = "    §6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n                      §7Have fun";
                        break;
                    case 1:
                        STRING_BOSSBAR = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n                    §7Distractie placuta!";
                        break;
                }
                break;
            case 6:
                switch (lang) {
                    case 0:
                        STRING_BOSSBAR = "      §6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n                  §7Thanks for playing!";
                        break;
                    case 1:
                        STRING_BOSSBAR = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n                §7Multumim pentru activitate!";
                        break;
                }
                break;
            case 9:
                switch (lang) {
                    case 0:
                        STRING_BOSSBAR = "     §6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n            §7Discord: mariusmrn.com/discord";
                        break;
                    case 1:
                        STRING_BOSSBAR = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n              §7Discord: mariusmrn.com/discord";
                        break;
                }
                break;
            case 12:
                switch (lang) {
                    case 0:
                        STRING_BOSSBAR = "          §6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n      §7Go to our website: mariusmrn.com/servers for more!";
                        break;
                    case 1:
                        STRING_BOSSBAR = "               §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n         §7Intra pe site-ul: mariusmrn.com/servers pentru a afla mai multe!";
                        break;
                }
                break;
            case 15:
                switch (lang) {
                    case 0:
                        STRING_BOSSBAR = "  §6§l»§r-- Welcome to §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n          §7Donate for awesome rewards!";
                        break;
                    case 1:
                        STRING_BOSSBAR = "    §6§l»§r-- Bine ai venit pe §6§lchpe.MariusMRN.com§r --§6§l« §r\n\n         §7Doneaza pentru recompense minunate!";
                        break;
                }
                break;
        }
        return STRING_BOSSBAR;
    }

    public String getRankMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §6Now all players are invisible!");
                break;
            case 1:
                player.sendMessage("§f» §6Acum toti jucatorii sunt invizibili!");
                break;
        }
    }

    public void sendShowPlayersMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §6Now all players are visible!");
                break;
            case 1:
                player.sendMessage("§f» §6Acum toti jucatorii sunt vizibili!");
                break;
        }
    }

    public void sendNotWorkServiceMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7This function not work yet! Use §b/utils §7options.");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Aceasta functie nu merge inca! Foloseste optiunile de la §b/utils§7.");
                break;
        }
    }

    public void sendNotFinishedServerMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7This server is in construction! Use another option.");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Aceast server e in constructie! Foloseste alta optiune.");
                break;
        }
    }

    public void sendSmecherieMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §6That's not good for you..");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §6Umblii cu smecherii? Nu e bine pentru tine..");
                break;
        }
    }

    public void sendBreakMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7You can't break blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Nu poti sparge blocuri aici!");
                break;
        }
    }

    public void sendPlaceMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7You can't place blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Nu poti pune blocuri aici!");
                break;
        }
    }

    public void sendPvPOffMessage(Player damager) {
        int lang = Database.profileProxy.getOrDefault(damager.getName(), new ProfileProxy(damager.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§7(§6!§7) §4Error: §7You can't do PvP here!");
                break;
            case 1:
                damager.sendMessage("§7(§6!§7) §4Eroare: §7Nu poti face PvP aici!");
                break;
        }
    }

    public void sendBorderMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§6!§7) §4Error: §7You are at border. Please go back!");
                break;
            case 1:
                player.sendMessage("§7(§6!§7) §4Eroare: §7Ai ajuns la Border. Te rugam sa te indepartezi!");
                break;
        }
    }

    public void sendLangMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou selected english language!");
                break;
            case 1:
                player.sendMessage("§f» §eAi selectat limba serverului in romana!");
                break;
        }
    }

    public void sendReceiveItemMessage(Player player, int experience) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dYou got §e" + experience + " §dExperience!");
                break;
            case 1:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dAi primit §e" + experience + " §dExperienta!");
                break;
        }
    }

    public void sendCrateMessage(Player player, int number) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §ePlease use only 1 key!");
                break;
            case 1:
                player.sendMessage("§f» §eTe rog foloseste doar 1 cheie!");
                break;
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

    public void sendShutDownSoonMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rThe place where fun begins", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rLocul unde distractia incepe", 20, 20, 20);
                break;
        }
    }

    public void sendSecondJoinTitle(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rUnique Systems", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§rSisteme Unice", 20, 20, 20);
                break;
        }
    }

    public void sendThreeJoinTitle(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§r§fWelcome", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE HUB §l§f«§r", "§r§fBun venit", 20, 20, 20);
                break;
        }
    }

    public void sendLanguageMessage(Player player) {
        player.sendTitle("§l§f» §r§6INFO §l§f«§r", "§r§f/lang <en/ro>", 20, 20, 20);
    }


    public void sendJoinMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r------------------------------ §8( §6§lCHPE §r§l» §eHUB §r§8) §r------------------------------§6§l«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("            Vote our server daily for awesome rewards §e§lmariusmrn.com/servers§r");
                player.sendMessage("                     Join with us on discord §e§lmariusmrn.com/discord§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("§l§6»§r§r------------------------------ §8( §6§lCHPE §r§l» §eHUB §r§8) §r------------------------------§6§l«§r");
                break;
            case 1:
                player.sendMessage("§l§6»§r------------------------------ §8( §6§lCHPE §r§l» §eHUB §r§8) §r------------------------------§6§l«§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("            Voteaza-ne serverul zilnic pentru beneficii §e§lmariusmrn.com/servers§r");
                player.sendMessage("                  Intra cu noi pe discord §e§lmariusmrn.com/discord§r");
                player.sendMessage("                                                                                                            ");
                player.sendMessage("§l§6»§r------------------------------ §8( §6§lCHPE §r§l» §eHUB §r§8) §r------------------------------§6§l«§r");
                break;
        }
    }
}