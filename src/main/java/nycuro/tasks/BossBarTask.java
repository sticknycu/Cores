package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import nycuro.API;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

/**
 * author: GiantQuartz
 * HubCore Project
 * API 1.0.0
 */
public class BossBarTask extends Task {

    private Object2IntMap<String> timers = new Object2IntOpenHashMap<>();

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            Profile profile = Database.profile.get(player.getUniqueId());

            if (API.getMainAPI().bossbar.get(player.getName()) != null) {
                String username = player.getName();
                Integer playerTime = timers.getOrDefault(username, 1);
                int lang = 0;
                if (profile != null) {
                    lang = profile.getLanguage();
                    profile.addTime(5000);
                }
                switch (playerTime) {
                    case 1:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n   §7Facebook on @ §efacebook.nycuro.us");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("      §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n  §7Facebook la @ §efacebook.nycuro.us");
                                break;
                        }
                        break;
                    case 2:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n         §7Vote on @ §evote.nycuro.us");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("    §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n    §7Voteaza la @ §evote.nycuro.us");
                                break;
                        }
                        break;
                    case 3:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n §7Messenger on @ §emessenger.nycuro.us");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("        §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n §7Messenger la @ §emessenger.nycuro.us");
                                break;
                        }
                        break;
                    case 4:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n     §7Discord on @ §ediscord.nycuro.us");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("     §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n  §7Discord la @ §ediscord.nycuro.us");
                                break;
                        }
                        break;
                    case 5:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n     §7Store on @ §enycuro.buycraft.net");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("      §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n  §7Magazin la @ §enycuro.buycraft.net");
                                break;
                        }
                        break;
                    case 6:
                        switch (lang) {
                            case 0:
                                API.getMainAPI().bossbar.get(player.getName()).setText("§7-§8=§7- §7You're playing on §6§lFactions §7-§8=§7- §r\n\n       §7Have Fun @ §eNycuRO Factions");
                                break;
                            case 1:
                                API.getMainAPI().bossbar.get(player.getName()).setText("         §7-§8=§7- §7Joci pe §6§lFactions §7-§8=§7- §r\n\n   §7Distractie placuta @ §eNycuRO Factions");
                                break;
                        }
                        break;
                    default:
                        playerTime = 1;
                }
                timers.put(username, playerTime + 1);
            }
        }
    }
}
