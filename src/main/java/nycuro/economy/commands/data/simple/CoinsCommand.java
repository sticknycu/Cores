package nycuro.economy.commands.data.simple;


import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.database.Database;
import nycuro.economy.commands.CommandBaseEconomy;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class CoinsCommand extends CommandBaseEconomy {

    public CoinsCommand() {
        super("coins");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            double money = Database.profileSkyblock.get(commandSender.getName()).getDollars();
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.money.now", mainAPI.emptyNoSpace + money));
        } else if (strings.length == 1) {
            Player player = mainAPI.getServer().getPlayerExact(strings[0]);
            double money = Database.profileSkyblock.get(player.getName()).getDollars();
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.money.now", player.getName(), mainAPI.emptyNoSpace + money));
        } else {
            sendUsage(commandSender);
            return true;
        }
        return true;
    }
}