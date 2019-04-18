package nycuro.commands.list.mechanic;

import cn.nukkit.command.CommandSender;
import cn.nukkit.math.Vector3;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class SpawnFireworkCommand extends PrincipalCommand {

    public SpawnFireworkCommand() {
        super("sf", "SpawnFireworks");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isOp()) return false;
        for (int i = 0; i <= 20; i++) {
            //API.getMechanicAPI().spawnFirework(new Vector3(4985, 4, 5026));
            //API.getMechanicAPI().spawnFirework(new Vector3(5009, 4, 5025));
        }
        return true;
    }
}