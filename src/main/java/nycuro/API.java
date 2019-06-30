package nycuro;

import nycuro.ai.AiAPI;
import nycuro.api.*;
import nycuro.crate.CrateAPI;
import nycuro.database.Database;
import nycuro.dropparty.DropPartyAPI;
import nycuro.utils.vote.VoteSettings;

/**
 * author: NycuRO
 * FactionsCore Project
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

    public static Database database;

    public static VoteSettings voteSettingsAPI;

    public static ReportAPI reportAPI;

    public static HomeAPI homeAPI;

    public static SlotsAPI slotsAPI;

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

    public static SlotsAPI getSlotsAPI() {
        return slotsAPI;
    }
}
