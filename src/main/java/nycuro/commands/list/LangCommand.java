package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LangCommand extends PrincipalCommand {

    public LangCommand() {
        super("lang", "Seteaza limba");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        ProfileProxy profile = Database.profileProxy.get(commandSender.getName());
        if (strings.length == 1) {
            String message = strings[0];
            if (message.equals("en")) {
                API.getDatabase().setLanguage(commandSender.getName(), 0);
                profile.setLanguage(0);
            } else {
                API.getDatabase().setLanguage(commandSender.getName(), 1);
                profile.setLanguage(1);
            }
            API.getMessageAPI().sendLangMessage((Player) commandSender);
        } else {
            API.getMessageAPI().sendLangArrayException((Player) commandSender);
        }
        return true;
    }
}