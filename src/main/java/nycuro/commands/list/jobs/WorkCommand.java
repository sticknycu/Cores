package nycuro.commands.list.jobs;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.DatabaseMySQL;
import nycuro.jobs.NameJob;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class WorkCommand extends PrincipalCommand {

    public WorkCommand() {
        super("work", "Work bitch!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getDatabase().playerExistInJobs(player.getName(), bool -> {
            if (bool) {
                API.getJobsAPI().handleMission(player);
            } else {
                int jb = DatabaseMySQL.profileSkyblock.get(player.getName()).getJob();
                NameJob job = NameJob.valueOf(NameJob.getType(jb));
                switch (job) {
                    case MINER:
                        API.getJobsAPI().processMissionOnMiner(player);
                        break;
                    case FARMER:
                        API.getJobsAPI().processMissionOnFarmer(player);
                        break;
                    case BUTCHER:
                        API.getJobsAPI().processMissionOnButcher(player);
                        break;
                    case FISHERMAN:
                        API.getJobsAPI().processMissionOnFisherman(player);
                        break;
                }
            }
        });
        return true;
    }
}

