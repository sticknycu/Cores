package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ServersCommand extends PrincipalCommand {

    public ServersCommand() {
        super("servers", "Get Servers");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getMechanicAPI().sendServersModal(player);
        if (strings.length > 1) {
            API.getMessageAPI().sendExceptionServersMessage(player);
            return false;
        }
        return true;
    }
}
