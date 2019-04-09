package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.mechanic.handlers.MechanicHandlers;

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
        if ((API.getMainAPI().coords.getOrDefault(commandSender.getName(), null) == null) ||
                (API.getMainAPI().coords.getOrDefault(commandSender.getName(), null).equals(false)))  {
            API.getMainAPI().coords.put(commandSender.getName(), true);
            API.getMessageAPI().sendCoordsSwitchMessage((Player) commandSender, true);
        } else if (API.getMainAPI().coords.getOrDefault(commandSender.getName(), null).equals(true)) {
            API.getMainAPI().coords.put(commandSender.getName(), false);
            API.getMessageAPI().sendCoordsSwitchMessage((Player) commandSender, false);
        }
        return true;
    }
}