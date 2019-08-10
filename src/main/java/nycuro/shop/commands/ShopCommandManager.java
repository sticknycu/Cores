package nycuro.shop.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.shop.commands.data.ShopCommand;

public class ShopCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new ShopCommand());
        map.register("SkyblockCORE", new TestingCommand("test1"));
    }
}
