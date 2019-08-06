package nycuro.utils.commands.data.settings;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class StatsCommand extends CommandBaseUtils {

    public StatsCommand() {
        super("stats");
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