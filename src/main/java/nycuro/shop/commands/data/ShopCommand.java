package nycuro.shop.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.shop.commands.CommandBaseShop;

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