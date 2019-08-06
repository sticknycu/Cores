package nycuro.kits.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.kits.commands.CommandBaseKits;

import static nycuro.api.API.kitsAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class KitsCommand extends CommandBaseKits {

    public KitsCommand() {
        super("kits");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        kitsAPI.sendKit(player);
        return true;
    }
}
