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
public class TopKillsCommand extends PrincipalCommand {

    public TopKillsCommand() {
        super("topkills", "Get TopKills!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Coins §7«");
            player.sendMessage("§c1) §a" + DatabaseMySQL.scoreboardkillsName.get(1) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(1));
            player.sendMessage("§c2) §a" + DatabaseMySQL.scoreboardkillsName.get(2) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(2));
            player.sendMessage("§c3) §a" + DatabaseMySQL.scoreboardkillsName.get(3) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(3));
            player.sendMessage("§c4) §a" + DatabaseMySQL.scoreboardkillsName.get(4) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(4));
            player.sendMessage("§c5) §a" + DatabaseMySQL.scoreboardkillsName.get(5) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(5));
            player.sendMessage("§c6) §a" + DatabaseMySQL.scoreboardkillsName.get(6) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(6));
            player.sendMessage("§c7) §a" + DatabaseMySQL.scoreboardkillsName.get(7) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(7));
            player.sendMessage("§c8) §a" + DatabaseMySQL.scoreboardkillsName.get(8) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(8));
            player.sendMessage("§c9) §a" + DatabaseMySQL.scoreboardkillsName.get(9) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(9));
            player.sendMessage("§c10) §a" + DatabaseMySQL.scoreboardkillsName.get(10) + " -> " + DatabaseMySQL.scoreboardkillsValue.get(10));
        } else {
            API.getMessageAPI().topKillsExceptionMessage(player);
            return true;
        }
        return true;
    }
}