package nycuro.ai.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.ai.commands.CommandBaseAI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class SpawnBossCommand extends CommandBaseAI {

    public SpawnBossCommand() {
        super("spawnboss");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return !(commandSender instanceof Player);
        //mechanicAPI.spawnBoss();
    }
}