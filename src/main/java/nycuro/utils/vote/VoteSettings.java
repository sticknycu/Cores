package nycuro.utils.vote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nycuro.utils.objects.VotePartyObject;
import nycuro.utils.objects.MechanicDropParty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * RoleplayCore Project
 * API 1.0.0
 */
public class VoteSettings {

    public MechanicDropParty mechanic = new MechanicDropParty();

    public void init() {
        try {
            File file = new File(mainAPI.getDataFolder() + "/config.json");
            if (!file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(serializeData(0, 0));
                fileWriter.close();
            } else {
                byte[] jsonData = Files.readAllBytes(file.toPath());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonData);
                JsonNode jsonNodeMechanic = rootNode.path("mechanic");

                mechanic.setDropParty(Integer.valueOf(jsonNodeMechanic.get("dropParty").toString()));
                mechanic.setTimeDropParty(Long.valueOf(jsonNodeMechanic.get("timeDropParty").toString()));
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void saveConfig(int dropParty, long timeDropParty) {
        try {
            File file = new File(mainAPI.getDataFolder() + "/config.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(serializeData(dropParty, timeDropParty));
            fileWriter.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private String serializeData(int dropParty, long timeDropParty) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        VotePartyObject json = new VotePartyObject();
        mechanic.setDropParty(dropParty);
        mechanic.setTimeDropParty(timeDropParty);

        json.mechanic = mechanic;

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }
}