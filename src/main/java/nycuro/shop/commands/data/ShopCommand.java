package nycuro.shop.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.shop.commands.CommandBaseShop;

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
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        switch (strings.length) {
            case 0:
                shopAPI.sendShopContents(player);
                return true;
            default:
                sendUsage(commandSender);
                return true;
        }
    }
}