package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class CheckerTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            int x = (int) player.getX();
            int y = (int) player.getY();
            int z = (int) player.getZ();
            Vector3 vector3 = new Vector3(x, y, z);
            if (player.getPosition().distance(vector3) <= 300 && player.getY() <= 29) {
                Loader.isOnSpawn.put(player.getName(), true);
            } else {
                Loader.isOnSpawn.put(player.getName(), false);
            }

            // Border Check
            if (!Loader.isOnSpawn.getBoolean(player.getName())) {
                API.getMessageAPI().sendBorderMessage(player);
                double yaw = player.getYaw();
                double xx = Math.cos(yaw);
                double zz = Math.sin(yaw);
                player.setMotion(new Vector3(-xx, 0, -zz));
            }
        }
    }
}