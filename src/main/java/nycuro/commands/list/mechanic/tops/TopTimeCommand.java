package nycuro.commands.list.mechanic.tops;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.DatabaseMySQL;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class TopTimeCommand extends PrincipalCommand {

    public TopTimeCommand() {
        super("toptime", "Get TopTime!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Online Time §7«");
            player.sendMessage("§c1) §a" + DatabaseMySQL.scoreboardtimeName.get(1) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(1));
            player.sendMessage("§c2) §a" + DatabaseMySQL.scoreboardtimeName.get(2) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(2));
            player.sendMessage("§c3) §a" + DatabaseMySQL.scoreboardtimeName.get(3) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(3));
            player.sendMessage("§c4) §a" + DatabaseMySQL.scoreboardtimeName.get(4) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(4));
            player.sendMessage("§c5) §a" + DatabaseMySQL.scoreboardtimeName.get(5) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(5));
            player.sendMessage("§c6) §a" + DatabaseMySQL.scoreboardtimeName.get(6) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(6));
            player.sendMessage("§c7) §a" + DatabaseMySQL.scoreboardtimeName.get(7) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(7));
            player.sendMessage("§c8) §a" + DatabaseMySQL.scoreboardtimeName.get(8) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(8));
            player.sendMessage("§c9) §a" + DatabaseMySQL.scoreboardtimeName.get(9) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(9));
            player.sendMessage("§c10) §a" + DatabaseMySQL.scoreboardtimeName.get(10) + " -> " + DatabaseMySQL.scoreboardtimeValue.get(10));
        } else {
            API.getMessageAPI().topTimeExceptionMessage(player);
            return true;
        }
        return true;
    }
}