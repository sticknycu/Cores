package nycuro.utils.commands.data.teleportation;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class SpawnCommand extends CommandBaseUtils {

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getMessageAPI().sendCommandCooldownSpawnMessage(player);
        player.getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMechanicAPI().sendToSpawn(player);
                API.getMessageAPI().sendCommandSpawnMessage(player);
            }
        }, 7 * 20);
        return true;
    }
}
