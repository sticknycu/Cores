package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CheckLevelTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            double experience = 0;
            int level = 0;
            double necesary = 250;
            ProfileFactions profile = Database.profileFactions.get(player.getUniqueId());
            if (profile != null) {
                experience = profile.getExperience();
                level = profile.getLevel();
                necesary = profile.getNecesary();
            }
            if (experience >= necesary) {
                profile.setLevel(profile.getLevel() + 1);
                profile.setExperience(0.0);
                profile.setNecesary(profile.getNecesary() + 250.0);
            }
        }
    }
}
