package nycuro.homes.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.homes.commands.CommandBaseHomes;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class HomeCommand extends CommandBaseHomes {

    public HomeCommand() {
        super("home");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getHomeAPI().createWindowHome(player);
        return true;
    }
}
