package nycuro.jobs.jobs;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
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
public class FarmerJob extends CommonJob {

    @Override
    public NameJob getName() {
        return NameJob.FARMER;
    }

    @Override
    public int getLevelNeeded(TypeJob typeJob) {
        switch (typeJob) {
            case EASY:
                return 10;
            case MEDIUM:
                return 25;
            case HARD:
                return 45;
            case EXTREME:
                return 65;
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
            FastRandom.current().doubles(1, 500, 750).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.MEDIUM)) {
            FastRandom.current().doubles(1, 750, 900).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.HARD)) {
            FastRandom.current().doubles(1, 1000, 1350).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else {
            FastRandom.current().doubles(1, 1500, 2000).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        }
    }

    @Override
    public void processMission(Player player, TypeJob typeJob, Consumer<Object> consumer) {
        if (getStatus(player, typeJob).equals(StatusJobs.LOCKED)) {
            player.sendMessage(API.getMessageAPI().sendLockedJobStatus(player));
        } else {
            Collection<Item> itemsMap = new HashSet<>();
            if (typeJob.equals(TypeJob.EASY)) {
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.SEEDS, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.CARROT, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.POTATO, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.HAY_BALE, 0, j));
                });
            } else if (typeJob.equals(TypeJob.MEDIUM)) {
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.SEEDS, 0, j));
                });
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.CARROT, 0, j));
                });
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.POTATO, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.HAY_BALE, 0, j));
                });
            } else if (typeJob.equals(TypeJob.HARD)) {
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.SEEDS, 0, j));
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.CARROT, 0, j));
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.POTATO, 0, j));
                });
                FastRandom.current().ints(1, 5, 10).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.HAY_BALE, 0, j));
                });
                FastRandom.current().ints(1, 3, 5).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.RED_FLOWER, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.DOUBLE_PLANT, 0, j));
                });
            } else {
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.SEEDS, 0, j));
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.CARROT, 0, j));
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.POTATO, 0, j));
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.HAY_BALE, 0, j));
                });
                FastRandom.current().ints(1, 5, 10).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.RED_FLOWER, 0, j));
                });
                FastRandom.current().ints(1, 3, 5).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.DOUBLE_PLANT, 0, j));
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.RED_FLOWER, 1, j));
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.RED_FLOWER, 3, j));
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap.add(Item.get(Item.DOUBLE_PLANT, 1, j));
                });
            }
            consumer.accept(itemsMap);
        }
    }
}
