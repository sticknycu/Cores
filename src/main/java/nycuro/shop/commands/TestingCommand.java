package nycuro.shop.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.utils.objects.ShopObject;

public class TestingCommand extends CommandBaseShop {


    public TestingCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        Player player = (Player) sender;
        if (API.mainAPI.moneyAPI.getShopItems().contains(new ShopObject("Stone", 1, 1, 5))) {
            player.sendMessage("merge");
            API.log("merge bai asta");
        }
        return true;
    }
}
