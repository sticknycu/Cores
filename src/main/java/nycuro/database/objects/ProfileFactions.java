package nycuro.database.objects;

import lombok.Data;

@Data
public class ProfileFactions {

    public String name;
    public int job;
    public int kills;
    public int deaths;
    public long cooldown;
    public double experience;
    public int level;
    public double necesary;
    public long time;
    public double dollars;

    public ProfileFactions(String name, int job, int kills, int deaths, long cooldown, double experience, int level, double necesary, long time, double dollars) {
        this.name = name;
        this.job = job;
        this.kills = kills;
        this.deaths = deaths;
        this.cooldown = cooldown;
        this.experience = experience;
        this.level = level;
        this.necesary = necesary;
        this.time = time;
        this.dollars = dollars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getNecesary() {
        return necesary;
    }

    public void setNecesary(double necesary) {
        this.necesary = necesary;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }
}
