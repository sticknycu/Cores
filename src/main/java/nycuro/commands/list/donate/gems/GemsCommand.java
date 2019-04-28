package nycuro.commands.list.donate.gems;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import nycuro.commands.PrincipalCommand;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

import java.util.ArrayList;
import java.util.UUID;

/**
 * author: NycuRO
 * ProxyCore Project
 * API 1.0.0
 */
public class GemsCommand extends PrincipalCommand {

    public GemsCommand() {
        super("gems");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("NycuR0");
        arrayList.add("MariusMRN");
        arrayList.add("iKeedMC");
        if (commandSender instanceof ProxiedPlayer) {
            arrayList.forEach(
                    (str) -> {
                        if (commandSender.getName().equals(str)) {
                            if (strings.length == 0) return;
                            if (strings.length == 1) commandSender.sendMessage(new ComponentBuilder("Use /gems <name> <count>").color(ChatColor.GREEN).create());
                            if (strings.length == 2) {
                                UUID uuid = ProxyServer.getInstance().getPlayer(strings[0]).getUniqueId();
                                int count = Integer.valueOf(strings[1]);
                                ProfileProxy profile = Database.profileProxy.get(uuid);
                                profile.setGems(count + profile.getGems());
                                commandSender.sendMessage(new ComponentBuilder("Succes!").color(ChatColor.GREEN).create());
                            }
                        }
                    }
            );
        }
    }
}