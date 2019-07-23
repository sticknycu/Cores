package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.commands.ParentCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class SpawnBossCommand extends ParentCommand {

    public SpawnBossCommand() {
        super("spawnboss", "SpawnBoss Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return !(commandSender instanceof Player);
        //API.getMechanicAPI().spawnBoss();
    }
}