package nycuro.database.objects;

import lombok.Data;

@Data
public class ProfileHub {

    public String name;
    public int language;
    public double gems;
    public long time;

    public ProfileHub(String name, int language, double gems, long time) {
        this.name = name;
        this.language = language;
        this.gems = gems;
        this.time = time;
    }
}