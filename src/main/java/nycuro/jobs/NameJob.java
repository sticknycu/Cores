package nycuro.jobs;

import lombok.Getter;
import lombok.Setter;

/**
 * Project: SkyblockCore
 * Author: NycuRO
 */
public enum NameJob {
    NO_JOB(" ", 0),
    MINER("Miner", 1),
    BUTCHER("Butcher", 2),
    FARMER("Farmer", 3),
    FISHERMAN("Fisherman", 4);

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public int id;

    NameJob(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String getType(int id) {
        switch (id) {
            case 0:
                return NameJob.NO_JOB.getName();
            case 1:
                return NameJob.MINER.getName();
            case 2:
                return NameJob.BUTCHER.getName();
            case 3:
                return NameJob.FARMER.getName();
            case 4:
                return NameJob.FISHERMAN.getName();
        }
        return NameJob.NO_JOB.getName();
    }
}
