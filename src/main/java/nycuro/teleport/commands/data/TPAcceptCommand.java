package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import nycuro.api.API;
import nycuro.teleport.commands.CommandBaseTeleportation;
import nycuro.teleport.objects.TPRequest;

public class TPAcceptCommand extends CommandBaseTeleportation {

    public TPAcceptCommand() {
        super("tpaccept");
        this.setAliases(new String[]{"tpyes"});

        // command parameters
        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
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
        if (API.getTeleportationAPI().getLatestTPRequestTo(to) == null) {
            sender.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.tpaccept.noRequest"));
            return false;
        }
        TPRequest request;
        Player from;
        if (args.length == 0) {
            if ((request = API.getTeleportationAPI().getLatestTPRequestTo(to)) == null) {
                sender.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.tpaccept.unavailable"));
                return false;
            }
            from = request.getFrom();
        } else {
            from = API.getMainAPI().getServer().getPlayer(args[0]);
            if (from == null) {
                sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
                return false;
            }
            if ((request = API.getTeleportationAPI().getTPRequestBetween(from, to)) != null) {
                sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.tpaccept.noRequestFrom", from.getName()));
                return false;
            }
        }
        if (request == null) {
            sender.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.tpaccept.noRequest"));
            return false;
        }
        from.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.tpaccept.accepted", to.getName()));
        sender.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.generic.teleporting"));
        if (request.isTo()) {
            API.getTeleportationAPI().onTP(from, request.getLocation(), API.getMessageAPI().messagesObject.getMessages().get("commands.generic.teleporting"));
        } else {
            API.getTeleportationAPI().onTP(to, request.getLocation(), API.getMessageAPI().messagesObject.getMessages().get("commands.generic.teleporting"));
        }
        API.getTeleportationAPI().removeTPRequestBetween(from, to);
        return true;
    }
}
