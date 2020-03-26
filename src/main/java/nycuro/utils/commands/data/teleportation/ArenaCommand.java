package nycuro.utils.commands.data.teleportation;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mechanicAPI;

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
        mechanicAPI.teleportArena((Player) commandSender);
        return true;
    }
}