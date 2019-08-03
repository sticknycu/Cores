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
public class TopCoinsCommand extends PrincipalCommand {

    public TopCoinsCommand() {
        super("topcoins", "Get TopCoins!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Coins §7«");
            player.sendMessage("§c1) §a" + DatabaseMySQL.scoreboardcoinsName.get(1) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(1));
            player.sendMessage("§c2) §a" + DatabaseMySQL.scoreboardcoinsName.get(2) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(2));
            player.sendMessage("§c3) §a" + DatabaseMySQL.scoreboardcoinsName.get(3) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(3));
            player.sendMessage("§c4) §a" + DatabaseMySQL.scoreboardcoinsName.get(4) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(4));
            player.sendMessage("§c5) §a" + DatabaseMySQL.scoreboardcoinsName.get(5) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(5));
            player.sendMessage("§c6) §a" + DatabaseMySQL.scoreboardcoinsName.get(6) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(6));
            player.sendMessage("§c7) §a" + DatabaseMySQL.scoreboardcoinsName.get(7) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(7));
            player.sendMessage("§c8) §a" + DatabaseMySQL.scoreboardcoinsName.get(8) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(8));
            player.sendMessage("§c9) §a" + DatabaseMySQL.scoreboardcoinsName.get(9) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(9));
            player.sendMessage("§c10) §a" + DatabaseMySQL.scoreboardcoinsName.get(10) + " -> " + DatabaseMySQL.scoreboardcoinsValue.get(10));
        } else {
            API.getMessageAPI().topMoneyExceptionMessage(player);
            return true;
        }
        return true;
    }
}