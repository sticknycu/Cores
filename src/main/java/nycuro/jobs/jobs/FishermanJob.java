package nycuro.jobs.jobs;

import cn.nukkit.Player;
import nycuro.api.API;
import nycuro.database.DatabaseMySQL;
import nycuro.jobs.CommonJob;
import nycuro.jobs.NameJob;
import nycuro.jobs.StatusJobs;
import nycuro.jobs.TypeJob;
import nycuro.utils.typo.FastRandom;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public class FishermanJob extends CommonJob {

    @Override
    public NameJob getName() {
        return NameJob.FISHERMAN;
    }

    @Override
    public int getLevelNeeded(TypeJob typeJob) {
        switch (typeJob) {
            case EASY:
                return 5;
            case MEDIUM:
                return 30;
            case HARD:
                return 50;
            case EXTREME:
                return 70;
        }
        return 0;
    }

    @Override
    public boolean isLocked(Player player, TypeJob typeJob) {
        int level = DatabaseMySQL.profileSkyblock.get(player.getName()).getLevel();
        return level >= getLevelNeeded(typeJob);
    }

    @Override
    public StatusJobs getStatus(Player player, TypeJob typeJob) {
        if (isLocked(player, typeJob)) return StatusJobs.LOCKED;
        else return StatusJobs.UNLOCKED;
    }

    @Override
    public void getReward(TypeJob typeJob, Consumer<Double> consumer) {
        if (typeJob.equals(TypeJob.EASY)) {
            FastRandom.current().doubles(1, 600, 850).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.MEDIUM)) {
            FastRandom.current().doubles(1, 900, 1000).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.HARD)) {
            FastRandom.current().doubles(1, 900, 1200).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else {
            FastRandom.current().doubles(1, 1000, 1300).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        }
    }

    @Override
    public void processMission(Player player, TypeJob typeJob, Consumer<Object> consumer) {
        if (getStatus(player, typeJob).equals(StatusJobs.LOCKED)) {
            player.sendMessage(API.getMessageAPI().sendLockedJobStatus(player));
        } else {
            Collection<Integer> collection = new HashSet<>();
            if (typeJob.equals(TypeJob.EASY)) {
                FastRandom.current().ints(2, 10, 15).findFirst().ifPresent((j) -> {
                    collection.add(j);
                });
            } else if (typeJob.equals(TypeJob.MEDIUM)) {
                FastRandom.current().ints(2, 15, 25).findFirst().ifPresent((j) -> {
                    collection.add(j);
                });
            } else if (typeJob.equals(TypeJob.HARD)) {
                FastRandom.current().ints(2, 35, 40).findFirst().ifPresent((j) -> {
                    collection.add(j);
                });
            } else {
                FastRandom.current().ints(2, 45, 60).findFirst().ifPresent((j) -> {
                    collection.add(j);
                });
            }
            consumer.accept(collection);
        }
    }
}
