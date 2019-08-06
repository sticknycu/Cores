package nycuro.dropparty.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.dropparty.commands.CommandBaseDropParty;

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
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            API.getMechanicAPI().sendDropPartyMessageBroadcast(player);
            return true;
        }
        return false;
    }
}