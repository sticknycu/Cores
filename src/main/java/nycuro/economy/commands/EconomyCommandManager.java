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
        map.register(mainAPI, new AddCoinsCommand());
        map.register(mainAPI, new CoinsCommand());
        map.register(mainAPI, new SetCoinsCommand());
        map.register(mainAPI, new TopCoinsCommand());
    }
}
