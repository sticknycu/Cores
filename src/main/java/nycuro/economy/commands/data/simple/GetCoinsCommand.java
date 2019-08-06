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
public class GetCoinsCommand extends CommandBaseEconomy {

    public GetCoinsCommand() {
        super("getcoins");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            double money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            messageAPI.getSelfMoneyMessage((Player) commandSender, money);
        } else if (strings.length == 1) {
            Player player = mainAPI.getServer().getPlayerExact(strings[0]);
            double money = Database.profileSkyblock.get(player.getName()).getDollars();
            messageAPI.getPlayerMoneyMessage(commandSender, player, money);
        } else {
            messageAPI.getMoneyExceptionMessage((Player) commandSender);
            return true;
        }
        return true;
    }
}