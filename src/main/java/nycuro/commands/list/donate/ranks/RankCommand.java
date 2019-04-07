package nycuro.commands.list.donate.ranks;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.commands.PrincipalCommand;
import nycuro.level.handlers.LevelHandlers;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class RankCommand extends PrincipalCommand {

    public RankCommand() {
        super("ranks", "Show ranks of Server!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        LevelHandlers.sendFormRank((Player) commandSender);
        return true;
    }
}