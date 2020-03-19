package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.level.Level;
import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;

import java.util.Collection;
import java.util.List;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.databaseAPI;

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
            for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
                message = messageAPI.messagesObject.translateMessage("broadcast.mobclear.started");
                player.sendMessage(message);
                try {
                    Thread.sleep(1000 * 30);
                } finally {
                    for (Level level : mainAPI.getServer().getLevels().values()) {
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
                    message = messageAPI.messagesObject.translateMessage("broadcast.mobclear.finished");
                    player.sendMessage(message);
                }
            }
            // Clear the damn weather shit
            for (Level level : mainAPI.getServer().getLevels().values()) {
                if (level.isThundering()) {
                    level.setThundering(false);
                }
                if (level.isRaining()) {
                    level.setRaining(false);
                }
            }
            // Check for deletion old reports
            List<String> names = databaseAPI.getPlayerMap();
            for (String name : names) {
                Collection<Long> timers = databaseAPI.getTimersPlayerReport(name);
                for (long time : timers) {
                    if ((1000 * 60 * 60 * 48 - (System.currentTimeMillis() - time)) <= 0) {
                        databaseAPI.deleteReport(name);
                    }
                }
            }
        } catch (Exception e) {
            //
        }
    }
}

