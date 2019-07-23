package nycuro.commands.list.stats;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class StatsCommand extends PrincipalCommand {

    public StatsCommand() {
        super("stats", "Stats Command");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            API.getMechanicAPI().sendStats(commandSender, (Player) commandSender);
        } else {
            IPlayer player = API.getMainAPI().getServer().getOfflinePlayer(strings[0]);
            API.getMechanicAPI().sendStats(commandSender, player);
        }
        return true;
    }
}