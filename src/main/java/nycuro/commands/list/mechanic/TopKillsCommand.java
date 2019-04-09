package nycuro.commands.list.mechanic;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;

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
            player.sendMessage("§c1) §a" + Database.scoreboardkillsName.get(1) + " -> " + Database.scoreboardkillsValue.get(1));
            player.sendMessage("§c2) §a" + Database.scoreboardkillsName.get(2) + " -> " + Database.scoreboardkillsValue.get(2));
            player.sendMessage("§c3) §a" + Database.scoreboardkillsName.get(3) + " -> " + Database.scoreboardkillsValue.get(3));
            player.sendMessage("§c4) §a" + Database.scoreboardkillsName.get(4) + " -> " + Database.scoreboardkillsValue.get(4));
            player.sendMessage("§c5) §a" + Database.scoreboardkillsName.get(5) + " -> " + Database.scoreboardkillsValue.get(5));
            player.sendMessage("§c6) §a" + Database.scoreboardkillsName.get(6) + " -> " + Database.scoreboardkillsValue.get(6));
            player.sendMessage("§c7) §a" + Database.scoreboardkillsName.get(7) + " -> " + Database.scoreboardkillsValue.get(7));
            player.sendMessage("§c8) §a" + Database.scoreboardkillsName.get(8) + " -> " + Database.scoreboardkillsValue.get(8));
            player.sendMessage("§c9) §a" + Database.scoreboardkillsName.get(9) + " -> " + Database.scoreboardkillsValue.get(9));
            player.sendMessage("§c10) §a" + Database.scoreboardkillsName.get(10) + " -> " + Database.scoreboardkillsValue.get(10));
        } else {
            API.getMessageAPI().topKillsExceptionMessage(player);
            return true;
        }
        return true;
    }
}