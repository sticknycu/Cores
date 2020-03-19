package nycuro.jobs;

import cn.nukkit.Player;
import cn.nukkit.player.Player;

import java.util.function.Consumer;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public abstract class CommonJob {

    public abstract NameJob getName();

    public abstract void getReward(TypeJob typeJob, Consumer<Double> consumer);

    public abstract int getLevelNeeded(TypeJob typeJob);

    public abstract void processMission(Player player, TypeJob typeJob, Consumer<Object> consumer);

    public abstract boolean isLocked(Player player, TypeJob typeJob);

    public abstract StatusJobs getStatus(Player player, TypeJob typeJob);
}
