package nycuro.economy.api;

import nycuro.api.API;
import nycuro.economy.commands.EconomyCommandManager;

public class EconomyAPI extends API {

    @Override
    public void registerCommands() {
        EconomyCommandManager.registerAll(getMainAPI());
    }
}
