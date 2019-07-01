package nycuro.tasks;

import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.utils.MechanicUtils;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class RegisterTopsTask extends Task {

    @Override
    public void onRun(int i) {
        MechanicUtils.getTops();
        Loader.registerTops();
        API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "gc");
    }
}
