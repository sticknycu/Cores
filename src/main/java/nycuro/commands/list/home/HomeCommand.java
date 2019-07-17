package nycuro.commands.list.home;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.ParentCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class HomeCommand extends ParentCommand {

    public HomeCommand() {
        super("home", "Homes!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getHomeAPI().createWindowHome(player);
        return true;
    }
}
