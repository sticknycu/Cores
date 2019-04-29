package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: GiantQuartz
 * FactionsCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileFactions profile = Database.profileFactions.get(player.getName());

            if (API.getMainAPI().bossbar.get(player.getName()) != null) {
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
                String message = API.getMessageAPI().getMessageBossBar(player, level, necessary, count);
                API.getMainAPI().bossbar.get(player.getName()).setText(message);
            }
        }
    }
}
