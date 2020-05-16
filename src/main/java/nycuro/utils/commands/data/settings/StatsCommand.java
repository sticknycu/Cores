package nycuro.utils.commands.data.settings;

import cn.nukkit.command.CommandSender;
import cn.nukkit.player.IPlayer;
import cn.nukkit.player.Player;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.mechanicAPI;

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
            //mechanicAPI.sendStats(commandSender, (Player) commandSender);
        } else {
            IPlayer player = mainAPI.getServer().getOfflinePlayer(strings[0]);
            //mechanicAPI.sendStats(commandSender, player);
        }
        return true;
    }
}