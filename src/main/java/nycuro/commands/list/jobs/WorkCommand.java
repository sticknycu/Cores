package nycuro.commands.list.jobs;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.NameJob;
import nycuro.jobs.objects.JobsObject;

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
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        if (profileSkyblock.getJob() == 0) {
            API.getMessageAPI().sendNoJobMessage(player);
        } else {
            NameJob job = NameJob.getType(profileSkyblock.getJob());
            JobsObject jobsObject = API.getMainAPI().jobsObject.get(player.getUniqueId());
            if (jobsObject != null) {
                API.getJobsAPI().handleMission(player);
            } else {
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
        }
        return true;
    }
}

