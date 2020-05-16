package nycuro.api;

import cn.nukkit.utils.TextFormat;
import nycuro.Loader;
import nycuro.abuse.settings.AbuseAPI;
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
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

import java.util.concurrent.TimeUnit;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class API {

    public static Loader mainAPI;
    public static AbuseAPI abuseAPI;
    public static MechanicAPI mechanicAPI;
    public static UtilsAPI utilsAPI;
    public static KitsAPI kitsAPI;
    public static MessageAPI messageAPI;
    public static JobsAPI jobsAPI;
    public static AiAPI aiAPI;
    public static CrateAPI crateAPI;
    public static DropPartyAPI dropPartyAPI;
    public static CombatAPI combatAPI;
    public static Database databaseAPI;
    public static VoteSettings voteSettingsAPI;
    public static ReportAPI reportAPI;
    public static HomeAPI homeAPI;
    public static EconomyAPI economyAPI;
    public static HelpingAPI helpingAPI;

    public static void log(String s) {
        mainAPI.getLogger().info(TextFormat.colorize("&a" + s));
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
