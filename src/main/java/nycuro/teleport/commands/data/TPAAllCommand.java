package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.player.Player;
import nycuro.teleport.commands.CommandBaseTeleportation;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.teleportationAPI;

public class TPAAllCommand extends CommandBaseTeleportation {

    public TPAAllCommand() {
        super("tpaall");
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
        if (teleportationAPI.hasCooldown(sender)) {
            return true;
        }
        Player player;
        if (args.length == 0) {
            if (!this.testIngame(sender)) {
                return false;
            }
            player = (Player) sender;
        } else if (args.length == 1) {
            player = mainAPI.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(messageAPI.messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
                return false;
            }
        } else {
            this.sendUsage(sender);
            return false;
        }
        for (Player p : mainAPI.getServer().getOnlinePlayers().values()) {
            if (p != player) {
                teleportationAPI.requestTP(player, p, false);
                p.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpahere.invite", player.getName()));
            }
        }
        player.sendMessage(messageAPI.messagesObject.messages.get("commands.tpaall.success"));
        return true;
    }
}
