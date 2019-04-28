package nycuro.database.objects;

import lombok.Data;

@Data
public class ProfileProxy {

    public String name;
    public int language;
    public double gems;
    public long time;
    public int votes;

    public ProfileProxy(String name, int language, double gems, long time, int votes) {
        this.name = name;
        this.language = language;
        this.gems = gems;
        this.time = time;
        this.votes = votes;
    }
}