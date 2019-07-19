package nycuro.jobs;

import cn.nukkit.entity.Entity;

import java.util.ArrayList;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public abstract class Job {
    public String jobName;
    public Entity jobNPC;
    public ArrayList<Mission> missions;

    public abstract void processMission();
}
