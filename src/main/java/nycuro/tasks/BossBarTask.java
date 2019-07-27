package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

/**
 * author: GiantQuartz
 * SkyblockCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    private int k = -1;

    @Override
    public void onRun(int i) {
        k++;
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());

            if (API.getMainAPI().bossbar.get(player.getUniqueId()) != null) {
                if (API.getCombatAPI().inCombat(player)) return;
                int level = 0;
                double necessary = 0;
                double count = 0;
                if (profile != null) {
                    level = profile.getLevel();
                    necessary = profile.getNecesary();
                    count = profile.getExperience();
                    profile.setTime(profile.getTime() + 1000);
                }
                String title = "&m&c&p&e&.&c&h&z&o&n&e&.&e&u";
                String[] split = title.split("&");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < split.length; j++) {
                    sb.append(split[j]);
                    if (j == k) {
                        sb.append("&c");
                    } else {
                        sb.append("&r");
                    }
                }
                if (k == split.length) k = -1;
                String p = TextFormat.colorize(sb.toString());

                String message = "";
                float health = 100F;
                if (API.getMechanicAPI().isOnArena(player) && API.getMechanicAPI().getBossHealth() != 0) {
                    health = API.getMechanicAPI().getBossHealth();
                    message = API.getMessageAPI().getMessageInArenaBossBar(player, Loader.round((double) API.getMechanicAPI().getBossHealth(), 2), p);
                } else {
                    message = API.getMessageAPI().getMessageBossBar(player, level, necessary, count, p);
                }
                API.getMainAPI().bossbar.get(player.getUniqueId()).setLength(health);
                API.getMainAPI().bossbar.get(player.getUniqueId()).setText(message);
            }
        }
    }
}
