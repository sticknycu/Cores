package nycuro.tasks;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.Loader;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class RegisterTopsTask extends Task {

    @Override
    public void onRun(int i) {
        Loader.registerTops();
        mainAPI.getServer().dispatchCommand(new ConsoleCommandSender(), "gc");
    }
}
