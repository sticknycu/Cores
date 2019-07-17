package nycuro.commands.list;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.ParentCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ShopCommand extends ParentCommand {

    public ShopCommand() {
        super("shop", "Buy, Sell, Trade with one command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;

        switch (strings.length) {
            case 0:
                API.getShopAPI().sendShopContents(player);
                return true;
            default:
                API.getMessageAPI().sendExceptionShopMessage(player);
                return true;
        }
    }
}