package nycuro.dropparty.commands;

import cn.nukkit.command.CommandMap;
import nycuro.Loader;
import nycuro.dropparty.commands.data.DropPartyMessageCommand;

public class DropPartyCommandManager {

    public static void registerAll(Loader mainAPI) {
        CommandMap map = mainAPI.getServer().getCommandMap();
        map.register("SkyblockCORE", new DropPartyMessageCommand());
    }
}

