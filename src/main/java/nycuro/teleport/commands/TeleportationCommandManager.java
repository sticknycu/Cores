package nycuro.teleport.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.teleport.commands.data.*;

public class TeleportationCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register(mainAPI, new TPACommand());
        map.register(mainAPI, new TPAAllCommand());
        map.register(mainAPI, new TPAcceptCommand());
        map.register(mainAPI, new TPAHereCommand());
        map.register(mainAPI, new TPAllCommand());
        map.register(mainAPI, new TPDenyCommand());
        map.register(mainAPI, new TPHereCommand());
    }
}
