package nycuro.kits.type;

import lombok.Getter;

public enum TypeItems {
    SWORD("Sword"),
    PICKAXE("Pickaxe"),
    AXE("Axe"),
    SHOVEL("Shovel");

    @Getter
    public String type;

    TypeItems(String type) {
        this.type = type;
    }
}
