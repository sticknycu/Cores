package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.level.Level;
import cn.nukkit.scheduler.Task;
import nycuro.api.API;

import java.util.Collection;
import java.util.List;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ClearLagTask extends Task {

    @Override
    public void onRun(int i) {
        String message = "";
        try {
            for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                message = API.getMessageAPI().sendMobDespawnMessage(player);
                player.sendMessage(message);
                try {
                    Thread.sleep(1000 * 30);
                } finally {
                    for (Level level : API.getMainAPI().getServer().getLevels().values()) {
                        for (Entity entity : level.getEntities()) {
                            switch (entity.getNetworkId()) {
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 52:
                                case 69:
                                case 89:
                                    entity.close();
                                    break;
                            }
                            if (entity instanceof EntityItem) entity.close();
                        }
                    }
                    message = API.getMessageAPI().sendMobDespawnFinishMessage(player);
                    player.sendMessage(message);
                }
            }
            // Clear the damn weather shit
            for (Level level : API.getMainAPI().getServer().getLevels().values()) {
                if (level.isThundering()) {
                    level.setThundering(false);
                }
                if (level.isRaining()) {
                    level.setRaining(false);
                }
            }
            // Check for deletion old reports
            List<String> names = API.getDatabase().getPlayerMap();
            for (String name : names) {
                Collection<Long> timers = API.getDatabase().getTimersPlayerReport(name);
                for (long time : timers) {
                    if ((1000 * 60 * 60 * 48 - (System.currentTimeMillis() - time)) <= 0) {
                        API.getDatabase().deleteReport(name);
                    }
                }
            }
        } catch (Exception e) {
            //
        }
    }
}

