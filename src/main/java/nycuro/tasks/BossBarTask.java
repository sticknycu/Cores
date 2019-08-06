package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.mechanicAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.combatAPI;

/**
 * author: GiantQuartz
 * SkyblockCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());

            if (mainAPI.bossbar.get(player.getUniqueId()) != null) {

                if (combatAPI.inCombat(player)) return;
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
                if (mechanicAPI.isOnArena(player) && mechanicAPI.getBossHealth() != 0) {
                    health = mechanicAPI.getBossHealth();
                    message = messageAPI.getMessageInArenaBossBar(player, API.round(mechanicAPI.getBossHealth(), 2));
                } else {
                    message = messageAPI.getMessageBossBar(player, level, necessary, count);
                }
                mainAPI.bossbar.get(player.getUniqueId()).setLength(health);
                mainAPI.bossbar.get(player.getUniqueId()).setText(message);
            }
        }
    }
}
