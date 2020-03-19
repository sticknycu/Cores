package nycuro.reports.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import nycuro.reports.commands.CommandBaseReports;

import static nycuro.api.API.reportAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ReportsCommand extends CommandBaseReports {

    public ReportsCommand() {
        super("reports");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        reportAPI.createWindowReport((Player) commandSender);
        return true;
    }
}