package nycuro.abuse.settings;

import cn.nukkit.block.BlockIds;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.ItemIds;
import cn.nukkit.utils.Identifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nycuro.abuse.settings.data.AbuseSettings;
import nycuro.api.API;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class AbuseAPI {

    public AbuseSettings abuseSettings;

    private String prefix = "[ABUSE] ";

    public void init() {
        try {
            File file = new File(mainAPI.getDataFolder() + "/abuse/abuse.json");
            API.log(prefix + "Checking for existance of file... ");
            if (!file.exists()) {
                API.log(prefix + "I've not found a file... Let's put all information..");
                FileWriter fileWriter = new FileWriter(file);
                API.log(prefix + "Setting InventoryAbuse...");
                Set<InventoryType> inventoryTypes = new HashSet<>();
                inventoryTypes.add(InventoryType.CHEST);
                inventoryTypes.add(InventoryType.DOUBLE_CHEST);
                inventoryTypes.add(InventoryType.CRAFTING);
                inventoryTypes.add(InventoryType.FURNACE);
                inventoryTypes.add(InventoryType.ENDER_CHEST);
                inventoryTypes.add(InventoryType.HOPPER);
                inventoryTypes.add(InventoryType.DROPPER);
                inventoryTypes.add(InventoryType.BREWING_STAND);
                inventoryTypes.add(InventoryType.ENCHANT_TABLE);
                inventoryTypes.add(InventoryType.ANVIL);
                inventoryTypes.add(InventoryType.DISPENSER);
                inventoryTypes.add(InventoryType.WORKBENCH);
                API.log(prefix + "Setting ItemsAbuse...");
                List<Identifier> itemsAbuse = new ArrayList<>();
                itemsAbuse.add(ItemIds.EXPERIENCE_BOTTLE);
                itemsAbuse.add(ItemIds.IRON_INGOT);
                itemsAbuse.add(ItemIds.GOLD_INGOT);
                itemsAbuse.add(ItemIds.DIAMOND);
                API.log(prefix + "Setting BlocksAbuse...");
                List<Identifier> blockAbuse = new ArrayList<>();
                blockAbuse.add(BlockIds.IRON_ORE);
                blockAbuse.add(BlockIds.GOLD_ORE);
                blockAbuse.add(BlockIds.COAL_ORE);
                blockAbuse.add(BlockIds.LAPIS_ORE);
                blockAbuse.add(BlockIds.TNT);
                blockAbuse.add(BlockIds.DIAMOND_ORE);
                blockAbuse.add(BlockIds.REDSTONE_ORE);
                blockAbuse.add(BlockIds.EMERALD_ORE);
                blockAbuse.add(BlockIds.QUARTZ_ORE);
                blockAbuse.add(BlockIds.LAPIS_BLOCK);
                blockAbuse.add(BlockIds.GOLD_BLOCK);
                blockAbuse.add(BlockIds.IRON_BLOCK);
                blockAbuse.add(BlockIds.DIAMOND_BLOCK);
                blockAbuse.add(BlockIds.REDSTONE_BLOCK);
                API.log(prefix + "Writing data...");
                fileWriter.write(serializeData(itemsAbuse, blockAbuse, inventoryTypes));
                fileWriter.close();
                API.log(prefix + "Finished!");
            } else {
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void saveConfig(List<Identifier> itemsAbuse, List<Identifier> blockAbuse, Set<InventoryType> inventoryType) {
        try {
            File file = new File(mainAPI.getDataFolder() + "/abuse/abuse.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(serializeData(itemsAbuse, blockAbuse, inventoryType));
            fileWriter.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private String serializeData(List<Identifier> itemsAbuse, List<Identifier> blockAbuse, Set<InventoryType> inventoryType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        AbuseSettings abuseSettings = new AbuseSettings(itemsAbuse, blockAbuse, inventoryType);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(abuseSettings);
    }
}
