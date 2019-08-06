package nycuro.utils.commands.data.settings;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CoordsCommand extends CommandBaseUtils {

    public CoordsCommand() {
        super("coords");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if ((mainAPI.coords.getOrDefault(player.getUniqueId(), null) == null) ||
                (mainAPI.coords.getOrDefault(player.getUniqueId(), null).equals(false)))  {
            mainAPI.coords.put(player.getUniqueId(), true);
            messageAPI.sendCoordsSwitchMessage(player, true);
        } else if (mainAPI.coords.getOrDefault(player.getUniqueId(), null).equals(true)) {
            mainAPI.coords.put(player.getUniqueId(), false);
            messageAPI.sendCoordsSwitchMessage(player, false);
        }
        return true;
    }
}