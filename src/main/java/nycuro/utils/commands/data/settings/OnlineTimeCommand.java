package nycuro.utils.commands.data.settings;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class OnlineTimeCommand extends CommandBaseUtils {

    public OnlineTimeCommand() {
        super("onlinetime");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            ProfileSkyblock senderProfile = Database.profileSkyblock.get(player.getName());
            long session = System.currentTimeMillis() - Loader.startTime.getLong(player.getUniqueId());
            long time = senderProfile.getTime();
            messageAPI.getSelfTimeMessage(player, session, time);
        } else if (strings.length == 1) {
            Player playerCommand = mainAPI.getServer().getPlayerExact(strings[0]);
            ProfileSkyblock profile = Database.profileSkyblock.get(playerCommand.getName());
            long sessionCommand = System.currentTimeMillis() - Loader.startTime.getLong(playerCommand.getUniqueId());
            long time = profile.getTime();
            messageAPI.getPlayerTimeMessage(player, playerCommand, sessionCommand, time);
        } else {
            messageAPI.getTimeExceptionMessage(player);
            return true;
        }
        return true;
    }
}