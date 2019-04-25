package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nycuro.API;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileHub;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class ScoreboardTask extends Task {

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            if (API.getMainAPI().scoreboard.get(player.getName()) != null) {
                API.getMainAPI().scoreboard.get(player.getName()).despawnFrom(player);
                addToScoreboard(player);
            }
        }
    }

    private void addToScoreboard(Player player) {
        FakeScoreboard scoreboard = API.getMainAPI().scoreboard.get(player.getName());

        Objective scoreboardDisplay = scoreboard.objective.getObjective();

        ProfileHub profile = Database.profileHub.get(player.getUniqueId());

        DisplayObjective dobj = new DisplayObjective(
                scoreboardDisplay,
                ObjectiveSortOrder.ASCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        scoreboardDisplay.setScore(0, "  " + "  ", 0);
        scoreboardDisplay.setScore(1, "  " + "  ", 1);
        try {
            scoreboardDisplay.setScore(2, API.getMessageAPI().getInfoScoreboard(player), 2);
            scoreboardDisplay.setScore(3, "§7| §fGems: §6" + profile.getGems() + "  ", 3);
            scoreboardDisplay.setScore(4, "§7| §fOnline Time: §6" + Loader.time(profile.getTime()) + "  ", 4);
            scoreboardDisplay.setScore(5, "§7| §fPing: §6" + player.getPing() + "   ", 5);
            scoreboardDisplay.setScore(6, "§7 " + String.valueOf("") + "    ", 6);
            scoreboardDisplay.setScore(7, "§7--- §e§lServer: " + "  ", 7);
            scoreboardDisplay.setScore(8, "§7| §fOnline Global: §6" + Loader.getCountOnline(0) + "    ", 8);
            scoreboardDisplay.setScore(9, "§7| §fFactions: §6" + Loader.getCountOnline(2) + "  ", 9);
            scoreboardDisplay.setScore(10, "§7| §fSkyBlock: §6" + Loader.getCountOnline(3) + "  ", 10);
            scoreboardDisplay.setScore(11, "§7| §fSkyPvP: §6" + Loader.getCountOnline(4) + "   ", 11);
        } catch (Exception e) {
            // ignore
        }

        scoreboard.objective = dobj;

        scoreboard.update();
        scoreboard.addPlayer(player);
    }
}