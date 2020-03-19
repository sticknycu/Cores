package nycuro.homes.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.homes.commands.data.HomeCommand;

public class HomesCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register(mainAPI, new HomeCommand());
    }
}
