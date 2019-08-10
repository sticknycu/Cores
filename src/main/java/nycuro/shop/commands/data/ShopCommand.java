package nycuro.shop.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.shop.commands.CommandBaseShop;

import static nycuro.api.API.messageAPI;
import static nycuro.api.API.shopAPI;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ShopCommand extends CommandBaseShop {

    public ShopCommand() {
        super("shop");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        Player player = (Player) commandSender;
        if (args.length > 0) {
            messageAPI.sendExceptionShopMessage(player);
            return true;
        }
        shopAPI.sendShopContents(player);

        return true;
    }
}