package nycuro.tasks;


import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import nycuro.Loader;
import nycuro.api.API;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;
import nycuro.jobs.NameJob;

import java.util.UUID;

import static nycuro.api.API.*;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class ScoreboardTask extends Task {

    @Override
    public void onRun(int i) {
        /*for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            if (mainAPI.scoreboard.get(player.getServerId()) != null) {
                mainAPI.scoreboard.get(player.getServerId()).despawnFrom(player);
                addToScoreboard(player);
            }
        }*/
    }

    /*private void addToScoreboard(Player player) {
        FakeScoreboard scoreboard = mainAPI.scoreboard.get(player.getServerId());

        Objective scoreboardDisplay = scoreboard.objective.getObjective();

        ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
        Object2BooleanMap<UUID> coords = mainAPI.coords;

        DisplayObjective dobj = new DisplayObjective(
                scoreboardDisplay,
                ObjectiveSortOrder.ASCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        scoreboardDisplay.setScore(0, "  " + "  ", 0);
        scoreboardDisplay.setScore(1, "  " + "  ", 1);
        try {
            scoreboardDisplay.setScore(2, messageAPI.messagesObject.translateMessage("scoreboard.info"), 2);
            scoreboardDisplay.setScore(3, messageAPI.messagesObject.translateMessage("scoreboard.name", player.getName()), 3);
            scoreboardDisplay.setScore(4, messageAPI.messagesObject.translateMessage("scoreboard.rank", mechanicAPI.getRank(player)), 4);
            scoreboardDisplay.setScore(5, messageAPI.messagesObject.translateMessage("scoreboard.dollars", mainAPI.emptyNoSpace + API.round(profileSkyblock.getDollars(), 2)), 5);
            scoreboardDisplay.setScore(6, messageAPI.messagesObject.translateMessage("scoreboard.gems", mainAPI.emptyNoSpace + profileProxy.getGems()), 6);
            scoreboardDisplay.setScore(7, messageAPI.messagesObject.translateMessage("scoreboard.onlinetime", time(profileSkyblock.getTime())), 7);
            scoreboardDisplay.setScore(8, messageAPI.messagesObject.translateMessage("scoreboard.job", NameJob.getType(profileSkyblock.getJob()).getName()), 8);
            if (coords.getOrDefault(player.getServerId(), false)) {
                scoreboardDisplay.setScore(9, messageAPI.messagesObject.translateMessage("scoreboard.coords", mainAPI.emptyNoSpace + player.getX(), mainAPI.emptyNoSpace + player.getY(), mainAPI.emptyNoSpace + player.getZ()), 9);
            }
            scoreboardDisplay.setScore(10, messageAPI.messagesObject.translateMessage("scoreboard.empty"), 10);
            scoreboardDisplay.setScore(11, messageAPI.messagesObject.translateMessage("scoreboard.server"), 11);
            scoreboardDisplay.setScore(12, messageAPI.messagesObject.translateMessage("scoreboard.online", mainAPI.emptyNoSpace + mainAPI.getServer().getOnlinePlayers().size()), 12);
            scoreboardDisplay.setScore(13, messageAPI.messagesObject.translateMessage("scoreboard.countvoteparty", mainAPI.emptyNoSpace + Loader.dropPartyVotes), 13);
            scoreboardDisplay.setScore(14, messageAPI.messagesObject.translateMessage("scoreboard.voteparty", time(1000 * 60 * 60 * 24 - (System.currentTimeMillis() - Loader.dropPartyTime))), 14);
        } catch (Exception e) {
            // ignore
        }

        scoreboard.objective = dobj;

        //scoreboard.removePlayer(player);
        scoreboard.update();
        scoreboard.addPlayer(player);
    }*/
}