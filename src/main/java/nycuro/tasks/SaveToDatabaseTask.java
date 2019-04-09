package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Core;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

import java.util.Map;
import java.util.UUID;

public class SaveToDatabaseTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            API.getMessageAPI().sendShutDownSoonMessage(player);
        }
        try {
            Thread.sleep(20000);
            for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
                API.getMessageAPI().sendShutDownInTenSecondsMessage(player);
            }
            Thread.sleep(10000);
            for (Map.Entry<UUID, Profile> map : Database.profile.entrySet()) {
                API.getDatabase().saveDatesPlayer(map.getKey(), map.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Core.log("All data saved!");
            API.getMainAPI().getServer().dispatchCommand(new ConsoleCommandSender(), "stop");
        }
    }
}
