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
public class StaffChatCommand extends PrincipalCommand {

    public StaffChatCommand() {
        super("staffchat", "Transfer catre hub");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (!player.hasPermission("core.staffchat")) {
            API.getMessageAPI().sendCustomPermissionMessage(player);
            return true;
        }
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
