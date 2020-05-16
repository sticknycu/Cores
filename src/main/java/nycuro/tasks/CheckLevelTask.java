package nycuro.tasks;


import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import nycuro.database.Database;
import nycuro.database.objects.ProfileSkyblock;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class CheckLevelTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            double experience = 0;
            double necesary = 250;
            ProfileSkyblock profile = Database.profileSkyblock.get(player.getName());
            if (profile != null) {
                experience = profile.getExperience();
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
