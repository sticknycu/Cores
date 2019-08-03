package nycuro.commands.list.economy;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.DatabaseMySQL;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class SetCoinsCommand extends PrincipalCommand {

    public SetCoinsCommand() {
        super("setcoins", "Set Coins to Player!");
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
            DatabaseMySQL.profileSkyblock.get(commandSender.getName()).setDollars(count);
            API.getMessageAPI().setSelfMoneyMessage((Player) commandSender, count);
        } else if (strings.length == 2) {
            Player player = API.getMainAPI().getServer().getPlayerExact(strings[0]);
            double count = Double.valueOf(strings[1]);
            DatabaseMySQL.profileSkyblock.get(player.getName()).setDollars(count);
            API.getMessageAPI().setPlayerMoneyMessage(commandSender, player, count);
        } else {
            API.getMessageAPI().addMoneyExceptionMessage((Player) commandSender);
            return true;
        }

        return true;
    }
}