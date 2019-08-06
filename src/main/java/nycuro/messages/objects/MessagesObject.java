package nycuro.messages.objects;

import cn.nukkit.utils.TextFormat;
import lombok.Data;
import nycuro.api.API;

import java.util.HashMap;
import java.util.Map;

@Data
public class MessagesObject {

    public Map<String, String> messages = new HashMap<>();

    public String translateMessage(String message) {
        String msg = messages.get(message);
        return TextFormat.colorize(msg);
    }

    public String translateMessage(String message, String replacer) {
        String msg = translateMessage(message);
        API.log("Before translate: " + msg);
        msg = msg.replace("{%0}", replacer);
        API.log("After translate" + msg);
        return msg;
    }
}
