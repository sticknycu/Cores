package nycuro.kits.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.kits.commands.data.KitsCommand;

public class KitsCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register(mainAPI, new KitsCommand());
    }
}
