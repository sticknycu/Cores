package nycuro.economy.commands.data.simple;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.economy.commands.CommandBaseEconomy;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class SetCoinsCommand extends CommandBaseEconomy {

    public SetCoinsCommand() {
        super("setcoins");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.hasPermission("core.setcoins")) {
            commandSender.sendMessage(this.getPermissionMessage());
            return true;
        }

        if (strings.length == 0) {
            API.getMessageAPI().addMoneyExceptionMessage((Player) commandSender);
        } else if (strings.length == 1) {
            double count = Double.valueOf(strings[0]);
            Database.profileSkyblock.get(commandSender.getName()).setDollars(count);
            API.getMessageAPI().setSelfMoneyMessage((Player) commandSender, count);
        } else if (strings.length == 2) {
            Player player = API.getMainAPI().getServer().getPlayerExact(strings[0]);
            double count = Double.valueOf(strings[1]);
            Database.profileSkyblock.get(player.getName()).setDollars(count);
            API.getMessageAPI().setPlayerMoneyMessage(commandSender, player, count);
        } else {
            API.getMessageAPI().addMoneyExceptionMessage((Player) commandSender);
            return true;
        }

        return true;
    }
}