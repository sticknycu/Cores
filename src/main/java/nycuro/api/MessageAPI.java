package nycuro.api;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.massivecraft.factions.FPlayers;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.manager.UserManager;
import nycuro.API;
import nycuro.Loader;
import nycuro.chat.handlers.ChatHandlers;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class MessageAPI {

    private String STRING = "";
    private String STRING_BOSSBAR = "";

    public String getInfoScoreboard(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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

    public String getNextScoreboard(Player player, long time) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7Next drop party in: §6" + Loader.time(time);
                break;
            case 1:
                STRING = "§7Urmatorul DropParty va fi in: §6" + Loader.time(time);
                break;
        }
        return STRING;
    }

    public String getNameScoreboard(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7| §fName: §6" + player.getName() + "  ";
                break;
            case 1:
                STRING = "§7| §fNume: §6" + player.getName() + "  ";
                break;
        }
        return STRING;
    }

    public String sendMobFarmMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§e» §6You have been teleported to MobFarm!";
                break;
            case 1:
                STRING = "§e» §6Ai fost teleportat la MobFarm!";
                break;
        }
        return STRING;
    }

    public String sendArenaException(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§e» §6You need have level 10+ to teleport to Arena!";
                break;
            case 1:
                STRING = "§e» §6Ai nevoie de nivelul 10+ pentru a te teleporta la Arena!";
                break;
        }
        return STRING;
    }

    public String sendTeleportArena(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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

    public String sendWitherSpawnMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7(§e!§7) §4Error: §7You can't spawn Wither in spawn!";
                break;
            case 1:
                STRING = "§7(§e!§7) §4Error: §7Nu poti spawna Wither-ul in spawn!";
                break;
        }
        return STRING;
    }

    public String sendRandomTPNotFirstTimeMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7(§e!§7) §4Error: §7You played before! For teleport you need to have §c1000 dollars§7!";
                break;
            case 1:
                STRING = "§7(§e!§7) §4Error: §7Ai mai jucat pana acum! Pentru a te teleporta ai nevoie de §c1000 dollars§7!";
                break;
        }
        return STRING;
    }

    public String sendMobDespawnMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§6§lTIP §r§e» §7Everyone, please pay attention! In §630 seconds §7all entities will be removed!";
                break;
            case 1:
                STRING = "§6§lTIP §r§e» §7Toata lumea, atentie! In §630 de secunde §7toate entitatile vor fi sterse!";
                break;
        }
        return STRING;
    }

    public String sendMobDespawnFinishMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§6§lTIP §r§e» §7All entities has been removed! Next entities will be removed in §65 minutes§7!";
                break;
            case 1:
                STRING = "§6§lTIP §r§e» §7Toate entitatile au fost sterse! Urmatoarele entitati vor fi sterse in §65 minute§7!";
                break;
        }
        return STRING;
    }

    public String sendTooMuchWithers(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7(§e!§7) §4Error: §7There are 10 withers spawned (§cMAXIMUM§7)! Try again later!";
                break;
            case 1:
                STRING = "§7(§e!§7) §4Eroare: §7Sunt deja 10 witheri pe server (§cMAXIMUL§7)! Incearca mai tarziu!";
                break;
        }
        return STRING;
    }

    private User giveMeADamnUser(UUID uuid) {
        UserManager userManager = ChatHandlers.api.getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);

        return userFuture.join(); // ouch!
    }

    private String getRank(IPlayer player) {
        String rank = "";
        String group = giveMeADamnUser(player.getUniqueId()).getPrimaryGroup().toUpperCase();
        String sGroup = group.toLowerCase();
        switch (sGroup) {
            case "default":
                rank = "&l&o&6PLAYER&r";
                break;
            case "premium":
                rank = "&l&o&ePREMIUM&r";
                break;
            case "vip":
                rank = "&l&o&3VIP&r";
                break;
            case "helper":
                rank = "&l&o&aHELPER&r";
                break;
            case "moderator":
                rank = "&l&o&bMODERATOR&r";
                break;
            case "yt":
                rank = "&l&o&fYT&r";
                break;
            case "admin":
                rank = "&l&o&4ADMIN&r";
                break;
        }
        return TextFormat.colorize(rank);
    }

    public String getRankScoreboard(Player player) {
        String rank = getRank(player);
        return "§7| §fRank: §6" + rank + "  ";
    }

    private String getOS(Player player) {
        switch(player.getLoginChainData().getDeviceOS()) {
            case 1:
                return "Android";
            case 2:
                return "iOS";
            case 3:
                return "Mac";
            case 4:
                return "Fire";
            case 5:
                return "Gear VR";
            case 6:
                return "HoloLens";
            case 7:
                return "Windows 10";
            case 8:
                return "Windows";
            case 9:
                return "Dedicated";
            case 10:
                return "tvOS";
            case 11:
                return "PlayStation";
            case 12:
                return "NX";
            case 13:
                return "Xbox";
            default:
                return "Unknown";
        }
    }

    public String getStatsCommand(CommandSender commandSender, IPlayer player) {
        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
        ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
        String faction = FPlayers.i.get(player.getName()).getFaction().getTag();
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date firstPlay = new Date(player.getFirstPlayed());
        Date lastPlay = new Date(player.getLastPlayed());
        switch (profileProxy.getLanguage()) {
            case 0:
                STRING = "         Profile Info:\n\n" +
                        "§c» §aName: §e" + player.getName() + "\n" +
                        "§eRank: §6" + getRank(player) + "\n" +
                        "§eLevel: §6" + profileFactions.getLevel() + "\n" +
                        "§eExperience: §6" + profileFactions.getExperience() + "/" + profileFactions.getNecesary() + "\n" +
                        "§eFirst Join: §6" + simple.format(firstPlay) + "\n" +
                        "§eOnline: §6" + (player.isOnline() ? "§3YES§6" : simple.format(lastPlay))  + "\n" +
                        (player.isOnline() ? ("§eOnline on this session: §6" + Loader.time(System.currentTimeMillis() - API.getMainAPI().played.getLong(player.getName()))) + "\n" : "") +
                        "§eOnline Time: §6" + Loader.time(profileFactions.getTime()) + "\n" +
                        "§eDollars: §6" + profileFactions.getDollars() + "\n" +
                        "§eGems: §6" + profileProxy.getGems() + "\n" +
                        "§eKills: §6" + profileFactions.getKills() + "\n" +
                        "§eDeaths: §6" + profileFactions.getDeaths() + "\n" +
                        "§eVotes: §6" + profileProxy.getVotes() + "\n" +
                        "§eFaction: §6" + faction + "\n" +
                        (player.isOnline() ? ((commandSender.isOp() ? ("§eIP: §6" + player.getPlayer().getAddress()) : ("")) + "\n") : ("")) +
                        (player.isOnline() ? ((commandSender.isOp() ? ("§eDevice Model: §6" + player.getPlayer().getLoginChainData().getDeviceModel()) : ("")) + "\n") : "") +
                        (player.isOnline() ? ("§eOS: §6" + getOS(player.getPlayer()) + "\n") : "");
                break;
            case 1:
                STRING = "         Profile Info:\n\n" +
                        "§c» §aNume: §e" + player.getName() + "\n" +
                        "§eRank: §6" + getRank(player) + "\n" +
                        "§eNivel: §6" + profileFactions.getLevel() + "\n" +
                        "§eExperienta: §6" + profileFactions.getExperience() + "/" + profileFactions.getNecesary() + "\n" +
                        "§ePrima data cand ai intrat pe Sectiune: §6" + simple.format(firstPlay) + "\n" +
                        "§eOnline: §6" + (player.isOnline() ? "§3DA§6" : simple.format(lastPlay))  + "\n" +
                        (player.isOnline() ? ("§eOnline pe aceasta sesiune: §6" + Loader.time(System.currentTimeMillis() - API.getMainAPI().played.getLong(player.getName()))) + "\n" : "") +
                        "§eTimp Online: §6" + Loader.time(profileFactions.getTime()) + "\n" +
                        "§eDolari: §6" + profileFactions.getDollars() + "\n" +
                        "§eGems: §6" + profileProxy.getGems() + "\n" +
                        "§eKills: §6" + profileFactions.getKills() + "\n" +
                        "§eDeaths: §6" + profileFactions.getDeaths() + "\n" +
                        "§eVoturi: §6" + profileProxy.getVotes() + "\n" +
                        "§eFactiune: §6" + faction + "\n" +
                        (player.isOnline() ? ((commandSender.isOp() ? ("§eIP: §6" + player.getPlayer().getAddress()) : ("")) + "\n") : ("")) +
                        (player.isOnline() ? ((commandSender.isOp() ? ("§eDevice Model: §6" + player.getPlayer().getLoginChainData().getDeviceModel()) : ("")) + "\n") : "") +
                        (player.isOnline() ? ("§eOS: §6" + getOS(player.getPlayer()) + "\n") : "");
                break;
        }
        return STRING;
    }

    public String getOnlineScoreboard(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7| §fOnline Players: §6" + API.getMainAPI().getServer().getOnlinePlayers().size() + "    ";
                break;
            case 1:
                STRING = "§7| §fJucatori Online: §6" + API.getMainAPI().getServer().getOnlinePlayers().size() + "    ";
                break;
        }
        return STRING;
    }

    public void sendAbuseMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You abuse! That's not good..");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Hopa...Abuzezi! Nu e bine asa..");
                break;
        }
    }

    public void sendNotWorkServiceMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7This function not work! Use §6/utils §7options.");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Aceasta functie nu merge inca! Foloseste optiunile de la §6/utils§7.");
                break;
        }
    }

    public void sendSmecherieMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You can't place blocks here!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Nu poti pune blocuri aici!");
                break;
        }
    }

    public void sendDeadMessage(Player victim, Entity killer) {
        int lang = Database.profileProxy.get(victim.getName()).getLanguage();
        switch (lang) {
            case 0:
                victim.sendMessage("§7(§e!§7) §rYou was killed by §6" + killer.getName() + "§r!");
                break;
            case 1:
                victim.sendMessage("§7(§e!§7) §rAi fost omorat de §6" + killer.getName() + "§r!");
                break;
        }
    }

    public void sendPvPOffMessage(Player damager) {
        int lang = Database.profileProxy.get(damager.getName()).getLanguage();
        switch (lang) {
            case 0:
                damager.sendMessage("§7(§e!§7) §4Error: §7You can't PvP here!");
                break;
            case 1:
                damager.sendMessage("§7(§e!§7) §4Eroare: §7Nu poti face PvP aici!");
                break;
        }
    }

    public void sendBorderMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You are at border. Please go back!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Eroare: §7Ai ajuns la Border. Te rugam sa te indepartezi!");
                break;
        }
    }

    public void sendReceiveKitMessage(Player player, String kit) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWow! You got §6" + kit + " §e!");
                break;
            case 1:
                player.sendMessage("§7» §eWow! Ai primit §6" + kit + " §e!");
                break;
        }
    }

    public void sendFullInventoryMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eInfo: §6Your inventory it's full, so you can't do this!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eInfo: §6Ai inventarul plin pentru a face aceasta actiune!");
                break;
        }
    }

    public void sendCommandSpawnMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eInfo: §6You have been teleported in Spawn!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eInfo: §6Te-ai teleportat cu succes la Spawn!");
                break;
        }
    }

    public void sendCommandCooldownSpawnMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eYou will be teleported in Spawn in §67 §eseconds..");
                break;
            case 1:
                player.sendMessage("§7» §eVei fi teleportat in Spawn in §67 §esecunde..");
                break;
        }
    }

    public void sendLangMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSuccesfully teleported to Warp: §6" + warp + " §e!");
                break;
            case 1:
                player.sendMessage("§7» §eAi fost teleportat la Warp-ul: §6" + warp + " §e!");
                break;
        }
    }

    public void sendKitsMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eKits: §6EnchantedStarter, Sparrow, Knight, VIP, VIP+, MVP, MVP+, Paladin, Guardian.");
                break;
            case 1:
                player.sendMessage("§7» §eKit-uri: §6EnchantedStarter, Sparrow, Knight, VIP, VIP+, MVP, MVP+, Paladin, Guardian.");
                break;
        }
    }

    public void sendExceptionKitsMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWrong arguments! Please use: §6/kits.");
                break;
            case 1:
                player.sendMessage("§7» §eArgument gresit! Te rog foloseste: §6/kits.");
                break;
        }
    }

    public void sendExceptionKitMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWrong arguments! Please use: §6/kit {name}.");
                break;
            case 1:
                player.sendMessage("§7» §eArgument gresit! Te rog foloseste: §6/kit {name}.");
                break;
        }
    }

    public void sendExceptionServersMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWrong arguments! Please use: §6/servers.");
                break;
            case 1:
                player.sendMessage("§7» §eArgument gresit! Te rog foloseste: §6/servers.");
                break;
        }
    }

    public void sendExceptionEnchantMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eSorry! To Buy Enchantments with Experience, you need to have at least level 15.");
                break;
            case 1:
                player.sendMessage("§7» §eScuze! Pentru a Cumpara Enchant-uri cu Experienta, trebuie sa ai cel putin level 15.");
                break;
        }
    }

    public void sendUnsuficientMoneyMessage(Player player, double needed) {
        double coins = Database.profileProxy.get(player.getName()).getGems();
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You don't have enough money! You have: §6$" + coins + " §ebut you need: §6$" + needed + "!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Nu ai destui bani! Ai exact: §6$" + coins + " §edar ai nevoie de: §6$" + needed + "!");
                break;
        }
    }

    public void sendSuccesSpawnWither(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §aYou spawned succesfuly wither!");
                break;
            case 1:
                player.sendMessage("§7» §aAi spawnat wither-ul cu succes!");
                break;
        }
    }

    public void sendUnsuficientExperienceMessage(Player player, int needed) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eOpsss!!! You don't have enough quantity!");
                break;
            case 1:
                player.sendMessage("§7» §eOpsss!!! Nu ai cantitatea necesara!");
                break;
        }
    }

    public void sendCustomPermissionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7You don't have permission to do this!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Error: §7Nu ai permisiunea de a face acest lucru!");
                break;
        }
    }

    public void sendCooldownMessage(Player player, long timeGone) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §4Error: §7It hasn't gone 24 hours to use this kit again! Has gone only §6" + Loader.time(timeGone) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §4Error: §7Nu au trecut 24 ore pentru a putea folosi kit-ul! S-au dus doar §6" + Loader.time(timeGone) + " §7!");
                break;
        }
    }

    public void sendExceptionShopMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully sold: §6" + item + "§e for §6" + priceFinal + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi vandut cu succes: §6" + item + "§e pentru §6" + priceFinal + "§e!");
                break;
        }
    }

    public void sendRandomTPMessage(Player player, int x, int z) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully teleported to coordonates: §6" + "X: " + x + ", Z: " + z + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi fost teleportat cu succes la coordonatele: §6" + "X: " + x + ", Z: " + z + "§e!");
                break;
        }
    }

    public void sendRepairItemMessage(Player player, Item item) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully repaired: §6" + item + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi reparat cu succes: §6" + item + "§e!");
                break;
        }
    }

    public void sendReceiveItemMessage(Player player, int experience) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dYou got §e" + experience + " §dExperience!");
                break;
            case 1:
                player.sendTitle("§e§k  §r§5Yoahh?! §f§k!", "§dAi primit §e" + experience + " §dExperienta!");
                break;
        }
    }

    public void sendReceiveJobMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        int job = Database.profileFactions.get(player.getName()).getJob();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have successfully selected Job: §6" + JobsAPI.jobs.get(job) + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi selectat cu succes Job-ul: §6" + JobsAPI.jobs.get(job) + "§e!");
                break;
        }
    }

    public void sendWithoutJobMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eFrom now, you don't have a Job §e!");
                break;
            case 1:
                player.sendMessage("§f» §eDe acum, nu mai ai un Job§e!");
                break;
        }
    }

    public void sendCrateMessage(Player player, int number) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §ePlease use only 1 key!");
                break;
            case 1:
                player.sendMessage("§f» §eTe rog foloseste doar 1 cheie!");
                break;
        }
    }

    public void sendHitBowMessage(Entity entity, Player damager) {
        int lang = 0;
        try {
            lang = Database.profileProxy.get(damager.getName()).getLanguage();
        } catch (Exception e) {
            //
        }
        switch (lang) {
            case 0:
                damager.sendMessage("§f» §6" + entity.getName() + " §eis now at §6" + entity.getHealth() + "§e/§6" + entity.getMaxHealth() + "§e!");
                break;
            case 1:
                damager.sendMessage("§f» §6" + entity.getName() + " §eare acum §6" + entity.getHealth() + "§e/§6" + entity.getMaxHealth() + "§e!");
                break;
        }
    }

    public void getPlayerMoneyMessage(CommandSender commandSender, Player getter, double money) {
        if (commandSender instanceof Player) {
            int lang = Database.profileProxy.get(commandSender.getName()).getLanguage();
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
            int lang = Database.profileProxy.get(commandSender.getName()).getLanguage();
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

    public String getMessageBossBar(Player player, int level, double necesarry, double count) {
        int lang = 0;
        try {
            lang = Database.profileProxy.get(player.getName()).getLanguage();
        } catch (Exception e) {
            //
        }
        switch (lang) {
            case 0:
                STRING_BOSSBAR = "       §6§l»§r-- Level §6§l" + level + "§r --§6§l« §r\n\n  §rNecesarry XP: §6" + Loader.round(count, 2) + "§7/§6" + necesarry;
                break;
            case 1:
                STRING_BOSSBAR = "      §6§l»§r-- Nivel §6§l" + level + "§r --§6§l« §r\n\n  §rXP Necesar: §6" + Loader.round(count, 2) + "§7/§6" + necesarry;
                break;
        }
        return STRING_BOSSBAR;
    }

    public void getSelfMoneyMessage(Player player, double money) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eYou was active on this session for §6" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§e!§7) §eYou was active on server for §6" + Loader.time(totalTime) + " §7!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eAi fost activ pe aceasta sesiune timp de §6" + Loader.time(time) + " §7!");
                player.sendMessage("§7(§e!§7) §eAi fost activ pe server timp de §6" + Loader.time(totalTime) + " §7!");
                break;
        }
    }


    public void addPlayerMoneyMessage(CommandSender commandSender, Player giver, double money, double count) {
        if (commandSender instanceof Player) {
            int langSender = Database.profileProxy.get(commandSender.getName()).getLanguage();
            int langGiver = Database.profileProxy.get(giver.getName()).getLanguage();
            switch (langSender) {
                case 0:
                    commandSender.sendMessage("§f» §eYou added §6$" + count + " §eto " + giver.getName() + "§e!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §ehave now §6$" + money + "§e!");
                    switch (langGiver) {
                        case 0:
                            giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom " + commandSender.getName() + "§e!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la " + commandSender.getName() + "§e!");
                            break;
                    }
                    break;
                case 1:
                    commandSender.sendMessage("§f» §eAi adaugat §6$" + count + " §elui " + giver.getName() + "§e!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + money + "§e!");
                    switch (langGiver) {
                        case 0:
                            giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom " + commandSender.getName() + "§e!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la " + commandSender.getName() + "§e!");
                            break;
                    }
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §eAi adaugat §6$" + count + " §elui " + giver.getName() + "§e!");
            commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + money + "§e!");
            int language = Database.profileProxy.get(giver.getName()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§f» §eYou bought §6$" + count + " §efrom CONSOLE§e!");
                    break;
                case 1:
                    giver.sendMessage("§f» §eAi primit §6$" + count + " §ede la CONSOLE§e!");
                    break;
            }
        }
    }

    public void setPlayerMoneyMessage(CommandSender commandSender, Player giver, double count) {
        if (commandSender instanceof Player) {
            int lang = Database.profileProxy.get(commandSender.getName()).getLanguage();
            int language = Database.profileProxy.get(giver.getName()).getLanguage();
            switch (lang) {
                case 0:
                    commandSender.sendMessage("§f» §eYou setted §6$" + count + " §eto " + giver.getName() + "§e!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §ehave now §6$" + count + "§e!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§f» §eYour coins setted to §6$" + count + " §eby " + commandSender.getName() + "§e!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §ede " + commandSender.getName() + "§e!");
                            break;
                    }
                    break;
                case 1:
                    commandSender.sendMessage("§f» §eAi setat §e$" + count + " banii §elui " + giver.getName() + "§e!");
                    commandSender.sendMessage("§f» §e" + giver.getName() + " §eare acum §6$" + count + "§e!");
                    switch (language) {
                        case 0:
                            giver.sendMessage("§f» §eYour coins setted to §e$" + count + " §eby " + commandSender.getName() + "§e!");
                            break;
                        case 1:
                            giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §ede " + commandSender.getName() + "§e!");
                            break;
                    }
                    break;
            }
        } else {
            commandSender.sendMessage("§f» §eAi setat §6$" + count + " banii §elui " + giver.getName() + "§e!");
            commandSender.sendMessage("§f» §6" + giver.getName() + " §eare acum §6$" + count + "§e!");
            int language = Database.profileProxy.get(giver.getName()).getLanguage();
            switch (language) {
                case 0:
                    giver.sendMessage("§f» §eYour coins setted to §6$" + count + " §6by CONSOLE§e!");
                    break;
                case 1:
                    giver.sendMessage("§f» §eBanii tai au fost setati la §6$" + count + " §6de CONSOLE§e!");
                    break;
            }
        }
    }

    public void addSelfMoneyMessage(Player player, double money, double count) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou got §6$" + count + " from yourself§e!");
                player.sendMessage("§f» §eYou have now §6$" + money + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eAi primit §e$" + count + " de la tine§e!");
                player.sendMessage("§f» §eAcum ai§e$" + money + "§e!");
                break;
        }
    }

    public void setSelfMoneyMessage(Player player, double count) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYour coins setted to §6$" + count + " by yourself§e!");
                player.sendMessage("§f» §eYou have now §e$" + count + "§e!");
                break;
            case 1:
                player.sendMessage("§f» §eTi-ai setat banii la §6$" + count + "§e!");
                player.sendMessage("§f» §eAcum ai§6$" + count + "§e!");
                break;
        }
    }

    public void addMoneyExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/addcoins <player> <count>§e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/addcoins <player> <count>§e!");
                break;
        }
    }

    public void getMoneyExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/coins <player>§e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/coins <player>§e!");
                break;
        }
    }

    public void getTimeExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/onlinetime <player> §e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/onlinetime <player>§e!");
                break;
        }
    }

    public void topMoneyExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/topcoins §e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/topcoins §e!");
                break;
        }
    }

    public void topKillsExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/topkills §e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/topkills §e!");
                break;
        }
    }

    public void topTimeExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou use too much arguments! Use: §c/toptime §e!");
                break;
            case 1:
                player.sendMessage("§f» §eFolosesti prea multe argumente! Foloseste: §c/toptime §e!");
                break;
        }
    }

    public void topDeathsExceptionMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("f» §eYou use too much arguments! Use: §c/topdeaths §e!");
                break;
            case 1:
                player.sendMessage("f» §eFolosesti prea multe argumente! Foloseste: §c/topdeaths §e!");
                break;
        }
    }

    public String sendJobPrincipalModal(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Job Category!\n" +
                        "               Choose your job";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "        Bine ai venit la categoria Job!\n" +
                        "      Alege-ti jobul";
                break;
        }
        return string;
    }

    public String sendInfoMessageJobs(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "            Welcome to Info Jobs!\n\n" +
                        "§c» §aMiner:\n" +
                        "§eWith that Job you can get money with ores.\n" +
                        "§eGet needed materials and you can get up: §750$\n\n" +
                        "§c» §aButcher: \n" +
                        "§eYou can make money with food and obiects from animals\n" +
                        "§eGet needed materials and you can get up: §775$\n\n" +
                        "§c» §aFarmer: \n" +
                        "§eYou can make money with this job if you plant.\n" +
                        "§eYou can get money too from Plants or Cultivate Seeds, any kind of Seeds, Saplings and Flowers.\n" +
                        "§eGet needed materials and you can get up: §730$\n\n" +
                        "§c» §aFisherman:\n" +
                        "§eWith this Job you can make money if you get fish!\n" +
                        "§eGet needed materials and you can get up: §745$";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Info Jobs!\n\n" +
                        "§c» §aMiner:\n" +
                        "§eCu acest loc de munca poti face rost de bani folosind minerale\n" +
                        "§eAduna tot ce ai nevoie si poti castiga pana la: §750$\n\n" +
                        "§c» §aButcher: \n" +
                        "§ePoti face bani cu acest job facand rost de mancare si obiecte de la animale\n" +
                        "§eStrange materialele necesare si poti castiga pana la: §775$\n\n" +
                        "§c» §aFarmer: \n" +
                        "§ePoti castiga bani plantand.\n" +
                        "§ePe langa acest lucru, poti sa castigi bani strangand seminte, orice tip de seminte, sapling-uri si flori\n" +
                        "§eStrange materialele necesare si poti castiga pana la: §730$\n\n" +
                        "§c» §aFisherman:\n" +
                        "§eCu acest job faci bani cu ajutorul pestilor!\n" +
                        "§eStrange materialele necesare si poti castiga pana la: §745$";
                break;
        }
        return string;
    }

    public void sendDropPartyEventMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                    The DropParty will start in 1 minute!§r\n" +
                        "                         Fireworks will appear in Arena.§r\n" +
                        "             Because you are Online you will get a Special Key!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                       DropParty-ul va incepe in 1 minut!§r\n" +
                        "                          Artificile vor aparea in Arena.§r\n" +
                        "               Pentru ca ai fost Online, vei primii o Cheie Speciala!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendDropPartySpawnedMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                          The DropParty started!§r\n" +
                        "                The fireworks and The Boss appeared in Arena.§r\n" +
                        "                     Everyone received a DropParty Key!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                              DropParty-ul a inceput!§r\n" +
                        "                    Artificile si The Boss au aparut in Arena.§r\n" +
                        "                      Toata lumea a primit DropParty Key!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendShutDownSoonMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                           Server will restart soon!§r\n" +
                        "                    Please accord attention to this message!§r\n" +
                        "                  You can enter again in 10 seconds after restart!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                           Serverul se va restarta curand!§r\n" +
                        "                        Va rog acordati atentie acestui mesaj!§r\n" +
                        "                     Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendShutDownInTenSecondsMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                         Server will restart §cin 10 seconds!§r\n" +
                        "                       Please accord attention to this message!§r\n" +
                        "                           You can enter again in 10 seconds!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                        Serverul se va restarta §cin 10 secunde!§r\n" +
                        "                            Va rog acordati atentie acestui mesaj!§r\n" +
                        "                      Vei putea intra dupa 10 secunde de la restart!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendDropPartyReceiveKeyMessage(Player player) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§f» §eYou have now +1 DropParty Key!§r");
                break;
            case 1:
                player.sendMessage("§f» §eAi +1 DropParty Key!§r");
                break;
        }
    }

    public void sendCoordsSwitchMessage(Player player, boolean bool) {
        int lang = Database.profileProxy.get(player.getName()).getLanguage();
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
        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
        if (profileProxy != null) {
            lang = Database.profileProxy.get(player.getName()).getLanguage();
        }
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
        int lang = 0;
        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
        if (profileProxy != null) {
            lang = Database.profileProxy.get(player.getName()).getLanguage();
        }
        switch (lang) {
            case 0:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fVote for awesome rewards", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6CHPE Factions §l§f«§r", "§r§fVoteaza pentru beneficii", 20, 20, 20);
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
