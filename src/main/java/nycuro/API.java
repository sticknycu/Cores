package nycuro;

import nycuro.database.Database;

/**
 * author: NycuRO
 * ProxyCore Project
 * API 1.0.0
 */
public class API {

    public static Loader mainAPI;
    public static Database database;

    public static Loader getMainAPI() {
        return mainAPI;
    }
    public static Database getDatabase() {
        return database;
    }
}
