package nycuro.kits.type;

import lombok.Getter;
import lombok.Setter;

public enum TypeKit {
    STARTER("Starter"),
    ENCHANTED_STARTER("Enchanted Starter"),
    GUARDIAN("Guardian"),
    KNIGHT("Knight"),
    PALADIN("Paladin"),
    SPARROW("Sparrow");

    @Getter
    @Setter
    public String name;

    TypeKit(String name) {
        this.name = name;
    }
}
