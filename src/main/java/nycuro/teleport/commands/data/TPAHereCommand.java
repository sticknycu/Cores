package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import nycuro.api.API;
import nycuro.teleport.commands.CommandBaseTeleportation;

public class TPAHereCommand extends CommandBaseTeleportation {

    public TPAHereCommand() {
        super("tpahere");

        // command parameters
        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, false)
        });
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (!this.testIngame(sender)) {
            return false;
        }
        if (args.length != 1) {
            this.sendUsage(sender);
            return false;
        }
        if (API.getTeleportationAPI().hasCooldown(sender)) {
            return true;
        }
        Player player = API.getMainAPI().getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
            return false;
        }
        if (sender == player) {
            sender.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.tpa.self"));
            return false;
        }
        API.getTeleportationAPI().requestTP((Player) sender, player, false);
        player.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.tpahere.invite", sender.getName()));
        sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.tpa.success", player.getName()));
        return true;
    }
}
