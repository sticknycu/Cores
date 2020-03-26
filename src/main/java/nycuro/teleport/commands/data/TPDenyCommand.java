package nycuro.teleport.commands.data;


import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.player.Player;
import nycuro.teleport.commands.CommandBaseTeleportation;
import nycuro.teleport.objects.TPRequest;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.teleportationAPI;

public class TPDenyCommand extends CommandBaseTeleportation {

    public TPDenyCommand() {
        super("tpdeny");
        this.setAliases(new String[]{"tpno"});

        /* command parameters
        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });*/
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length > 1) {
            this.sendUsage(sender);
            return false;
        }
        Player to = (Player) sender;
        if (teleportationAPI.getLatestTPRequestTo(to) == null) {
            sender.sendMessage(messageAPI.messagesObject.messages.get("commands.tpaccept.noRequest"));
            return false;
        }
        TPRequest request;
        Player from;
        switch (args.length) {
            case 0:
                if ((request = teleportationAPI.getLatestTPRequestTo(to)) == null) {
                    sender.sendMessage(messageAPI.messagesObject.messages.get("commands.tpaccept.unavailable"));
                    return false;
                }
                from = request.getFrom();
                break;
            case 1:
                from = mainAPI.getServer().getPlayer(args[0]);
                if (from == null) {
                    sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
                    return false;
                }
                if ((request = teleportationAPI.getTPRequestBetween(from, to)) != null) {
                    sender.sendMessage( messageAPI.messagesObject.translateMessage("commands.tpaccept.noRequestFrom", from.getName()));
                    return false;
                }
                break;
            default:
                return false;
        }
        from.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpdeny.denied", to.getName()));
        sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpdeny.success", to.getName()));
        teleportationAPI.removeTPRequestBetween(from, to);
        return true;
    }
}
