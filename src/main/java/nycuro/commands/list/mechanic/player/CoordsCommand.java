package nycuro.commands.list.mechanic.player;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CoordsCommand extends PrincipalCommand {

    public CoordsCommand() {
        super("coords", "Arata coordonatele la Scoreboard");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if ((API.getMainAPI().coords.getOrDefault(player.getUniqueId(), null) == null) ||
                (API.getMainAPI().coords.getOrDefault(player.getUniqueId(), null).equals(false)))  {
            API.getMainAPI().coords.put(player.getUniqueId(), true);
            API.getMessageAPI().sendCoordsSwitchMessage(player, true);
        } else if (API.getMainAPI().coords.getOrDefault(player.getUniqueId(), null).equals(true)) {
            API.getMainAPI().coords.put(player.getUniqueId(), false);
            API.getMessageAPI().sendCoordsSwitchMessage(player, false);
        }
        return true;
    }
}