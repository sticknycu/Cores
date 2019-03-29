package nycuro;

import nycuro.api.MechanicAPI;
import nycuro.api.MessageAPI;
import nycuro.crate.CrateAPI;
import nycuro.database.Database;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class API {

    public static Loader mainAPI;
    public static MechanicAPI mechanicAPI;
    public static MessageAPI messageAPI;
    public static CrateAPI crateAPI;
    public static Database database;

    public static Loader getMainAPI() {
        return mainAPI;
    }

    public static MechanicAPI getMechanicAPI() {
        return mechanicAPI;
    }

    public static MessageAPI getMessageAPI() {
        return messageAPI;
    }

    public static CrateAPI getCrateAPI() {
        return crateAPI;
    }

    public static Database getDatabase() {
        return database;
    }
}
