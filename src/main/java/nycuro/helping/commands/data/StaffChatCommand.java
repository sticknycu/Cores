package nycuro.helping.commands.data;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.helping.commands.CommandBaseHelping;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

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
        if (!mainAPI.staffChat.contains(player.getServerId())) {
            mainAPI.staffChat.add(player.getServerId());
            player.sendMessage(messageAPI.messagesObject.translateMessage("staffchat.enter.success"));
        } else {
            mainAPI.staffChat.remove(player.getServerId());
            player.sendMessage(messageAPI.messagesObject.translateMessage("staffchat.abandonated"));
        }
        return true;
    }
}
