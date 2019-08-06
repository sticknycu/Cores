package nycuro.reports.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.reports.commands.data.ReportsCommand;

public class ReportsCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new ReportsCommand());
    }
}
