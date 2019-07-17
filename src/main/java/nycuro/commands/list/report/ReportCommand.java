package nycuro.commands.list.report;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.ParentCommand;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ReportCommand extends ParentCommand {

    public ReportCommand() {
        super("report", "Raporteaza un jucator");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getReportAPI().createWindowReport((Player) commandSender);
        return true;
    }
}