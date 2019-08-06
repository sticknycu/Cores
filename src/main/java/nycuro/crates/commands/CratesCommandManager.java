package nycuro.crates.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.crates.commands.data.SpawnFireworkCommand;

public class CratesCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new SpawnFireworkCommand());
    }
}
