package nycuro.chat.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import cn.nukkit.utils.TextFormat;
import me.lucko.luckperms.api.LuckPermsApi;
import nycuro.API;
import nycuro.api.JobsAPI;
import nycuro.chat.ChatFormat;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

import java.util.Objects;

/**
 * author: uselesswaifu
 * FactionsCore Project
 * API 1.0.0
 */
public class ChatHandlers implements Listener {

    private LuckPermsApi api;

    public ChatHandlers() {
        RegisteredServiceProvider<LuckPermsApi> provider = API.getMainAPI().getServer().getServiceManager().getProvider(LuckPermsApi.class);
        if (provider != null) {
            api = provider.getProvider();
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String group = Objects.requireNonNull(api.getUser(player.getUniqueId())).getPrimaryGroup().toUpperCase();
        String s = ChatFormat.valueOf(group).toString();
        s = s.replace("%name", player.getName());
        s = s.replace("%msg", event.getMessage());
        int job = 0;
        Profile profile = Database.profile.get(player.getUniqueId());
        if (profile != null) {
            job = Database.profile.get(player.getUniqueId()).getJob();
        }
        s = s.replace("%job", JobsAPI.jobs.get(job));
        s = s.replace("%lvl", String.valueOf(player.getExperienceLevel()));
        s = TextFormat.colorize(s);
        event.setFormat(s);
    }
}
