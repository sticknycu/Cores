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
public class TopDeathsCommand extends PrincipalCommand {

    public TopDeathsCommand() {
        super("topdeaths", "Get TopDeaths!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.sendMessage("§7» §6Top Deaths §7«");
            player.sendMessage("§c1) §a" + Database.scoreboarddeathsName.get(1) + " -> " + Database.scoreboarddeathsValue.get(1));
            player.sendMessage("§c2) §a" + Database.scoreboarddeathsName.get(2) + " -> " + Database.scoreboarddeathsValue.get(2));
            player.sendMessage("§c3) §a" + Database.scoreboarddeathsName.get(3) + " -> " + Database.scoreboarddeathsValue.get(3));
            player.sendMessage("§c4) §a" + Database.scoreboarddeathsName.get(4) + " -> " + Database.scoreboarddeathsValue.get(4));
            player.sendMessage("§c5) §a" + Database.scoreboarddeathsName.get(5) + " -> " + Database.scoreboarddeathsValue.get(5));
            player.sendMessage("§c6) §a" + Database.scoreboarddeathsName.get(6) + " -> " + Database.scoreboarddeathsValue.get(6));
            player.sendMessage("§c7) §a" + Database.scoreboarddeathsName.get(7) + " -> " + Database.scoreboarddeathsValue.get(7));
            player.sendMessage("§c8) §a" + Database.scoreboarddeathsName.get(8) + " -> " + Database.scoreboarddeathsValue.get(8));
            player.sendMessage("§c9) §a" + Database.scoreboarddeathsName.get(9) + " -> " + Database.scoreboarddeathsValue.get(9));
            player.sendMessage("§c10) §a" + Database.scoreboarddeathsName.get(10) + " -> " + Database.scoreboarddeathsValue.get(10));
        } else {
            API.getMessageAPI().topDeathsExceptionMessage(player);
            return true;
        }
        return true;
    }
}