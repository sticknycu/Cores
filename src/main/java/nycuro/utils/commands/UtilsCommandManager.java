package nycuro.utils.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.utils.commands.data.settings.*;
import nycuro.utils.commands.data.teleportation.ArenaCommand;
import nycuro.utils.commands.data.teleportation.HubCommand;
import nycuro.utils.commands.data.teleportation.SpawnCommand;
import nycuro.utils.commands.data.tops.TopDeathsCommand;
import nycuro.utils.commands.data.tops.TopKillsCommand;
import nycuro.utils.commands.data.tops.TopTimeCommand;
import nycuro.utils.commands.data.utils.UtilsCommand;

public class UtilsCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();

        map.register("SkyblockCORE", new HubCommand());
        map.register("SkyblockCORE", new SpawnCommand());
        map.register("SkyblockCORE", new ArenaCommand());
        map.register("SkyblockCORE", new LevelCommand());

        map.register("SkyblockCORE", new CoordsCommand());
        map.register("SkyblockCORE", new OnlineTimeCommand());
        map.register("SkyblockCORE", new LangCommand());
        map.register("SkyblockCORE", new SettingsCommand());
        map.register("SkyblockCORE", new StatsCommand());

        map.register("SkyblockCORE", new TopKillsCommand());
        map.register("SkyblockCORE", new TopDeathsCommand());
        map.register("SkyblockCORE", new TopTimeCommand());

        map.register("SkyblockCORE", new UtilsCommand());
    }
}
