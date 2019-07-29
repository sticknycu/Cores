package nycuro.commands.list.homes;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class HomesCommand extends PrincipalCommand {

    public HomesCommand() {
        super("homes", "Homes!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getHomeAPI().createWindowHome(player);
        return true;
    }
}
