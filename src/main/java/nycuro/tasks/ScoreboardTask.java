package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import nycuro.API;
import nycuro.Loader;
import nycuro.api.JobsAPI;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileHub;

/**
 * author: NycuRO
 * FactionsCore Project
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

        ProfileFactions profileFactions = Database.profileFactions.get(player.getUniqueId());
        ProfileHub profileHub = Database.profileHub.get(player.getUniqueId());
        Object2BooleanMap<String> coords = API.getMainAPI().coords;

        DisplayObjective dobj = new DisplayObjective(
                scoreboardDisplay,
                ObjectiveSortOrder.ASCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        scoreboardDisplay.setScore(0, "  " + "  ", 0);
        scoreboardDisplay.setScore(1, "  " + "  ", 1);
        try {
            scoreboardDisplay.setScore(2, API.getMessageAPI().getInfoScoreboard(player), 2);
            scoreboardDisplay.setScore(3, API.getMessageAPI().getNameScoreboard(player), 3);
            scoreboardDisplay.setScore(4, API.getMessageAPI().getRankScoreboard(player), 4);
            scoreboardDisplay.setScore(5, "§7| §fCoins: §6" +  Loader.round(profileFactions.getDollars(), 2), 5);
            scoreboardDisplay.setScore(6, "§7| §fGems: §6" + profileHub.getGems() + "  ", 6);
            scoreboardDisplay.setScore(7, "§7| §fOnline Time: §6" + Loader.time(profileFactions.getTime()) + "  ", 7);
            scoreboardDisplay.setScore(8, "§7| §fJob: §6" + JobsAPI.jobs.get(profileFactions.getJob()) + "   ", 8);
            if (coords.getOrDefault(player.getName(), false)) {
                scoreboardDisplay.setScore(9, "§7| §fX: §6" + (int) player.getX() + " §fY: §6" + (int) player.getY() + " §fZ: §6" + (int) player.getZ() + "   ", 9);
            }
            scoreboardDisplay.setScore(10, "§7 " + "" + "    ", 10);
            scoreboardDisplay.setScore(11, "§7--- §e§lServer: " + "  ", 11);
            scoreboardDisplay.setScore(12, API.getMessageAPI().getOnlineScoreboard(player), 12);
            scoreboardDisplay.setScore(13, "§7| §fDropParty: §6" + " ", 13);
        } catch (Exception e) {
            // ignore
        }

        scoreboard.objective = dobj;

        //scoreboard.removePlayer(player);
        scoreboard.update();
        scoreboard.addPlayer(player);
    }
}