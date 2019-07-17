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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }
}
