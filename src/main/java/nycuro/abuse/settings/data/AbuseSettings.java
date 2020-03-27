package nycuro.abuse.settings.data;

import cn.nukkit.inventory.InventoryType;
import cn.nukkit.utils.Identifier;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class AbuseSettings {
    protected List<Identifier> itemsAbuse;

    protected List<Identifier> blocksAbuse;

    protected Set<InventoryType> inventoryAbuse;

    public AbuseSettings(List<Identifier> itemsAbuse, List<Identifier> blocksAbuse, Set<InventoryType> inventoryType) {
        this.itemsAbuse = new ArrayList<>(itemsAbuse);
        this.blocksAbuse = new ArrayList<>(blocksAbuse);
        this.inventoryAbuse = inventoryType;
    }
}
