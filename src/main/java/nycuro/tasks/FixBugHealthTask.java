package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.API;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class FixBugHealthTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            player.setHealth(player.getHealth());
        }
    }
}
