package nycuro.ai.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.ai.commands.data.SpawnBossCommand;

public class AiCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new SpawnBossCommand());
    }
}
