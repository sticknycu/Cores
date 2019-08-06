package nycuro.utils.commands.data.settings;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.commands.CommandBaseUtils;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class SettingsCommand extends CommandBaseUtils {

    public SettingsCommand() {
        super("settings");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getMechanicAPI().sendSettingsForm(player);
        return true;
    }
}