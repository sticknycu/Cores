package nycuro.teleport.commands.data;


import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.Location;
import cn.nukkit.player.Player;
import nycuro.teleport.commands.CommandBaseTeleportation;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;

public class TPAllCommand extends CommandBaseTeleportation {

    public TPAllCommand() {
        super("tpall");

        /*command parameters
        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });*/
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
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
                p.teleport(Location.from(player.getX(), player.getY(), player.getZ(), player.getLevel()));
                p.sendMessage(messageAPI.messagesObject.translateMessage("commands.tpall.other", player.getName()));
            }
        }
        player.sendMessage(messageAPI.messagesObject.messages.get("commands.tpall.success"));
        return true;
    }
}

