package nycuro.database.objects;

import lombok.Data;

@Data
public class HomeObject {

    public String name;
    public int x;
    public int y;
    public int z;
    public String worldName;
    public String homeName;

    public HomeObject(String name, int x, int y, int z, String worldName, String homeName) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
        this.homeName = homeName;
    }
}
