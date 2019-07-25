package nycuro.commands.list.spawning;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ArenaCommand extends PrincipalCommand {

    public ArenaCommand() {
        super("arena", "Teleporteaza arena!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getMechanicAPI().teleportArena((Player) commandSender);
        return true;
    }
}