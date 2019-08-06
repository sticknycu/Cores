package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import nycuro.teleport.commands.CommandBaseTeleportation;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.teleportationAPI;

public class TPACommand extends CommandBaseTeleportation {

    public TPACommand() {
        super("tpa");
        this.setAliases(new String[]{"call", "tpask"});

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
        if (teleportationAPI.hasCooldown(sender)) {
            return true;
        }
        Player player = mainAPI.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
            return false;
        }
        if (sender == player) {
            sender.sendMessage(messageAPI.messagesObject.getMessages().get("commands.tpa.self"));
            return false;
        }
        teleportationAPI.requestTP((Player) sender, player, true);
        player.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpa.invite", sender.getName()));
        sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpa.success", player.getName()));
        return true;
    }
}
