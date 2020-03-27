package nycuro.abuse.settings;

import cn.nukkit.block.BlockIds;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.ItemIds;
import cn.nukkit.utils.Identifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nycuro.abuse.settings.data.AbuseSettings;
import nycuro.api.API;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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

                List<Identifier> blocksAbuse = new ArrayList<>();
                blocksAbuse.add(BlockIds.IRON_ORE);
                blocksAbuse.add(BlockIds.GOLD_ORE);
                blocksAbuse.add(BlockIds.COAL_ORE);
                blocksAbuse.add(BlockIds.LAPIS_ORE);
                blocksAbuse.add(BlockIds.TNT);
                blocksAbuse.add(BlockIds.DIAMOND_ORE);
                blocksAbuse.add(BlockIds.REDSTONE_ORE);
                blocksAbuse.add(BlockIds.EMERALD_ORE);
                blocksAbuse.add(BlockIds.QUARTZ_ORE);
                blocksAbuse.add(BlockIds.LAPIS_BLOCK);
                blocksAbuse.add(BlockIds.GOLD_BLOCK);
                blocksAbuse.add(BlockIds.IRON_BLOCK);
                blocksAbuse.add(BlockIds.DIAMOND_BLOCK);
                blocksAbuse.add(BlockIds.REDSTONE_BLOCK);

                API.log(prefix + "Writing data...");

                fileWriter.write(serializeData(itemsAbuse, blocksAbuse, inventoryTypes));
                fileWriter.close();

                API.log(prefix + "Finished!");
            } else {
                API.log(prefix + "I've found an file... Let's get all information..");

                byte[] jsonData = Files.readAllBytes(file.toPath());

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode rootNode = objectMapper.readTree(jsonData);

                API.log(prefix + "Creating Maps..");

                List<Identifier> itemsAbuse = new ArrayList<>();
                List<Identifier> blocksAbuse = new ArrayList<>();
                Set<InventoryType> inventoryTypes = new HashSet<>();

                API.log(prefix + "Input information from Abuse Items to Map..");

                for (JsonNode jsonNodeItemsAbuse : rootNode.get("itemsAbuse")) {
                    itemsAbuse.add(Identifier.from(jsonNodeItemsAbuse.get("namespace").textValue(), jsonNodeItemsAbuse.get("name").textValue()));
                }

                API.log(prefix + "Input information from Abuse Blocks to Map..");

                for (JsonNode jsonNodeBlocksAbuse : rootNode.get("blockAbuse")) {
                    blocksAbuse.add(Identifier.from(jsonNodeBlocksAbuse.get("namespace").textValue(), jsonNodeBlocksAbuse.get("name").textValue()));
                }

                API.log(prefix + "Input information from Abuse Inventory to Map..");

                for (JsonNode jsonNodeInventoryAbuse : rootNode.get("inventoryAbuse")) {
                    inventoryTypes.add(InventoryType.valueOf(jsonNodeInventoryAbuse.textValue()));
                }

                API.log(prefix + "Add information to AbuseSettings..");

                abuseSettings = new AbuseSettings(itemsAbuse, blocksAbuse, inventoryTypes);

                API.log(prefix + "Done!");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private String serializeData(List<Identifier> itemsAbuse, List<Identifier> blocksAbuse, Set<InventoryType> inventoryType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        AbuseSettings abuseSettings = new AbuseSettings(itemsAbuse, blocksAbuse, inventoryType);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(abuseSettings);
    }
}
