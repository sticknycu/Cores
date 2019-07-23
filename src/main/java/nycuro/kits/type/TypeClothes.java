package nycuro.kits.type;

import lombok.Getter;

public enum TypeClothes {
    HELMET("Helmet"),
    ARMOR("Armor"),
    PANTS("Pants"),
    BOOTS("Boots");

    @Getter
    public String type;

    TypeClothes(String type) {
        this.type = type;
    }
}
