package nycuro.utils.commands.data.teleportation;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.utils.commands.CommandBaseUtils;

import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class HubCommand extends CommandBaseUtils {

    public HubCommand() {
        super("hub");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        mechanicAPI.handleTransferHub(player);
        return true;
    }
}
