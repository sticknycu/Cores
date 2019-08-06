package nycuro.economy.commands.data.simple;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.database.Database;
import nycuro.economy.commands.CommandBaseEconomy;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class AddCoinsCommand extends CommandBaseEconomy {

    public AddCoinsCommand() {
        super("addcoins");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            messageAPI.addMoneyExceptionMessage((Player) commandSender);
        } else if (strings.length == 1) {
            double money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            double count = Double.valueOf(strings[0]);
            Database.profileSkyblock.get(commandSender.getName()).setDollars(Database.profileSkyblock.get(commandSender.getName()).getDollars() + count);
            messageAPI.addSelfMoneyMessage((Player) commandSender, money, count);
        } else if (strings.length == 2) {
            Player player = mainAPI.getServer().getPlayerExact(strings[0]);
            double money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            double count = Double.valueOf(strings[1]);
            Database.profileSkyblock.get(player.getName()).setDollars(Database.profileSkyblock.get(commandSender.getName()).getDollars() + count);
            messageAPI.addSelfMoneyMessage((Player) commandSender, money, count);

        } else {
            messageAPI.addMoneyExceptionMessage((Player) commandSender);
            return true;
        }
        return true;
    }
}