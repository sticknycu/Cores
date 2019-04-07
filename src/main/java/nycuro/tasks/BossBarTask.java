package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

/**
 * author: GiantQuartz
 * HubCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    private Object2IntMap<String> timers = new Object2IntOpenHashMap<>();

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            Profile profile = Database.profile.get(player.getUniqueId());

            if (API.getMainAPI().bossbar.get(player.getName()) != null) {
                String username = player.getName();
                Integer playerTime = timers.getOrDefault(username, 1);
                if (profile != null) {
                    profile.setTime(3000 + profile.getTime());
                }
                String message = API.getMessageAPI().getMessageBossBar(player, playerTime);
                API.getMainAPI().bossbar.get(player.getName()).setText(message);
                if (playerTime <= 5)  timers.put(username, playerTime + 1);
                else timers.put(username, 1);
            }
        }
    }
}
