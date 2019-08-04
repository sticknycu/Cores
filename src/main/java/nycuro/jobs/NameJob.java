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

    public static NameJob getType(int id) {
        for (NameJob job : NameJob.values()) {
            if (job.getId() == id) {
                return job;
            }
        }
        return NameJob.NO_JOB;
    }
}
