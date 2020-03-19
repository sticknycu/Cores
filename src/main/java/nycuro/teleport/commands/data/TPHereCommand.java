package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.player.Player;
import nycuro.teleport.commands.CommandBaseTeleportation;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

public class TPHereCommand extends CommandBaseTeleportation {

    public TPHereCommand() {
        super("tphere");
        this.setAliases(new String[]{"s"});

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
        Player player = mainAPI.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
            return false;
        }
        player.teleport((Player) sender);
        player.sendMessage(messageAPI.messagesObject.translateMessage("commands.tphere.other", sender.getName()));
        sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.tphere.success", player.getName()));
        return true;
    }
}
