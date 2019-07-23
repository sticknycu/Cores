package nycuro.commands.list.mechanic;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class DropPartyMessageCommand extends PrincipalCommand {

    public DropPartyMessageCommand() {
        super("droppartymessage", "DropParty Message Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) return false;
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            API.getMechanicAPI().sendDropPartyMessageBroadcast(player);
            return true;
        }
        return false;
    }
}