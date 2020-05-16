package nycuro.tasks;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.scheduler.Task;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class RestartTask extends Task {

    @Override
    public void onRun(int i) {
        mainAPI.getServer().dispatchCommand(new ConsoleCommandSender(), "stop");
    }
}

