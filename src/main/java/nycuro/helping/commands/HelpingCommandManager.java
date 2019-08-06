package nycuro.helping.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.helping.api.HelpingAPI;
import nycuro.helping.commands.data.HelpOpCommand;
import nycuro.helping.commands.data.StaffChatCommand;

public class HelpingCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new HelpOpCommand());
        map.register("SkyblockCORE", new StaffChatCommand());
    }
}
