package nycuro.commands.list.donate.gems;

import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class GemsCommand extends PrincipalCommand {

    public GemsCommand() {
        super("gems", "Add Gems Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isOp()) return false;
        ProfileFactions profile = Database.profileFactions.get(API.getMainAPI().getServer().getPlayer(strings[0]).getUniqueId());
        double count = Double.valueOf(strings[1]);
        //profile.setGems(profile.getGems() + count);
        return true;
    }
}