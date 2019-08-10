package nycuro.messages.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nycuro.api.API;
import nycuro.messages.objects.MessagesObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import static nycuro.api.API.mainAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class MessageAPI {

    public MessagesObject messagesObject = new MessagesObject();

    public void init() {
        try {
            File file = new File(mainAPI.getDataFolder() + "/language/lang.json");
            API.log("Checking for existance of Language file... ");
            if (file.exists()) {
                API.log("I've found an file [EN]... Let's get all information..");
                //
                byte[] jsonData = Files.readAllBytes(file.toPath());

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode rootNode = objectMapper.readTree(jsonData);

                API.log("Setting messages...[EN]");
                JsonNode generic = rootNode.path("messages_en");
                Iterator<JsonNode> elementsGeneric = generic.elements();
                Iterator<String> fieldNamesGeneric = generic.fieldNames();
                while (fieldNamesGeneric.hasNext()) {
                    JsonNode value = elementsGeneric.next();
                    messagesObject.messages.put(fieldNamesGeneric.next(), value.textValue());
                }

                API.log("Messages added!");
            } else {
                API.log("I cannot find language file...");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
