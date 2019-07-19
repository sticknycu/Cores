package nycuro.jobs.missions;

import cn.nukkit.Player;
import nycuro.API;
import nycuro.jobs.Mission;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */

public class MinerMission extends Mission {
    private Player player;

    public MinerMission(Player player) {
        this.player = player;
    }

    @Override
    public void executeReward(Player player) {
        API.getMainAPI().getServer().getLogger().info("executeReward atins");
    }

    @Override
    public void processMission() {

    }
}
