package nycuro.commands.list.economy;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class GetCoinsCommand extends PrincipalCommand {

    public GetCoinsCommand() {
        super("coins", "Get Coins of Player!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            double money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            API.getMessageAPI().getSelfMoneyMessage((Player) commandSender, money);
        } else if (strings.length == 1) {
            Player player = API.getMainAPI().getServer().getPlayerExact(strings[0]);
            double money = Database.profileSkyblock.get(player.getName()).getDollars();
            API.getMessageAPI().getPlayerMoneyMessage(commandSender, player, money);
        } else {
            API.getMessageAPI().getMoneyExceptionMessage((Player) commandSender);
            return true;
        }
        return true;
    }
}