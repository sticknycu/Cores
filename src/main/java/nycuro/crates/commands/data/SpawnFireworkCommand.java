package nycuro.crates.commands.data;

import cn.nukkit.command.CommandSender;
import nycuro.crates.commands.CommandBaseCrates;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class SpawnFireworkCommand extends CommandBaseCrates {

    public SpawnFireworkCommand() {
        super("sf");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isOp()) return false;
        for (int i = 0; i <= 20; i++) {
            //mechanicAPI.spawnFirework(new Vector3(4985, 4, 5026));
            //mechanicAPI.spawnFirework(new Vector3(5009, 4, 5025));
        }
        return true;
    }
}