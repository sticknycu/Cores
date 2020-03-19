package nycuro.messages.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.player.Player;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import cn.nukkit.utils.TextFormat;
import me.lucko.luckperms.api.LuckPermsApi;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.messages.ChatFormat;

import java.util.Objects;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.mechanicAPI;

/**
 * author: uselesswaifu
 * SkyblockCore Project
 * API 1.0.0
 */
public class ChatHandlers implements Listener {

    public static LuckPermsApi api;
    public static int count = 0;

    public ChatHandlers() {
        RegisteredServiceProvider<LuckPermsApi> provider = mainAPI.getServer().getServiceManager().getProvider(LuckPermsApi.class);
        if (provider != null) {
            api = provider.getProvider();
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        count++;
        String group = Objects.requireNonNull(api.getUser(player.getUniqueId())).getPrimaryGroup().toUpperCase();
        String s = ChatFormat.valueOf(group).toString();
        String message = event.getMessage();
        if (!player.hasPermission("core.colorchat")) {
            message = TextFormat.clean(message);
        }
        s = s.replace("%name", player.getName());
        s = s.replace("%msg", message);
        if (count % 2 == 0)
            s = s.replace("%slash", "\\");
        else
            s = s.replace("%slash", "/");
        String lvl = "";
        if (profileSkyblock != null) {
            lvl = String.valueOf(profileSkyblock.getLevel());
        }
        s = s.replace("%lvl", lvl);
        s = TextFormat.colorize(s);

        if (mainAPI.staffChat.contains(player.getUniqueId())) {
            event.setFormat(mechanicAPI.staffchatTag + s);
        }  else {
            event.setFormat(s);
        }

        // StaffChat
        if (player.hasPermission("core.staffchat") && message.indexOf("@") == 0) {
            mechanicAPI.handleStaffChat(player, s, 0);
            event.setCancelled();
        }

        if (mainAPI.staffChat.contains(player.getUniqueId())) {
            mechanicAPI.handleStaffChat(player, s, 1);
            event.setCancelled();
        }
    }
}
