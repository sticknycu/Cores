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
public class HelpOpCommand extends PrincipalCommand {

    public HelpOpCommand() {
        super("helpop", "Cere ajutor!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getMechanicAPI().handleHelpop((Player) commandSender, s, API.getMechanicAPI().helpopTag);
        return true;
    }
}

