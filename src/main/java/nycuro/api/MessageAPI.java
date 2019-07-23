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
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.kits.type.TypeKit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MessageAPI {

    private String STRING = "";
    private String STRING_BOSSBAR = "";

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

    public String getNextScoreboard(Player player, long time) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§7Next DropParty: §6" + Loader.time(time);
                break;
            case 1:
                STRING = "§7Urmatorul DropParty: §6" + Loader.time(time);
                break;
        }
        return STRING;
    }

    public String getNameScoreboard(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String sendWitherSpawnMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String getMessageCombatLogger(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§aYou are no longer in combat, you may now logout and run commands.";
                break;
            case 1:
                STRING = "§aDe acum nu mai esti in lupta. Acum poti sa te deloghezi si sa folosesti comenzile.";
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

    public String sendMobDespawnMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String setCombatMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "§eYou have entered combat, logging out now will cause your death. Please wait §613 §eseconds.";
                break;
            case 1:
                STRING = "§eTocmai ai intrat in lupta. Daca te vei deloga, vei muri. Te rog asteapta §613 §esecunde.";
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String teleportBossArenaMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                      Hello!\n" +
                        "         Welcome to Teleport Arena!\n\n" +
                        "§c» §aWhat is 'Teleport Arena'?\n" +
                        "§eTeleport Arena is an option for teleporting to Arena where Boss is spawning.\n" +
                        "§eYou can do that only if you have level 10+\n" +
                        "§eYou will cannot use /tp and other commands for teleporting here. Just /spawn.\n" +
                        "§eBoss is spawning every day at 21:00, 9 PM, GT+2\n" +
                        "§eBe careful, PVP is on!\n" +
                        "§eIf you don't want to be teleported, tap on X!\n" +
                        "§eHave a nice day!";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "      Bine ai venit la Teleport Arena!\n\n" +
                        "§c» §aCe inseamna de fapt 'Teleport Arena'?:\n" +
                        "§eTeleport Arena este o optiune pentru a te teleporta la Arena unde se spawneaza Boss-ul.\n" +
                        "§ePoti face asta doar daca ai nivel 10+\n" +
                        "§eNu o sa poti folosi nici un fel de comanda de teleport, precum /tp. Doar /spawn.\n" +
                        "§eBoss-ul se spawneaza in fiecare seara la ora 9.\n" +
                        "§eAi grija, PVP-ul este ON!\n" +
                        "§eDaca nu vrei sa te teleportezi, apasa sus in dreapta pe X!\n" +
                        "§eSa ai o zi buna!";
                break;
        }
        return STRING;
    }

    public String teleportPvPArenaMessages(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING = "                      Hello!\n" +
                        "         Welcome to Teleport Arena!\n\n" +
                        "§c» §aWhat is 'Teleport Arena'?\n" +
                        "§eTeleport Arena is an option for teleporting to PvP Arena.\n" +
                        "§eYou can do that only if you have level 5+\n" +
                        "§eYou will cannot use /tp and other commands for teleporting here. Just /spawn.\n" +
                        "§eBe careful, PVP is on!\n" +
                        "§eIf you don't want to be teleported, tap on X!\n" +
                        "§eHave a nice day!";
                break;
            case 1:
                STRING = "                      Salut!\n" +
                        "      Bine ai venit la Teleport Arena!\n\n" +
                        "§c» §aCe inseamna de fapt 'Teleport Arena'?:\n" +
                        "§eTeleport Arena este o optiune pentru a te teleporta la PvP Arena.\n" +
                        "§ePoti face asta doar daca ai nivel 5+\n" +
                        "§eNu o sa poti folosi nici un fel de comanda de teleport, precum /tp. Doar /spawn.\n" +
                        "§eAi grija, PVP-ul este ON!\n" +
                        "§eDaca nu vrei sa te teleportezi, apasa sus in dreapta pe X!\n" +
                        "§eSa ai o zi buna!";
                break;
        }
        return STRING;
    }

    public String sendTooMuchWithers(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        if (API.getMainAPI().getServer().lookupName(player.getName()).isPresent()) {
            String group = giveMeADamnUser(API.getMainAPI().getServer().lookupName(player.getName()).get()).getPrimaryGroup().toUpperCase();
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
        }
        return TextFormat.colorize(rank);
    }

    public String getRankScoreboard(Player player) {
        String rank = getRank(player);
        return "§7| §fRank: §6" + rank + "  ";
    }

    private String getOS(Player player) {
        switch (player.getLoginChainData().getDeviceOS()) {
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
        ProfileProxy profileProxy = Database.profileProxy.get(commandSender.getName());
        Database.addUnAsyncDatesPlayerFactions(player.getName());
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        String faction = FPlayers.i.get(player.getName()).getFaction().getTag();
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date firstPlay = new Date(player.getFirstPlayed());
        Date lastPlay = new Date(player.getLastPlayed());
        switch (profileProxy.getLanguage()) {
            case 0:
                STRING = "         Profile Info:\n\n" +
                        "§c» §aName: §e" + player.getName() + "\n" +
                        "§eRank: §6" + getRank(player) + "\n" +
                        "§eLevel: §6" + profileSkyblock.getLevel() + "\n" +
                        "§eExperience: §6" + profileSkyblock.getExperience() + "/" + profileSkyblock.getNecesary() + "\n" +
                        "§eFirst Join: §6" + simple.format(firstPlay) + "\n" +
                        "§eOnline: §6" + (player.isOnline() ? "§3YES§6" : simple.format(lastPlay))  + "\n" +
                        (player.isOnline() ? ("§eOnline on this session: §6" + Loader.time(System.currentTimeMillis() - API.getMainAPI().played.getLong(player.getName()))) + "\n" : "") +
                        "§eOnline Time: §6" + Loader.time(profileSkyblock.getTime()) + "\n" +
                        "§eDollars: §6" + profileSkyblock.getDollars() + "\n" +
                        "§eGems: §6" + profileProxy.getGems() + "\n" +
                        "§eKills: §6" + profileSkyblock.getKills() + "\n" +
                        "§eDeaths: §6" + profileSkyblock.getDeaths() + "\n" +
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
                        "§eNivel: §6" + profileSkyblock.getLevel() + "\n" +
                        "§eExperienta: §6" + profileSkyblock.getExperience() + "/" + profileSkyblock.getNecesary() + "\n" +
                        "§ePrima data cand ai intrat pe Sectiune: §6" + simple.format(firstPlay) + "\n" +
                        "§eOnline: §6" + (player.isOnline() ? "§3DA§6" : simple.format(lastPlay))  + "\n" +
                        (player.isOnline() ? ("§eOnline pe aceasta sesiune: §6" + Loader.time(System.currentTimeMillis() - API.getMainAPI().played.getLong(player.getName()))) + "\n" : "") +
                        "§eTimp Online: §6" + Loader.time(profileSkyblock.getTime()) + "\n" +
                        "§eDolari: §6" + profileSkyblock.getDollars() + "\n" +
                        "§eGems: §6" + profileProxy.getGems() + "\n" +
                        "§eKills: §6" + profileSkyblock.getKills() + "\n" +
                        "§eDeaths: §6" + profileSkyblock.getDeaths() + "\n" +
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendReceiveKitMessage(Player player, TypeKit typeKit) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWow! You got §6" + typeKit + " §e!");
                break;
            case 1:
                player.sendMessage("§7» §eWow! Ai primit §6" + typeKit + " §e!");
                break;
        }
    }

    public void sendFullInventoryMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7(§e!§7) §eInfo: §6Your inventory it's full or you have equiped an armor, so you can't do this!");
                break;
            case 1:
                player.sendMessage("§7(§e!§7) §eInfo: §6Ai inventarul plin sau porti o armura, deci nu poti face aceasta actiune!");
                break;
        }
    }

    public void sendCommandSpawnMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendKitsMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§7» §eWrong arguments! Please use: §6/kits.");
                break;
            case 1:
                player.sendMessage("§7» §eArgument gresit! Te rog foloseste: §6/kits.");
                break;
        }
    }

    public void sendExceptionServersMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendUnsuficientMoneyMessage(Player player, double needed) {
        double coins = Database.profileProxy.get(player.getName()).getGems();
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendCustomPermissionMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendRandomTPMessage(Player player, int x, int z) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendReceiveJobMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        int job = Database.profileSkyblock.get(player.getName()).getJob();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendHitBowMessage(Entity entity, Player damager) {
        int lang = Database.profileProxy.getOrDefault(damager.getName(), new ProfileProxy(damager.getName(), 0,0,0,0)).getLanguage();
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
            int lang = Database.profileProxy.getOrDefault(commandSender.getName(), new ProfileProxy(commandSender.getName(), 0,0,0,0)).getLanguage();
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
            int lang = Database.profileProxy.getOrDefault(commandSender.getName(), new ProfileProxy(commandSender.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String getMessageInArenaBossBar(Player player, double bosshp) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                STRING_BOSSBAR = "    §6§l»§r-- Boss §6§l§r --§6§l« §r\n\n      §rHP: §6" + bosshp + "§7/" + "§6100";
                break;
            case 1:
                STRING_BOSSBAR = "   §6§l»§r-- Boss §6§l§r --§6§l« §r\n\n  §rViata: §6" + bosshp + "§7/" + "§6100";
                break;
        }
        return STRING_BOSSBAR;
    }

    public void getSelfMoneyMessage(Player player, double money) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
            int langSender = Database.profileProxy.getOrDefault(commandSender.getName(), new ProfileProxy(commandSender.getName(), 0, 0, 0,0)).getLanguage();
            int langGiver = Database.profileProxy.getOrDefault(giver.getName(), new ProfileProxy(giver.getName(), 0, 0, 0,0)).getLanguage();
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
            int lang = Database.profileProxy.getOrDefault(commandSender.getName(), new ProfileProxy(commandSender.getName(), 0, 0, 0,0)).getLanguage();
            int language = Database.profileProxy.getOrDefault(giver.getName(), new ProfileProxy(giver.getName(), 0, 0, 0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String sendArenaPrincipalModal(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Arena Category!\n" +
                        "           Choose what you want to do";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Arena!\n" +
                        "    Alege ce doresti sa faci de acum";
                break;
        }
        return string;
    }

    public String sendKitPrincipalModal(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Kit Category!\n" +
                        "           Choose what you want to do";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Kituri!\n" +
                        "    Alege ce doresti sa faci de acum";
                break;
        }
        return string;
    }

    public String sendHomesPrincipalModal(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Home Category!\n" +
                        "           Choose what you want to do";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Home!\n" +
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

    public String sendHomeList(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                       Hello!\n" +
                        "               Welcome to Home List!";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "     Bine ai venit la categoria Home List";
                break;
        }
        return string;
    }

    public String sendInfoMessageJobs(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public String sendInfoMessageKits(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "            Welcome to Info Kits!\n\n" +
                        "§c» §aEnchanted Starter:\n" +
                        "§eCooldown: 24h.\n" +
                        "§eCost: §71500$\n" +
                        "§eItems: §7Full Lether Enchanted Armor, Stone Tools, 32 Breads\n\n" +
                        "§c» §aGuardian:\n" +
                        "§eCooldown: 24h.\n" +
                        "§eCost: §71500$\n\n" +
                        "§eItems: §7Full Gold Enchanted Armor, Iron Tools, 32 Breads, 16 TNT, 64 Obsidian\n\n";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Info Kits!\n\n" +
                        "§c» §aEnchanted Starter:\n" +
                        "§eCooldown: 24h.\n" +
                        "§eCosta: §71500$\n\n" +
                        "§eIteme: §7Echipament Lether Enchantat, Unele de Piatra, 32 Paini\n\n" +
                        "§c» §aGuardian:\n" +
                        "§eCooldown: 24h.\n" +
                        "§eCosta: §71500$\n\n" +
                        "§eIteme: §7Echipament Gold Enchantat, Unelte de Iron, 32 Paini, 16 TNT, 64 Obsidian\n\n";
                break;
        }
        return string;
    }

    public String sendInputNameReport(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "Please put here name of Player who want to report";
                break;
            case 1:
                string = "Te rog introdu aici numele Jucatorului pe care doresit sa il raportezi";
                break;
        }
        return string;
    }

    public String sendInputNameHome(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "Please put here name of Home you want to create";
                break;
            case 1:
                string = "Te rog introdu aici numele Home-ului pe care vrei sa il creezi";
                break;
        }
        return string;
    }

    public String sendHomeExistsMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §cThis home already exists!";
                break;
            case 1:
                string = "§f» §cAcest Home exista deja!";
                break;
        }
        return string;
    }

    public String sendInputReasonReport(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "Please put here reason why you report player with specifications what them do";
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
                string = "§f» §aYou report player " + name + " succesfully!";
                break;
            case 1:
                string = "§f» §aAi raportat pe " + name + " cu succes!";
                break;
        }
        return string;
    }

    public String sendCreatedHomeSuccesfully(Player player, int x, int y, int z) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou succesfully created Home at: §eX: " + x + " Y: " + y + " Z: " + z + "§a!";
                break;
            case 1:
                string = "§f» §aAi creat cu succes un Home la: §eX: " + x + " Y: " + y + " Z: " + z + "§a!";
                break;
        }
        return string;
    }

    public String sendTeleportedHomeSuccesfully(Player player, int x, int y, int z) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou succesfully teleported to: §eX: " + x + " Y: " + y + " Z: " + z + "§a!";
                break;
            case 1:
                string = "§f» §aAi fost teleportat cu succes la: §eX: " + x + " Y: " + y + " Z: " + z + "§a!";
                break;
        }
        return string;
    }

    public String sendDeleteReportMessage(Player player, String name) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou succesfully deleted " + name + " report!";
                break;
            case 1:
                string = "§f» §aAi sters cu succes reportul lui " + name + "!";
                break;
        }
        return string;
    }

    public String sendArenaWarningMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §cYou need have level 10+ to enter here!";
                break;
            case 1:
                string = "§f» §cAi nevoie de cel putin nivelul 10 pentru a intra aici!";
                break;
        }
        return string;
    }

    public String sendTooMuchHomesMessage(Player player, int count) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §eYou have too much homes! Count: §a" + count + "§e! Please open /shop and buy more from Premium Shop!";
                break;
            case 1:
                string = "§f» §eAi prea multe home-uri! Cantitate: §a" + count + "§e! Te rog deschide /shop si cumpara mai multe din Premium Shop!";
                break;
        }
        return string;
    }

    public String sendRemoveHomeSuccesfully(Player player, String homeName) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "§f» §aYou have succesfully deleted home: §e" + homeName;
                break;
            case 1:
                string = "§f» §aAi sters cu succes Home-ul: §e" + homeName;
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

    public String sendInfoMessageHome(Player player, String home) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "          Welcome to Home " + home + "!\n\n" +
                        "§eTo teleport to home just use §a'Teleport'.\n" +
                        "§eTo delete a home just use §a'Delete Home'.\n";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Home" + home + "!\n\n" +
                        "§ePentru a te teleporta la home foloseste §a'Teleport'.\n" +
                        "§ePentru a sterge Home-ul foloseste §a'Delete Home'.\n";
                break;
        }
        return string;
    }

    public String sendInfoMessageArena(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "          Welcome to Arena Mechanic!\n\n" +
                        "§c» §aHow to Teleport to Boss?\n" +
                        "§eFor teleporting to boss just tab on 'Boss Arena'.\n" +
                        "§c» §aHow to Teleport to PvP?\n" +
                        "§eFor teleporting to PvP just tab on 'PvP Arena'";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Arena Mechanic!\n\n" +
                        "§c» §aCum te teleportezi la Boss?\n" +
                        "§ePentru a te teleporta la boss trebuie sa apesi pe fereastra 'PvP Arena'.\n" +
                        "§c» §aCum te teleportezi la PvP?\n" +
                        "§ePentru a te teleporta la PvP trebuie sa apesi pe fereastra 'PvP Arena'";
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
                        "          Welcome to Reports Mechanic!\n\n" +
                        "§c» §aHow to report a Player?\n" +
                        "§eTo report a player just tab on 'Report'.\n" +
                        "§eYou need to complete all available fields.\n\n" +
                        "§cATENTION! \n" +
                        "§eIf you have a proof, we're waiting you on Discord\n" +
                        "§eYou can report a player on game, but on Discord too!\n\n" +
                        "§c» §aHow to report on Discord? \n" +
                        "§eEnter on Discord and use #reports-en #reports";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Reports Mechanic!\n\n" +
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

    public String sendInfoMessageHomes(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        String string = "";
        switch (lang) {
            case 0:
                string = "                      Hello!\n" +
                        "          Welcome to Home Mechanic!\n\n" +
                        "§c» §aHow to make a Home?\n" +
                        "§eTo make a Home just tab on 'Create a Home'.\n" +
                        "§cATENTION! \n" +
                        "§eYou can have only 2 Home. For more, you need use Premium Shop from /shop\n" +
                        "§c» §aHow to remove a Home? \n" +
                        "§eTo remove a Home just tab on 'Manage Homes', select Home and then tab on 'Delete Home'";
                break;
            case 1:
                string = "                      Salut!\n" +
                        "          Bine ai venit la Home Mechanic!\n\n" +
                        "§c» §aCum creez un Home?\n" +
                        "§eDoar apasa pe 'Create a Home'.\n" +
                        "§cATENTIE! \n" +
                        "§ePoti crea doar 2 Home-uri. Pentru mai multe trebuie sa accesezi Home Premium de la /shop\n" +
                        "§c» §aCum sterg un Home? \n" +
                        "§ePentru a sterge un Home foloseste 'Manage Homes', selecteaza Home-ul si dupa apasa pe 'Sterge Home'";
                break;
        }
        return string;
    }

    public void sendDropPartyEventMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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

    public void sendBossSpawnedMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
        switch (lang) {
            case 0:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                             Is 21:00 at Romania!§r\n" +
                        "                           The Boss appeared in Arena.§r\n" +
                        "                           Everyone let's go to /arena!§r" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
            case 1:
                player.sendMessage("§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n" +
                        "                                                                                                            \n" +
                        "                              Este 21:00 in Romania!§r\n" +
                        "                           The Boss au aparut in Arena.§r\n" +
                        "                         Toata lumea sa mearga la /arena!§r\n" +
                        "                                                                                                            \n" +
                        "§l§6»§r§r--------------- §8( §6§lCHPE §r§l» §eFactions §r§8) §r---------------§6§l«§r\n");
                break;
        }
    }

    public void sendShutDownSoonMessage(Player player) {
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
        int lang = Database.profileProxy.getOrDefault(player.getName(), new ProfileProxy(player.getName(), 0,0,0,0)).getLanguage();
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
                player.sendTitle("§l§f» §r§6Reports Mechanic §l§f«§r", "§r§fThere are §c" + count + "§f reports not solved!", 20, 20, 20);
                break;
            case 1:
                player.sendTitle("§l§f» §r§6Reports Mechanic §l§f«§r", "§r§fExista §c" + count + "§f raporturi nerezolvate!", 20, 20, 20);
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
