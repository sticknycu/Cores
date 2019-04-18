package nycuro.commands.list.time;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.Loader;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class GetTimeCommand extends PrincipalCommand {

    public GetTimeCommand() {
        super("onlinetime", "Get Time of Player!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            ProfileFactions senderProfile = Database.profileFactions.get(player.getUniqueId());
            long session = System.currentTimeMillis() - Loader.startTime.getLong(player.getUniqueId());
            long time = senderProfile.getTime();
            API.getMessageAPI().getSelfTimeMessage(player, session, time);
        } else if (strings.length == 1) {
            Player playerCommand = API.getMainAPI().getServer().getPlayerExact(strings[0]);
            ProfileFactions profile = Database.profileFactions.get(playerCommand.getUniqueId());
            long sessionCommand = System.currentTimeMillis() - Loader.startTime.getLong(playerCommand.getUniqueId());
            long time = profile.getTime();
            API.getMessageAPI().getPlayerTimeMessage(player, playerCommand, sessionCommand, time);
        } else {
            API.getMessageAPI().getTimeExceptionMessage(player);
            return true;
        }
        return true;
    }
}