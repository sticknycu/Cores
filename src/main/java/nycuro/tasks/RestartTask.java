package nycuro.tasks;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.api.API;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class RestartTask extends Task {

    @Override
    public void onRun(int i) {
        API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "stop");
    }
}

