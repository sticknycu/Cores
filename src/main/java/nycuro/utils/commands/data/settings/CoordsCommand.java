package nycuro.utils.commands.data.settings;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
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
        if ((mainAPI.coords.getOrDefault(player.getServerId(), null) == null) ||
                (mainAPI.coords.getOrDefault(player.getServerId(), null).equals(false)))  {
            mainAPI.coords.put(player.getServerId(), true);
            player.sendMessage(messageAPI.messagesObject.translateMessage("coords.switch.show.true"));
        } else if (mainAPI.coords.getOrDefault(player.getServerId(), null).equals(true)) {
            mainAPI.coords.put(player.getServerId(), false);
            player.sendMessage(messageAPI.messagesObject.translateMessage("coords.switch.show.false"));
        }
        return true;
    }
}