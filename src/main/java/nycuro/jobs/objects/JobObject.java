package nycuro.jobs.objects;

import java.util.Map;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public class JobObject {
    private String name;
    private Map<String, Double> npc;
    private Map<String, Integer> required_items;
    private String[] rewards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getNPC() {
        return npc;
    }

    public void setNPC(Map<String, Double> npc) {
        this.npc = npc;
    }

    public Map<String, Integer> getRequiredItems() {
        return required_items;
    }

    public void setRequiredItems(Map<String, Integer> required_items) {
        this.required_items = required_items;
    }

    public String[] getRewards() {
        return rewards;
    }

    public void setRewards(String[] rewards) {
        this.rewards = rewards;
    }
}
