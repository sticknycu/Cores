package nycuro.kits.type;

import lombok.Getter;
import lombok.Setter;

public enum NameKit {
    // Clasic
    STARTER("Starter"),
    ENCHANTED_STARTER("Enchanted Starter"),
    GUARDIAN("Guardian"),
    KNIGHT("Knight"),
    PALADIN("Paladin"),
    SPARROW("Sparrow"),
    // Premium
    VIPER("Viper"),
    MASTER("Master"),
    KILLER("Killer"),
    DETONATOR("Detonator"),
    TURRET_MONKEY("Turret Monkey"),
    // Specific
    PLANTER("Planter"),
    STONNER("Stonner"),
    LACKER("Lacker"),
    WOODIE("Woodie"),
    PUSH_UP("Push Up"),
    DIGGER("Digger");

    @Getter
    @Setter
    public String name;

    NameKit(String name) {
        this.name = name;
    }
}
