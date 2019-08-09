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
        if (!commandSender.hasPermission("core.addcoins")) {
            commandSender.sendMessage(this.getPermissionMessage());
            return true;
        }
        double money = 0;
        double count = 0;
        if (strings.length == 0) {
            sendUsage(commandSender);
        } else if (strings.length == 1) {
            money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            count = Double.valueOf(strings[0]);
            Database.profileSkyblock.get(commandSender.getName()).setDollars(money + count);
        } else if (strings.length == 2) {
            Player player = mainAPI.getServer().getPlayerExact(strings[0]);
            money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            count = Double.valueOf(strings[1]);
            Database.profileSkyblock.get(player.getName()).setDollars(money + count);
        } else {
            sendUsage(commandSender);
            return true;
        }
        commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.money.now", mainAPI.emptyNoSpace + (money + count)));
        return true;
    }
}