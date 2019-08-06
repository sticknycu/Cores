package nycuro.helping.api;

import nycuro.api.API;
import nycuro.helping.commands.HelpingCommandManager;

public class HelpingAPI extends API {

    @Override
    public void registerCommands() {
        HelpingCommandManager.registerAll(getMainAPI());
    }
}
