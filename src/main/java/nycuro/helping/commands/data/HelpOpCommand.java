package nycuro.helping.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.helping.commands.CommandBaseHelping;
import nycuro.messages.ChatFormat;
import nycuro.messages.handlers.ChatHandlers;

import java.util.Objects;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class HelpOpCommand extends CommandBaseHelping {

    public HelpOpCommand() {
        super("helpop");
    }

    @Override
    public boolean execute(CommandSender commandSender, String see, String[] strings) {
        Player player = (Player) commandSender;
        //
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        String group = Objects.requireNonNull(ChatHandlers.api.getUser(player.getUniqueId())).getPrimaryGroup().toUpperCase();
        String s = ChatFormat.valueOf(group).toString();
        String message = String.join(" ", strings);
        if (!player.hasPermission("core.colorchat")) {
            message = TextFormat.clean(message);
        }
        s = s.replace("%name", player.getName());
        s = s.replace("%msg", message);
        ChatHandlers.count++;
        if (ChatHandlers.count % 2 == 0)
            s = s.replace("%slash", "\\");
        else
            s = s.replace("%slash", "/");
        String lvl = "";
        if (profileSkyblock != null) {
            lvl = String.valueOf(profileSkyblock.getLevel());
        }
        s = s.replace("%lvl", lvl);
        s = TextFormat.colorize(s);
        //
        API.getMechanicAPI().handleHelpop(player, s, API.getMechanicAPI().helpopTag);
        return true;
    }
}

