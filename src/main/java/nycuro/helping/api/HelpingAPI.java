package nycuro.helping.api;

import nycuro.helping.commands.HelpingCommandManager;

import static nycuro.api.API.mainAPI;

public class HelpingAPI {

    public void registerCommands() {
        HelpingCommandManager.registerAll(mainAPI);
    }
}
