package nycuro.utils.commands.data.settings;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import gt.creeperface.holograms.Holograms;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.databaseAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class LangCommand extends CommandBaseUtils {

    public LangCommand() {
        super("lang");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        ProfileProxy profile = Database.profileProxy.get(commandSender.getName());
        if (strings.length == 1) {
            String message = strings[0];
            Holograms holograms = new Holograms();
            if (message.equals("en")) {
                profile.setLanguage(0);
                databaseAPI.setLanguage(commandSender.getName(), 0);
                Holograms.setLanguageHandler( (p) -> 0);
                holograms.onLanguageChanged((Player) commandSender);
            } else if (message.equals("ro")) {
                profile.setLanguage(1);
                databaseAPI.setLanguage(commandSender.getName(), 1);
                Holograms.setLanguageHandler( (p) -> 1);
                holograms.onLanguageChanged((Player) commandSender);
            } else {
                sendUsage(commandSender);
            }
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("language.selected"));
        } else {
            sendUsage(commandSender);
        }
        return true;
    }
}