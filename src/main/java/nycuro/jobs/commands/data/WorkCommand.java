package nycuro.jobs.commands.data;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.NameJob;
import nycuro.jobs.commands.CommandBaseJobs;
import nycuro.jobs.objects.JobsObject;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.jobsAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class WorkCommand extends CommandBaseJobs {

    public WorkCommand() {
        super("work");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        if (profileSkyblock.getJob() == 0) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.empty"));
        } else {
            NameJob job = NameJob.getType(profileSkyblock.getJob());
            JobsObject jobsObject = mainAPI.jobsObject.get(player.getServerId());
            if (jobsObject != null) {
                jobsAPI.handleMission(player);
            } else {
                switch (job) {
                    case MINER:
                        jobsAPI.processMissionOnMiner(player);
                        break;
                    case FARMER:
                        jobsAPI.processMissionOnFarmer(player);
                        break;
                    case BUTCHER:
                        jobsAPI.processMissionOnButcher(player);
                        break;
                    case FISHERMAN:
                        jobsAPI.processMissionOnFisherman(player);
                        break;
                }
            }
        }
        return true;
    }
}

