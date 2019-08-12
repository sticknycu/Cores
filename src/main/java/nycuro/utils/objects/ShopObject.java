package nycuro.utils.objects;

import lombok.Getter;
import lombok.Setter;

public class ShopObject {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int id, meta;

    @Getter
    @Setter
    private double cost;

    public ShopObject(String name, int id, int meta, double cost) {
        this.name = name;
        this.id = id;
        this.meta = meta;
        this.cost = cost;
    }

}
