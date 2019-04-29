package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Role;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class ScoreTagTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            FPlayer fPlayer = FPlayers.i.get(player);
            ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
            ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
            String level = "";
            String rank = "";
            String hp = String.valueOf(player.getHealth());
            String maxhp = String.valueOf(player.getMaxHealth());
            String tagFaction = "";
            String nametag = "";
            if (fPlayer.hasFaction()) {
                tagFaction = "&8[&7%rank%faction&8]";
                nametag = "%tagFaction &7%name";
            } else {
                nametag = "&7%name";
            }
            for (FPlayer p : fPlayer.getFaction().getFPlayersWhereRole(Role.ADMIN)) {
                if (p.getName().equals(player.getName())) rank = "**";
            }
            for (FPlayer pm : fPlayer.getFaction().getFPlayersWhereRole(Role.MODERATOR)) {
                if (pm.getName().equals(player.getName())) rank = "*";
            }
            if (profileProxy != null) {
                try {
                    level = String.valueOf(profileFactions.getLevel());
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
            nametag = nametag.replace("%tagFaction", tagFaction);
            nametag = nametag.replace("%rank", rank);
            nametag = nametag.replace("%faction", fPlayer.getFaction().getTag());
            nametag = nametag.replace("%level", level);
            scoretag = scoretag.replace("%hp", hp);
            scoretag = scoretag.replace("maxhp", maxhp);
            scoretag = scoretag.replace("%type", finals);
            nametag = TextFormat.colorize(nametag);
            scoretag = TextFormat.colorize(scoretag);
            player.setNameTagVisible();
            player.setScoreTag(scoretag);
            player.setNameTag(nametag);
        }
    }
}
