package nycuro.teleport.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.teleport.commands.data.*;

public class TeleportationCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new TPACommand());
        map.register("SkyblockCORE", new TPAAllCommand());
        map.register("SkyblockCORE", new TPAcceptCommand());
        map.register("SkyblockCORE", new TPAHereCommand());
        map.register("SkyblockCORE", new TPAllCommand());
        map.register("SkyblockCORE", new TPDenyCommand());
        map.register("SkyblockCORE", new TPHereCommand());
    }
}
