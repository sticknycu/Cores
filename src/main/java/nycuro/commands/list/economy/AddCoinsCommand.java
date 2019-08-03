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
public class AddCoinsCommand extends PrincipalCommand {

    public AddCoinsCommand() {
        super("addcoins", "Add Coins to Player!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.hasPermission("core.addcoins")) {
            commandSender.sendMessage(this.getPermissionMessage());
            return true;
        }

        if (strings.length == 0) {
            API.getMessageAPI().addMoneyExceptionMessage((Player) commandSender);
        } else if (strings.length == 1) {
            double money = DatabaseMySQL.profileSkyblock.get(commandSender.getName()).getDollars();
            double count = Double.valueOf(strings[0]);
            DatabaseMySQL.profileSkyblock.get(commandSender.getName()).setDollars(DatabaseMySQL.profileSkyblock.get(commandSender.getName()).getDollars() + count);
            API.getMessageAPI().addSelfMoneyMessage((Player) commandSender, money, count);
        } else if (strings.length == 2) {
            Player player = API.getMainAPI().getServer().getPlayerExact(strings[0]);
            double money = DatabaseMySQL.profileSkyblock.get(commandSender.getName()).getDollars();
            double count = Double.valueOf(strings[1]);
            DatabaseMySQL.profileSkyblock.get(player.getName()).setDollars(DatabaseMySQL.profileSkyblock.get(commandSender.getName()).getDollars() + count);
            API.getMessageAPI().addSelfMoneyMessage((Player) commandSender, money, count);

        } else {
            API.getMessageAPI().addMoneyExceptionMessage((Player) commandSender);
            return true;
        }
        return true;
    }
}