package nycuro.reports.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;

public abstract class CommandBaseReports extends Command {

    public CommandBaseReports(String name) {
        super(name);
        //this.description = messageAPI.messagesObject.messages.get("commands." + name + ".description");
        //String usageMessage = messageAPI.messagesObject.messages.get("commands." + name + ".usage");
        //this.usageMessage = usageMessage.equals("commands." + name + ".usage") ? "/" + name : usageMessage;
        this.setPermission("nycuro." + name);
    }

    protected void sendUsage(CommandSender sender) {
        sender.sendMessage(new TranslationContainer("commands.generic.usage", this.usageMessage));
    }

    protected boolean testIngame(CommandSender sender) {
        if (!(sender instanceof Player)) {
            //sender.sendMessage(TextFormat.RED + messageAPI.messagesObject.messages.get("commands.generic.ingame"));
            return false;
        }
        return true;
    }

    protected void sendPermissionMessage(CommandSender sender) {
        sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.permission"));
    }
}

