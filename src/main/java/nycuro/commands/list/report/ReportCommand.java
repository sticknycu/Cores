package nycuro.commands.list.report;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import nycuro.api.API;
import nycuro.commands.PrincipalCommand;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ReportCommand extends PrincipalCommand {

    public ReportCommand() {
        super("report", "Raporteaza un jucator");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        API.getReportAPI().createWindowReport((Player) commandSender);
        return true;
    }
}