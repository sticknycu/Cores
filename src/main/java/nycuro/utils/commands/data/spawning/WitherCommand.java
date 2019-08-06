package nycuro.utils.commands.data.spawning;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class WitherCommand extends CommandBaseUtils {

    public WitherCommand() {
        super("wither");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getMechanicAPI().spawnWither((Player) commandSender);
        return true;
    }
}