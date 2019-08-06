package nycuro.jobs.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.jobs.commands.CommandBaseJobs;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobCommand extends CommandBaseJobs {

    public JobCommand() {
        super("job");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getJobsAPI().getJob(player);
        return true;
    }
}
