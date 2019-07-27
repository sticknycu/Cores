package nycuro.kits;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import lombok.Getter;
import lombok.Setter;
import nycuro.kits.type.StatusKit;
import nycuro.kits.type.NameKit;
import nycuro.kits.type.TypeKit;

public abstract class CommonKit {

    @Getter
    public Item helmet;

    @Getter
    public Item pants;

    @Getter
    public Item armor;

    @Getter
    public Item boots;

    @Getter
    public Item sword;

    @Getter
    public Item pickaxe;

    @Getter
    public Item axe;

    @Getter
    public Item shovel;

    @Getter
    public Item[] otherItems;

    @Getter
    public Item[] armorContents;

    @Getter
    public Item[] inventoryContents;

    @Getter
    public double price;

    @Getter
    public long timer;

    @Getter
    public TypeKit type;

    @Getter
    @Setter
    public NameKit kit;

    public String symbol = TextFormat.GOLD.toString();
    public String empty = " ";

    public abstract boolean passTimer(Player player);

    public abstract void sendKit(Player player);

    public abstract boolean canAddKit(Player player);

    public abstract boolean hasEnoughDollars(Player player);

    public abstract StatusKit getStatus(Player player);
}
