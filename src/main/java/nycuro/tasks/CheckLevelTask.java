package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.Profile;
import nycuro.utils.MechanicUtils;

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
            double necesary = 0;
            Profile profile = Database.profile.get(player.getUniqueId());
            if (profile != null) {
                experience = profile.getExperience();
                level = profile.getLevel();
                necesary = profile.getNecesary();
            }
            if (level == 0) {
                if (experience >= 250) {
                    profile.addLevel(1);
                    profile.setExperience(0.0);
                    profile.setNecesary(250.0);
                }
            } else if (level > 0) {
                if (experience >= necesary) {
                    profile.addLevel(1);
                    profile.setExperience(0.0);
                    profile.setNecesary(profile.getNecesary() + 250.0);
                }
            }
        }
    }
}
