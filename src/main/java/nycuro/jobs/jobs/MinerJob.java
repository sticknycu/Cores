package nycuro.jobs.jobs;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.jobs.CommonJob;
import nycuro.jobs.NameJob;
import nycuro.jobs.StatusJobs;
import nycuro.jobs.TypeJob;
import nycuro.utils.typo.FastRandom;

import java.util.function.Consumer;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public class MinerJob extends CommonJob {

    @Override
    public NameJob getName() {
        return NameJob.MINER;
    }

    @Override
    public int getLevelNeeded(TypeJob typeJob) {
        switch (typeJob) {
            case EASY:
                return 0;
            case MEDIUM:
                return 35;
            case HARD:
                return 55;
            case EXTREME:
                return 75;
        }
        return 0;
    }

    @Override
    public boolean isLocked(Player player, TypeJob typeJob) {
        int level = Database.profileSkyblock.get(player.getName()).getLevel();
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
            FastRandom.current().doubles(1, 350, 500).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.MEDIUM)) {
            FastRandom.current().doubles(1, 500, 800).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else if (typeJob.equals(TypeJob.HARD)) {
            FastRandom.current().doubles(1, 800, 1200).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        } else {
            FastRandom.current().doubles(1, 1200, 1500).findFirst().ifPresent( (j) -> {
                consumer.accept(j);
            });
        }
    }

    @Override
    public void processMission(Player player, TypeJob typeJob, Consumer<Object> consumer) {
        if (getStatus(player, typeJob).equals(StatusJobs.LOCKED)) {
            player.sendMessage(API.getMessageAPI().sendLockedJobStatus(player));
        } else {
            Item[] itemsMap = new Item[10];
            if (typeJob.equals(TypeJob.EASY)) {
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[0] = Item.get(Item.COBBLESTONE, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[1] = Item.get(Item.STONE, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[2] = Item.get(Item.IRON_ORE, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[3] = Item.get(Item.COAL_ORE, 0, j);
                });
            } else if (typeJob.equals(TypeJob.MEDIUM)) {
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap[0] = Item.get(Item.STONE, 0, j);
                });
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap[1] = Item.get(Item.GOLD_ORE, 0, j);
                });
                FastRandom.current().ints(1, 12, 36).findFirst().ifPresent((j) -> {
                    itemsMap[2] = Item.get(Item.IRON_ORE, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[3] = Item.get(Item.COAL_ORE, 0, j);
                });
            } else if (typeJob.equals(TypeJob.HARD)) {
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap[0] = Item.get(Item.STONE, 0, j);
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap[1] = Item.get(Item.GOLD_ORE, 0, j);
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap[2] = Item.get(Item.IRON_ORE, 0, j);
                });
                FastRandom.current().ints(1, 5, 10).findFirst().ifPresent((j) -> {
                    itemsMap[3] = Item.get(Item.DIAMOND, 0, j);
                });
                FastRandom.current().ints(1, 3, 5).findFirst().ifPresent((j) -> {
                    itemsMap[4] = Item.get(Item.EMERALD, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[5] = Item.get(Item.COAL_ORE, 0, j);
                });
            } else {
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap[0] = Item.get(Item.STONE, 0, j);
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap[1] = Item.get(Item.COBBLESTONE, 0, j);
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap[2] = Item.get(Item.GOLD_INGOT, 0, j);
                });
                FastRandom.current().ints(1, 24, 36).findFirst().ifPresent((j) -> {
                    itemsMap[3] = Item.get(Item.IRON_INGOT, 0, j);
                });
                FastRandom.current().ints(1, 5, 10).findFirst().ifPresent((j) -> {
                    itemsMap[4] = Item.get(Item.DIAMOND, 0, j);
                });
                FastRandom.current().ints(1, 3, 5).findFirst().ifPresent((j) -> {
                    itemsMap[5] = Item.get(Item.EMERALD, 0, j);
                });
                FastRandom.current().ints(1, 8, 24).findFirst().ifPresent((j) -> {
                    itemsMap[6] = Item.get(Item.COAL_ORE, 0, j);
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap[7] = Item.get(Item.REDSTONE_DUST, 0, j);
                });
                FastRandom.current().ints(1, 36, 64).findFirst().ifPresent((j) -> {
                    itemsMap[8] = Item.get(Item.REDSTONE, 0, j);
                });
            }
            consumer.accept(itemsMap);
        }
    }
}
