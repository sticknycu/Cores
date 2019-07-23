package nycuro.commands.list.kits;

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
        API.getKitsAPI().sendKit(player);
        return true;
    }
}
