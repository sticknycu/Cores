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
public class KitCommand extends PrincipalCommand {

    public KitCommand() {
        super("kit", "Get Kit");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        switch (strings.length) {
            case 1:
                API.getKitsAPI().sendKit(player, strings);
                return true;
            default:
                API.getMessageAPI().sendExceptionKitMessage(player);
                return false;
        }
    }
}
