package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ScoreTagTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            String level = "";
            String hp = String.valueOf(player.getHealth());
            String maxhp = String.valueOf(player.getMaxHealth());
            String nametag = "&7%name &7[&c%device&7]";
            if (profileProxy != null) {
                try {
                    level = String.valueOf(profileSkyblock.getLevel());
                } catch (Exception e) {
                    //
                }
            }
            String type = "&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:&7,:";
            String[] split = type.split(",");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < split.length; j++) {
                sb.append(split[j]);
                if (j <= player.getHealth()) {
                    sb.append("&c");
                }
            }
            String finals = sb.toString();
            String scoretag = "&8[&c%type&8]";
            nametag = nametag.replace("%name", player.getName());
            nametag = nametag.replace("%device", API.getMechanicAPI().getOS(player));
            scoretag = scoretag.replace("%hp", hp);
            scoretag = scoretag.replace("maxhp", maxhp);
            scoretag = scoretag.replace("%type", finals);
            nametag = TextFormat.colorize(nametag);
            scoretag = TextFormat.colorize(scoretag);
            player.setScoreTag(scoretag);
            player.setNameTag(nametag);
            player.setNameTagVisible(true);
        }
    }
}
