package nycuro.database.objects;

import lombok.Data;

@Data
public class Profile {

    public String name;
    public int language;

    public Profile(String name, int language) {
        this.name = name;
        this.language = language;
    }
}
