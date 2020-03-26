package nycuro.utils.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopObject {
    private String name;

    private int id, meta;

    private double cost;

    public ShopObject(String name, int id, int meta, double cost) {
        this.name = name;
        this.id = id;
        this.meta = meta;
        this.cost = cost;
    }

}
