package nycuro.dropparty.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.dropparty.commands.CommandBaseDropParty;

import static nycuro.api.API.*;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class DropPartyMessageCommand extends CommandBaseDropParty {

    public DropPartyMessageCommand() {
        super("dp");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) return false;
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            player.sendMessage(messageAPI.messagesObject.translateMessage("generic.dropparty.spawn"));
            return true;
        }
        return false;
    }
}