package nycuro.jobs.objects;

import cn.nukkit.item.Item;
import lombok.Data;

import java.util.UUID;

@Data
public class JobsObject {

    public UUID user;
    public int job;
    public Item[] items;
    public int[] countAnimals;
    public double reward;

    public JobsObject(UUID user, int job, Item[] items, int[] countAnimals, double reward) {
        this.user = user;
        this.job = job;
        this.items = items;
        this.countAnimals = countAnimals;
        this.reward = reward;
    }
}
