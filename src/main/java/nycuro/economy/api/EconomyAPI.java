package nycuro.economy.api;

import nycuro.economy.commands.EconomyCommandManager;

import static nycuro.api.API.mainAPI;

public class EconomyAPI {

    public void registerCommands() {
        EconomyCommandManager.registerAll(mainAPI);
    }
}
