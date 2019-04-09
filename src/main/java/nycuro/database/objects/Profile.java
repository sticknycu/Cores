package nycuro.database.objects;

public class Profile {

    public String name;
    public int language;
    public int job;
    public int kills;
    public int deaths;
    public long cooldown;
    public double experience;
    public int level;
    public double necesary;
    public double coins;
    public long time;

    public Profile(String name, int language, int job, int kills, int deaths, long cooldown, double experience, int level, double necesary, double coins, long time) {
        this.name = name;
        this.language = language;
        this.job = job;
        this.kills = kills;
        this.deaths = deaths;
        this.cooldown = cooldown;
        this.experience = experience;
        this.level = level;
        this.necesary = necesary;
        this.coins = coins;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
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

    public void addKills(int kills) {
        setKills(getKills() + kills);
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeaths(int deaths) {
        setDeaths(getDeaths() + deaths);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(int level) {
        setLevel(getLevel() + level);
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

    public void addExperience(double experience) {
        setExperience(getExperience() + experience);
    }

    public double getNecesary() {
        return necesary;
    }

    public void setNecesary(double necesary) {
        this.necesary = necesary;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public void addCoins(double coins) {
        setCoins(getCoins() + coins);
    }

    public void reduceCoins(double coins) {
        setCoins(getCoins() - coins);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void addTime(long time) {
        setTime(getTime() + time);
    }
}
