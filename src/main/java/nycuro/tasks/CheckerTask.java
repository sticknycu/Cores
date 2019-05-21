package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.api.UtilsAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CheckerTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            int x = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getX();
            int y = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getY();
            int z = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getZ();
            Vector3 vector3 = new Vector3(x, y, z);
            if (player.getLevel().getName().equalsIgnoreCase("world") && player.getPosition().distance(vector3) <= 300 && !player.isOp()) {
                Loader.isOnSpawn.put(player.getName(), true);
            } else {
                Loader.isOnSpawn.put(player.getName(), false);
            }

            if ((x >= 7500 || x <= -7500) || (z >= 7500 || z <= -7500)) {
                Loader.isOnBorder.put(player.getName(), false);
            } else {
                Loader.isOnBorder.put(player.getName(), true);
            }

            // Border Check
            if (!Loader.isOnBorder.getBoolean(player.getName())) {
                API.getMessageAPI().sendBorderMessage(player);
                double yaw = player.getYaw();
                double xx = Math.cos(yaw);
                double zz = Math.sin(yaw);
                player.setMotion(new Vector3(-xx, 0, -zz));
            }

            // RandomTP
            Location location = player.getLocation();
            if (location.getLevelBlock().getId() == BlockID.NETHER_PORTAL) {
                if (UtilsAPI.teleported) return;
                API.getUtilsAPI().handleRandomTeleport(player);
            }
        }
    }
}
