package nycuro.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import nycuro.jobs.JobType;
import nycuro.jobs.objects.JobObject;

import java.io.File;

/**
 * Project: CHPECores
 * Author: Gabitzuu
 */
public class ConfigManager {
    private JobObject job;

    public JobObject getJob(JobType jobType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            job = mapper.readValue(new File("/root/missions/" + jobType.getName() + ".yml"), JobObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }
}
