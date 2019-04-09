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
public class SpawnBossCommand extends PrincipalCommand {

    public SpawnBossCommand() {
        super("spawnboss", "SpawnBoss Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) return false;
        API.getMechanicAPI().spawnBoss();
        return true;
    }
}