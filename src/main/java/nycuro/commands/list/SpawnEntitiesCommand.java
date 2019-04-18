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
public class SpawnEntitiesCommand extends PrincipalCommand {

    public SpawnEntitiesCommand() {
        super("spawnentities", "SpawnEntities Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) return false;
        //API.getMechanicAPI().spawnEntities();
        return true;
    }
}