package nycuro.commands.list.mechanic.player;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import gt.creeperface.holograms.Holograms;
import nycuro.api.API;
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
            Holograms holograms = new Holograms();
            if (message.equals("en")) {
                profile.setLanguage(0);
                API.getDatabase().setLanguage(commandSender.getName(), 0);
                Holograms.setLanguageHandler( (p) -> 0);
                holograms.onLanguageChanged((Player) commandSender);
            } else {
                profile.setLanguage(1);
                API.getDatabase().setLanguage(commandSender.getName(), 1);
                Holograms.setLanguageHandler( (p) -> 1);
                holograms.onLanguageChanged((Player) commandSender);
            }
            API.getMessageAPI().sendLangMessage((Player) commandSender);
        } else {
            API.getMessageAPI().sendLangArrayException((Player) commandSender);
        }
        return true;
    }
}