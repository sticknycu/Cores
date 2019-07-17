package nycuro.commands.list.mechanic;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.ParentCommand;
import nycuro.database.Database;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class TopTimeCommand extends ParentCommand {

    public TopTimeCommand() {
        super("toptime", "Get TopTime!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Online Time §7«");
            player.sendMessage("§c1) §a" + Database.scoreboardtimeName.get(1) + " -> " + Database.scoreboardtimeValue.get(1));
            player.sendMessage("§c2) §a" + Database.scoreboardtimeName.get(2) + " -> " + Database.scoreboardtimeValue.get(2));
            player.sendMessage("§c3) §a" + Database.scoreboardtimeName.get(3) + " -> " + Database.scoreboardtimeValue.get(3));
            player.sendMessage("§c4) §a" + Database.scoreboardtimeName.get(4) + " -> " + Database.scoreboardtimeValue.get(4));
            player.sendMessage("§c5) §a" + Database.scoreboardtimeName.get(5) + " -> " + Database.scoreboardtimeValue.get(5));
            player.sendMessage("§c6) §a" + Database.scoreboardtimeName.get(6) + " -> " + Database.scoreboardtimeValue.get(6));
            player.sendMessage("§c7) §a" + Database.scoreboardtimeName.get(7) + " -> " + Database.scoreboardtimeValue.get(7));
            player.sendMessage("§c8) §a" + Database.scoreboardtimeName.get(8) + " -> " + Database.scoreboardtimeValue.get(8));
            player.sendMessage("§c9) §a" + Database.scoreboardtimeName.get(9) + " -> " + Database.scoreboardtimeValue.get(9));
            player.sendMessage("§c10) §a" + Database.scoreboardtimeName.get(10) + " -> " + Database.scoreboardtimeValue.get(10));
        } else {
            API.getMessageAPI().topTimeExceptionMessage(player);
            return true;
        }
        return true;
    }
}