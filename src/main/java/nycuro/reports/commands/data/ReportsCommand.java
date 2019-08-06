package nycuro.reports.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.reports.commands.CommandBaseReports;

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
        API.getReportAPI().createWindowReport((Player) commandSender);
        return true;
    }
}