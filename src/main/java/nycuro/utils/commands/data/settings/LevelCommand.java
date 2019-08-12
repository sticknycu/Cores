package nycuro.utils.commands.data.settings;

import cn.nukkit.IPlayer;
import cn.nukkit.command.CommandSender;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LevelCommand extends CommandBaseUtils {

    public LevelCommand() {
        super("setlevel");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 2) {
            IPlayer player = mainAPI.getServer().getOfflinePlayer(strings[0]);
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            profileSkyblock.setLevel(Integer.valueOf(strings[1]));
        } else {
            sendUsage(commandSender);
        }
        return true;
    }
}
