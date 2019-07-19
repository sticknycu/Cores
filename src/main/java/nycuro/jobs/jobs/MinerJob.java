package nycuro.jobs.jobs;

import nycuro.jobs.Job;
import nycuro.jobs.objects.JobObject;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public class MinerJob extends Job {
    private JobObject jobConfig;

    public MinerJob(JobObject jobConfig) {
        this.jobConfig = jobConfig;
    }

    @Override
    public void processMission() {

    }
}
