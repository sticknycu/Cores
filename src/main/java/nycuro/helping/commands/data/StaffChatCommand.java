package nycuro.helping.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.helping.commands.CommandBaseHelping;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class StaffChatCommand extends CommandBaseHelping {

    public StaffChatCommand() {
        super("staffchat");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (!API.getMainAPI().staffChat.contains(player.getUniqueId())) {
            API.getMainAPI().staffChat.add(player.getUniqueId());
            API.getMessageAPI().sendSuccesEnterStaffChat(player);
        } else {
            API.getMainAPI().staffChat.remove(player.getUniqueId());
            API.getMessageAPI().sendAbandonedStaffchatMessage(player);
        }
        return true;
    }
}
