package nycuro.utils.commands.data.teleportation;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mechanicAPI;
import static nycuro.api.API.messageAPI;

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
        messageAPI.messagesObject.translateMessage("generic.cooldown");
        player.getServer().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int i) {
                mechanicAPI.sendToSpawn(player);
                player.sendMessage(messageAPI.messagesObject.translateMessage("teleportation.spawn.teleported"));
            }
        }, 7 * 20);
        return true;
    }
}
