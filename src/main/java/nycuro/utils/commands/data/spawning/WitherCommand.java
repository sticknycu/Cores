package nycuro.utils.commands.data.spawning;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mechanicAPI;

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
        mechanicAPI.spawnWither((Player) commandSender);
        return true;
    }
}