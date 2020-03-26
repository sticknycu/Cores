package nycuro.utils.commands.data.utils;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.utilsAPI;

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
        utilsAPI.sendUtilsContents(player);
        return true;
    }
}
