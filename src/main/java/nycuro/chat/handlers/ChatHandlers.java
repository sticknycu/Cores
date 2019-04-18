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
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileHub;

import java.util.Objects;

/**
 * author: uselesswaifu
 * FactionsCore Project
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
        ProfileHub profileHub = Database.profileHub.get(player.getUniqueId());
        ProfileFactions profileFactions = Database.profileFactions.get(player.getUniqueId());
        count++;
        String group = Objects.requireNonNull(api.getUser(player.getUniqueId())).getPrimaryGroup().toUpperCase();
        String s = ChatFormat.valueOf(group).toString();
        s = s.replace("%name", player.getName());
        s = s.replace("%msg", event.getMessage());
        if (count % 2 == 0)
            s = s.replace("%slash", "\\");
        else
            s = s.replace("%slash", "/");
        int job = 0;
        String lvl = "";
        if (profileFactions != null) {
            job = profileFactions.getJob();
            lvl = String.valueOf(profileFactions.getLevel());
        }
        s = s.replace("%job", JobsAPI.jobs.get(job));
        s = s.replace("%lvl", lvl);
        s = TextFormat.colorize(s);
        event.setFormat(s);
    }
}
