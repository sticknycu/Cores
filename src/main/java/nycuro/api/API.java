package nycuro.api;

import nycuro.Loader;
import nycuro.ai.AiAPI;
import nycuro.api.data.MechanicAPI;
import nycuro.chat.api.MessageAPI;
import nycuro.combat.api.CombatAPI;
import nycuro.crate.CrateAPI;
import nycuro.database.DatabaseMySQL;
import nycuro.dropparty.api.DropPartyAPI;
import nycuro.home.api.HomeAPI;
import nycuro.jobs.api.JobsAPI;
import nycuro.kits.api.KitsAPI;
import nycuro.reports.api.ReportAPI;
import nycuro.shop.api.ShopAPI;
import nycuro.utils.api.UtilsAPI;
import nycuro.utils.vote.VoteSettings;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class API {

    public static Loader mainAPI;

    public static MechanicAPI mechanicAPI;

    public static UtilsAPI utilsAPI;

    public static KitsAPI kitsAPI;

    public static MessageAPI messageAPI;

    public static ShopAPI shopAPI;

    public static JobsAPI jobsAPI;

    public static AiAPI aiAPI;

    public static CrateAPI crateAPI;

    public static DropPartyAPI dropPartyAPI;

    public static CombatAPI combatAPI;

    public static DatabaseMySQL database;

    public static VoteSettings voteSettingsAPI;

    public static ReportAPI reportAPI;

    public static HomeAPI homeAPI;

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

    public static DatabaseMySQL getDatabase() {
        return database;
    }

    public static VoteSettings getVoteSettingsAPI() { return voteSettingsAPI; }

    public static ReportAPI getReportAPI() { return reportAPI; }

    public static HomeAPI getHomeAPI() { return homeAPI; }
}
