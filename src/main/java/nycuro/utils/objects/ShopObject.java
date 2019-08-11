package nycuro.utils.objects;

import lombok.Getter;
import lombok.Setter;

public class ShopObject {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double id, meta, cost;

    public ShopObject(String name, double id, double meta, double cost) {
        this.name = name;
        this.id = id;
        this.meta = meta;
        this.cost = cost;
    }

}
