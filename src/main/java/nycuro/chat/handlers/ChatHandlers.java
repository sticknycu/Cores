package nycuro.chat.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import cn.nukkit.utils.TextFormat;
import me.lucko.luckperms.api.LuckPermsApi;
import nycuro.api.API;
import nycuro.jobs.api.JobsAPI;
import nycuro.chat.ChatFormat;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

import java.util.Objects;

/**
 * author: uselesswaifu
 * SkyblockCore Project
 * API 1.0.0
 */
public class ChatHandlers implements Listener {

    public static LuckPermsApi api;
    private int count = 0;

    public ChatHandlers() {
        RegisteredServiceProvider<LuckPermsApi> provider = API.getMainAPI().getServer().getServiceManager().getProvider(LuckPermsApi.class);
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
        int job = 0;
        String lvl = "";
        if (profileSkyblock != null) {
            job = profileSkyblock.getJob();
            lvl = String.valueOf(profileSkyblock.getLevel());
        }
        s = s.replace("%job", JobsAPI.jobs.get(job));
        s = s.replace("%lvl", lvl);
        s = TextFormat.colorize(s);

        if (API.getMainAPI().staffChat.contains(player.getUniqueId())) {
            event.setFormat(API.getMechanicAPI().staffchatTag + s);
        }  else {
            event.setFormat(s);
        }

        // StaffChat
        if (player.hasPermission("core.staffchat")) {
            API.getMechanicAPI().handleStaffChat(player, message, 0);
        }

        if (API.getMainAPI().staffChat.contains(player.getUniqueId())) {
            API.getMechanicAPI().handleStaffChat(player, message, 1);
        }
    }
}
