package nycuro.messages.objects;

import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class MessagesObject {

    public Map<String, String> messages = new HashMap<>();

    public String translateMessage(String message) {
        String msg = messages.get(message);
        return TextFormat.colorize(msg);
    }

    public String translateMessage(String message, String replacer) {
        String msg = translateMessage(message);
        msg = msg.replace("{%0}", replacer);
        return msg;
    }

    public String translateMessage(String message, String firstReplacer, String secondReplacer) {
        String msg = translateMessage(message);
        msg = msg.replace("{%0}", firstReplacer);
        msg = msg.replace("{%1}", secondReplacer);
        return msg;
    }

    public String translateMessage(String message, String firstReplacer, String secondReplacer, String thirdReplacer) {
        String msg = translateMessage(message);
        msg = msg.replace("{%0}", firstReplacer);
        msg = msg.replace("{%1}", secondReplacer);
        msg = msg.replace("{%2}", thirdReplacer);
        return msg;
    }
}
