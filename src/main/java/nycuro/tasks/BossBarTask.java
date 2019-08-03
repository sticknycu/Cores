package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.Loader;
import nycuro.api.API;
import nycuro.database.DatabaseMySQL;
import nycuro.database.objects.ProfileSkyblock;

/**
 * author: GiantQuartz
 * SkyblockCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileSkyblock profile = DatabaseMySQL.profileSkyblock.get(player.getName());

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

                String message = "";
                float health = 100F;
                if (API.getMechanicAPI().isOnArena(player) && API.getMechanicAPI().getBossHealth() != 0) {
                    health = API.getMechanicAPI().getBossHealth();
                    message = API.getMessageAPI().getMessageInArenaBossBar(player, Loader.round((double) API.getMechanicAPI().getBossHealth(), 2));
                } else {
                    message = API.getMessageAPI().getMessageBossBar(player, level, necessary, count);
                }
                API.getMainAPI().bossbar.get(player.getUniqueId()).setLength(health);
                API.getMainAPI().bossbar.get(player.getUniqueId()).setText(message);
            }
        }
    }
}
