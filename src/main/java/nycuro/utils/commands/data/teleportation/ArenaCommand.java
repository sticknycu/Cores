package nycuro.utils.commands.data.teleportation;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ArenaCommand extends CommandBaseUtils {

    public ArenaCommand() {
        super("arena");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getMechanicAPI().teleportArena((Player) commandSender);
        return true;
    }
}