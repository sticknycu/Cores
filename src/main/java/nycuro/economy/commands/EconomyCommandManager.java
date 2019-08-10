package nycuro.economy.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.economy.commands.data.simple.AddCoinsCommand;
import nycuro.economy.commands.data.simple.CoinsCommand;
import nycuro.economy.commands.data.simple.SetCoinsCommand;
import nycuro.economy.commands.data.tops.TopCoinsCommand;

public class EconomyCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new AddCoinsCommand());
        map.register("SkyblockCORE", new CoinsCommand());
        map.register("SkyblockCORE", new SetCoinsCommand());
        map.register("SkyblockCORE", new TopCoinsCommand());
    }
}
