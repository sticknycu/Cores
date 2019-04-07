package nycuro.database.objects;

import lombok.Data;

@Data
public class Profile {

    public String name;
    public int language;
    public double gems;
    public long time;

    public Profile(String name, int language, double gems, long time) {
        this.name = name;
        this.language = language;
        this.gems = gems;
        this.time = time;
    }
}
