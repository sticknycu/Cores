package nycuro.commands.list.jobs;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.ParentCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class JobCommand extends ParentCommand {

    public JobCommand() {
        super("jobs", "Jobs!");
    }

    @Override
    public boolean execute(CommandSender sender, String str, String[] args) {
        Player player = (Player) sender;
        ProfileFactions profile = Database.profileFactions.get(player.getName());

        if(args.length == 0) {
            API.getJobsAPI().getJob(player);
        } else {
            switch(args[0]) {
                case "miner": {
                    profile.setJob(1);
                    API.getMessageAPI().sendReceiveJobMessage(player, "Miner");

                    break;
                }
                case "butcher": {
                    profile.setJob(2);
                    API.getMessageAPI().sendReceiveJobMessage(player, "Butcher");
                }
                case "fisherman": {

                }
            }
        }

        return true;
    }
}
