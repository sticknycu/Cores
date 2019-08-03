package nycuro.utils.vote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nycuro.utils.objects.JsonObject;
import nycuro.utils.objects.MechanicDropParty;

import java.io.*;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class VoteSettings {

    public MechanicDropParty mechanic = new MechanicDropParty();

    public void init() {
        try {
            File file = new File("/root/configs/config.json");
            if (!file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(serializeData(0, 0));
                fileWriter.close();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode settings = mapper.reader().readTree(new FileReader(file));
                JsonNode jsonNodeMechanic = settings.get("mechanic");

                mechanic.setDropParty(Integer.valueOf(jsonNodeMechanic.get("dropParty").toString()));
                mechanic.setTimeDropParty(Long.valueOf(jsonNodeMechanic.get("timeDropParty").toString()));
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void saveConfig(int dropParty, long timeDropParty) {
        try {
            File file = new File("/root/configs/config.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(serializeData(dropParty, timeDropParty));
            fileWriter.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private String serializeData(int dropParty, long timeDropParty) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JsonObject json = new JsonObject();
        mechanic.setDropParty(dropParty);
        mechanic.setTimeDropParty(timeDropParty);

        json.setMechanic(mechanic);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }
}