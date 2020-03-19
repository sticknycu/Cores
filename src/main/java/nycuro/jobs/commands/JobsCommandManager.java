package nycuro.jobs.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.jobs.api.JobsAPI;
import nycuro.jobs.commands.data.JobCommand;
import nycuro.jobs.commands.data.WorkCommand;

public class JobsCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register(mainAPI, new JobCommand());
        map.register(mainAPI, new WorkCommand());
    }
}
