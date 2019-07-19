package nycuro.jobs;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public enum JobType {
    MINER("miner"),
    FARMER("farmer");

    private String name;

    JobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
