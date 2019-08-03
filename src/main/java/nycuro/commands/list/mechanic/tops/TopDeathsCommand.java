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
public class TopDeathsCommand extends PrincipalCommand {

    public TopDeathsCommand() {
        super("topdeaths", "Get TopDeaths!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Deaths §7«");
            player.sendMessage("§c1) §a" + DatabaseMySQL.scoreboarddeathsName.get(1) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(1));
            player.sendMessage("§c2) §a" + DatabaseMySQL.scoreboarddeathsName.get(2) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(2));
            player.sendMessage("§c3) §a" + DatabaseMySQL.scoreboarddeathsName.get(3) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(3));
            player.sendMessage("§c4) §a" + DatabaseMySQL.scoreboarddeathsName.get(4) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(4));
            player.sendMessage("§c5) §a" + DatabaseMySQL.scoreboarddeathsName.get(5) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(5));
            player.sendMessage("§c6) §a" + DatabaseMySQL.scoreboarddeathsName.get(6) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(6));
            player.sendMessage("§c7) §a" + DatabaseMySQL.scoreboarddeathsName.get(7) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(7));
            player.sendMessage("§c8) §a" + DatabaseMySQL.scoreboarddeathsName.get(8) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(8));
            player.sendMessage("§c9) §a" + DatabaseMySQL.scoreboarddeathsName.get(9) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(9));
            player.sendMessage("§c10) §a" + DatabaseMySQL.scoreboarddeathsName.get(10) + " -> " + DatabaseMySQL.scoreboarddeathsValue.get(10));
        } else {
            API.getMessageAPI().topDeathsExceptionMessage(player);
            return true;
        }
        return true;
    }
}