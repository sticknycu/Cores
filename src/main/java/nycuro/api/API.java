package nycuro.api;

import cn.nukkit.utils.TextFormat;
import nycuro.Loader;
import nycuro.ai.AiAPI;
import nycuro.api.data.MechanicAPI;
import nycuro.combat.api.CombatAPI;
import nycuro.crates.api.CrateAPI;
import nycuro.database.Database;
import nycuro.dropparty.api.DropPartyAPI;
import nycuro.economy.api.EconomyAPI;
import nycuro.helping.api.HelpingAPI;
import nycuro.homes.api.HomeAPI;
import nycuro.jobs.api.JobsAPI;
import nycuro.kits.api.KitsAPI;
import nycuro.messages.api.MessageAPI;
import nycuro.reports.api.ReportAPI;
import nycuro.shop.api.ShopAPI;
import nycuro.teleport.api.TeleportationAPI;
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

import java.util.concurrent.TimeUnit;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public abstract class API {

    public static ShopAPI shopAPI;

    public static Loader mainAPI;

    public static MechanicAPI mechanicAPI;

    public static UtilsAPI utilsAPI;

    public static KitsAPI kitsAPI;

    public static MessageAPI messageAPI;

    public static JobsAPI jobsAPI;

    public static AiAPI aiAPI;

    public static CrateAPI crateAPI;

    public static DropPartyAPI dropPartyAPI;

    public static CombatAPI combatAPI;
    public static TeleportationAPI teleportationAPI;

    public static Database database;

    public static VoteSettings voteSettingsAPI;

    public static ReportAPI reportAPI;

    public static HomeAPI homeAPI;
    public static EconomyAPI economyAPI;
    public static HelpingAPI helpingAPI;

    public static TeleportationAPI getTeleportationAPI() {
        return teleportationAPI;
    }

    public static Loader getMainAPI() {
        return mainAPI;
    }

    public static MechanicAPI getMechanicAPI() {
        return mechanicAPI;
    }

    public static UtilsAPI getUtilsAPI() {
        return utilsAPI;
    }

    public static KitsAPI getKitsAPI() {
        return kitsAPI;
    }

    public static MessageAPI getMessageAPI() {
        return messageAPI;
    }

    public static ShopAPI getShopAPI() {
        return shopAPI;
    }

    public static JobsAPI getJobsAPI() {
        return jobsAPI;
    }

    public static AiAPI getAiAPI() {
        return aiAPI;
    }

    public static CrateAPI getCrateAPI() {
        return crateAPI;
    }

    public static DropPartyAPI getDropPartyAPI() {
        return dropPartyAPI;
    }

    public static CombatAPI getCombatAPI() { return combatAPI; }

    public static Database getDatabase() {
        return database;
    }

    public static VoteSettings getVoteSettingsAPI() { return voteSettingsAPI; }

    public static ReportAPI getReportAPI() { return reportAPI; }

    public static HomeAPI getHomeAPI() { return homeAPI; }

    public static EconomyAPI getEconomyAPI() {
        return economyAPI;
    }

    public static HelpingAPI getHelpingAPI() {
        return helpingAPI;
    }

    public abstract void registerCommands();

    public static void log(String s) {
        API.getMainAPI().getServer().getLogger().info(TextFormat.colorize("&a" + s));
    }

    public static String time(long time) {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(time);
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(time) - hours * 60);
        int MINS = (int) TimeUnit.MILLISECONDS.toMinutes(time);
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time) - MINS * 60);
        return hours + ":" + minutes + ":" + seconds;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
