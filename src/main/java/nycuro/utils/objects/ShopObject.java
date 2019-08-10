package nycuro.utils.objects;

import lombok.Getter;
import lombok.Setter;

public class ShopObject {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int id, meta, cost;

    public ShopObject(String name, int id, int meta, int cost) {
        this.name = name;
        this.id = id;
        this.meta = meta;
        this.cost = cost;
    }

}
