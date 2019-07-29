package nycuro.commands.list.mechanic.player;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class SettingsCommand extends PrincipalCommand {

    public SettingsCommand() {
        super("settings", "Arata setarile");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        API.getMechanicAPI().sendSettingsForm(player);
        return true;
    }
}