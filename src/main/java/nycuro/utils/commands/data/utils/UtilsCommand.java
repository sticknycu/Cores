package nycuro.utils.commands.data.utils;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class UtilsCommand extends CommandBaseUtils {

    public UtilsCommand() {
        super("utils");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getUtilsAPI().sendUtilsContents(player);
        return true;
    }
}
