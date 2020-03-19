package nycuro.jobs.data;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import nycuro.database.Database;
import nycuro.jobs.CommonJob;
import nycuro.jobs.NameJob;
import nycuro.jobs.StatusJobs;
import nycuro.jobs.TypeJob;
import nycuro.utils.typo.FastRandom;

import java.util.function.Consumer;

import static nycuro.api.API.messageAPI;

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
        int level = Database.profileSkyblock.get(player.getName()).getLevel();
        return level < getLevelNeeded(typeJob);
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
            player.sendMessage(messageAPI.messagesObject.translateMessage("jobs.locked"));
        } else {
            Item[] collection = new Item[2];
            if (typeJob.equals(TypeJob.EASY)) {
                FastRandom.current().ints(1, 10, 15).findFirst().ifPresent((j) -> {
                    collection[0] = Item.get(Item.RAW_FISH, 0, j);
                });
                FastRandom.current().ints(1, 10, 15).findFirst().ifPresent((j) -> {
                    collection[1] = Item.get(Item.RAW_SALMON, 0, j);
                });
            } else if (typeJob.equals(TypeJob.MEDIUM)) {
                FastRandom.current().ints(1, 15, 25).findFirst().ifPresent((j) -> {
                    collection[0] = Item.get(Item.RAW_FISH, 0, j);
                });
                FastRandom.current().ints(1, 15, 25).findFirst().ifPresent((j) -> {
                    collection[1] = Item.get(Item.RAW_SALMON, 0, j);
                });
            } else if (typeJob.equals(TypeJob.HARD)) {
                FastRandom.current().ints(1, 25, 35).findFirst().ifPresent((j) -> {
                    collection[0] = Item.get(Item.RAW_FISH, 0, j);
                });
                FastRandom.current().ints(1, 25, 35).findFirst().ifPresent((j) -> {
                    collection[1] = Item.get(Item.RAW_SALMON, 0, j);
                });
            } else {
                FastRandom.current().ints(1, 45, 60).findFirst().ifPresent((j) -> {
                    collection[0] = Item.get(Item.RAW_FISH, 0, j);
                });
                FastRandom.current().ints(1, 45, 60).findFirst().ifPresent((j) -> {
                    collection[1] = Item.get(Item.RAW_SALMON, 0, j);
                });
            }
            consumer.accept(collection);
        }
    }
}
