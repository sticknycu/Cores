package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class SpawnCommand extends PrincipalCommand {

    public SpawnCommand() {
        super("spawn", "Teleport Spawn");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getMessageAPI().sendCommandCooldownSpawnMessage(player);
        player.setImmobile(true);
        player.getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                API.getMechanicAPI().sendToSpawn(player);
                API.getMessageAPI().sendCommandSpawnMessage(player);
            }
        }, 3 * 20);
        return true;
    }
}
