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

        map.register(mainAPI, new HubCommand());
        map.register(mainAPI, new SpawnCommand());
        map.register(mainAPI, new ArenaCommand());
        map.register(mainAPI, new LevelCommand());

        map.register(mainAPI, new CoordsCommand());
        map.register(mainAPI, new OnlineTimeCommand());
        map.register(mainAPI, new LangCommand());
        map.register(mainAPI, new SettingsCommand());
        map.register(mainAPI, new StatsCommand());

        map.register(mainAPI, new TopKillsCommand());
        map.register(mainAPI, new TopDeathsCommand());
        map.register(mainAPI, new TopTimeCommand());

        map.register(mainAPI, new UtilsCommand());
    }
}
