package nycuro.economy.commands.data.tops;

import cn.nukkit.command.CommandSender;
import nycuro.database.Database;
import nycuro.economy.commands.CommandBaseEconomy;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class TopCoinsCommand extends CommandBaseEconomy {

    public TopCoinsCommand() {
        super("home");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.coins.top.first"));
            for (int i = 1; i <= 10; i++) {
                commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.coins.top.message",
                        Database.scoreboardcoinsName.get(i), mainAPI.emptyNoSpace + i,
                        mainAPI.emptyNoSpace + Database.scoreboardcoinsValue.get(i)));
            }
        } else {
            sendUsage(commandSender);
            return true;
        }
        return true;
    }
}