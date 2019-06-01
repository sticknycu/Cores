package nycuro.commands.list.jobs;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobCommand extends PrincipalCommand {

    public JobCommand() {
        super("jobs", "Jobs!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getJobsAPI().getJob(player);
        return true;
    }
}
