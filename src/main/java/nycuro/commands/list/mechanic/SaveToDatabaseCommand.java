package nycuro.commands.list.mechanic;

import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

import java.util.Map;
import java.util.UUID;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class SaveToDatabaseCommand extends PrincipalCommand {

    public SaveToDatabaseCommand() {
        super("savetodatabase", "DropParty Message Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isOp()) return false;
        for (Map.Entry<UUID, Profile> map : Database.profile.entrySet()) {
            API.getDatabase().saveDatesPlayer(map.getKey(), map.getValue());
        }
        return false;
    }
}