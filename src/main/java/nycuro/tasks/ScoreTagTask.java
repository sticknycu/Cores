package nycuro.tasks;


import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ScoreTagTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            String nametag = messageAPI.messagesObject.translateMessage("score.nametag", player.getName(), mechanicAPI.getOS(player));
            String scoretag = messageAPI.messagesObject.translateMessage("score.scoretag", buildHealthTag(player));
            player.setScoreTag(scoretag);
            player.setNameTag(nametag);
            player.setNameTagVisible(true);
        }
    }

    private String buildHealthTag(Player player) {
        String scoreTag = "&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:&:";
        String[] split = scoreTag.split("&");
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < split.length; j++) {
            sb.append(split[j]);
            if (j <= (int) player.getHealth()) {
                sb.append("&c");
            }
        }
        return TextFormat.colorize(sb.toString());
    }
}
