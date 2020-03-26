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
            sendUsage(commandSender);
        } else if (strings.length == 1) {
            double count = Double.valueOf(strings[0]);
            Database.profileSkyblock.get(commandSender.getName()).setDollars(count);
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.money.now", mainAPI.emptyNoSpace + count));
        } else if (strings.length == 2) {
            Player player = mainAPI.getServer().getPlayerExact(strings[0]);
            double count = Double.valueOf(strings[1]);
            Database.profileSkyblock.get(player.getName()).setDollars(count);
            commandSender.sendMessage(messageAPI.messagesObject.translateMessage("commands.money.now", mainAPI.emptyNoSpace + count));
        } else {
            sendUsage(commandSender);
            return true;
        }

        return true;
    }
}