package nycuro.homes.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.homes.commands.CommandBaseHomes;

import static nycuro.api.API.homeAPI;

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
        homeAPI.createWindowHome(player);
        return true;
    }
}
