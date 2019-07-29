package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.mechanic.objects.SettingsObject;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class FixBugHealthTask extends Task {

    private int k = -1;
    private int j = 0;

    @Override
    public void onRun(int i) {
        j++;
        if (j % 20 == 0) k++;
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            player.setHealth(player.getHealth());
            SettingsObject settings = API.getMainAPI().settings.get(player.getUniqueId());
            if (settings != null) {
                if (!settings.isPopupValue()) return;
            }
            if (j % 20 == 0) {
                String title = "&m&c&p&e&.&c&h&z&o&n&e&.&e&u";
                String[] split = title.split("&");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < split.length; j++) {
                    sb.append(split[j]);
                    if (j == k) {
                        sb.append("&e");
                    } else {
                        sb.append("&r&f");
                    }
                }
                if (k == split.length) {
                    j = 0;
                    k = -1;
                }
                String p = TextFormat.colorize(sb.toString());
                player.sendPopup(p);
            }
        }
    }
}
