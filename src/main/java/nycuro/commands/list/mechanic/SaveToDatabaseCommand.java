package nycuro.commands.list.mechanic;

import cn.nukkit.command.CommandSender;
import nycuro.API;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileHub;

import java.util.Map;
import java.util.UUID;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class SaveToDatabaseCommand extends PrincipalCommand {

    public SaveToDatabaseCommand() {
        super("savetodatabase", "Save to database Command!");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isOp()) return false;
        for (Map.Entry<UUID, ProfileHub> map : Database.profileHub.entrySet()) {
            API.getDatabase().saveDatesPlayerHub(map.getKey(), map.getValue());
        }
        for (Map.Entry<UUID, ProfileFactions> map : Database.profileFactions.entrySet()) {
            API.getDatabase().saveDatesPlayerFactions(map.getKey(), map.getValue());
        }
        return false;
    }
}