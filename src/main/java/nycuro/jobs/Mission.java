package nycuro.jobs;

import cn.nukkit.Player;

import java.util.ArrayList;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public abstract class Mission {
    public ArrayList<String> rewardCommands;

    public abstract void executeReward(Player player);

    public abstract void processMission();
}
