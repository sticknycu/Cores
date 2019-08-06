package nycuro.teleport.commands.data;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import nycuro.api.API;
import nycuro.teleport.commands.CommandBaseTeleportation;

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
        if (API.getTeleportationAPI().hasCooldown(sender)) {
            return true;
        }
        Player player;
        if (args.length == 0) {
            if (!this.testIngame(sender)) {
                return false;
            }
            player = (Player) sender;
        } else if (args.length == 1) {
            player = API.getMainAPI().getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.generic.player.notfound", args[0]));
                return false;
            }
        } else {
            this.sendUsage(sender);
            return false;
        }
        for (Player p : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            if (p != player) {
                API.getTeleportationAPI().requestTP(player, p, false);
                p.sendMessage(API.getMessageAPI().messagesObject.translateMessage("commands.tpahere.invite", player.getName()));
            }
        }
        player.sendMessage(API.getMessageAPI().messagesObject.getMessages().get("commands.tpaall.success"));
        return true;
    }
}
