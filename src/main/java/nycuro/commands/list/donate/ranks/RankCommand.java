package nycuro.commands.list.donate.ranks;

import cn.nukkit.command.CommandSender;
import nycuro.commands.PrincipalCommand;

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
        //LevelHandlers.sendForm((Player) commandSender);
        return true;
    }
}