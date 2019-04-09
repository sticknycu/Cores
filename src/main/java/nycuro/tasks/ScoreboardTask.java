package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import nycuro.API;
import nycuro.Core;
import nycuro.api.JobsAPI;
import nycuro.database.Database;
import nycuro.database.objects.Profile;

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

        Profile profile = Database.profile.get(player.getUniqueId());
        FPlayer fPlayers = FPlayers.i.get(player);
        Faction faction = fPlayers.getFaction();
        Object2ObjectMap<String, Boolean> coords = API.getMainAPI().coords;

        DisplayObjective dobj = new DisplayObjective(
                scoreboardDisplay,
                ObjectiveSortOrder.DESCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        scoreboardDisplay.setScore(12, String.valueOf(""), 12);

        try {
            if (fPlayers.hasFaction()) {
                scoreboardDisplay.setScore(11, "  §3Factions: §8" + faction.getTag(), 11);
                scoreboardDisplay.setScore(10, "  §3Kills: §8" + profile.getKills(), 10);
                scoreboardDisplay.setScore(9, "  §3Deaths: §8" + profile.getDeaths(), 9);
                scoreboardDisplay.setScore(8,"  §3OnlineTime: §8" + Core.time(profile.getTime()), 8);
                if (profile.getJob() != 0) {
                    scoreboardDisplay.setScore(7,"  §3Job: §8" + JobsAPI.jobs.get(profile.getJob()), 7);
                    scoreboardDisplay.setScore(6,"  §3Coins: §8" + Core.round(profile.getCoins(), 2), 6);
                    scoreboardDisplay.setScore(5,"  §3Level: §8" + player.getExperienceLevel(), 5);
                    if ((coords.getOrDefault(player.getName(), null).equals(false)) || (coords.getOrDefault(player.getName(), null) == null)) {
                        scoreboardDisplay.setScore(4,String.valueOf(""), 4);
                        scoreboardDisplay.setScore(3,"§7Discord: §3discord.nycuro.us", 3);
                    } else if (coords.getOrDefault(player.getName(), null).equals(true)) {
                        scoreboardDisplay.setScore(4,"  §3Coords: §8" + (int) player.getX() + ":" + (int) player.getY() + ":" + (int) player.getZ(), 4);
                        scoreboardDisplay.setScore(3,String.valueOf(""), 3);
                        scoreboardDisplay.setScore(2,"§7Discord: §3discord.nycuro.us", 2);
                    }
                } else {
                    scoreboardDisplay.setScore(7,"  §3Coins: §8" + Core.round(profile.getCoins(), 2), 7);
                    scoreboardDisplay.setScore(6,"  §3Level: §8" + player.getExperienceLevel(), 6);
                    if ((coords.getOrDefault(player.getName(), null).equals(false)) || (coords.getOrDefault(player.getName(), null) == null)) {
                        scoreboardDisplay.setScore(5,String.valueOf(""), 5);
                        scoreboardDisplay.setScore(4,"§7Discord: §3discord.nycuro.us", 4);
                    } else if (coords.getOrDefault(player.getName(), null).equals(true)) {
                        scoreboardDisplay.setScore(5,"  §3Coords: §8" + (int) player.getX() + ":" + (int) player.getY() + ":" + (int) player.getZ(), 5);
                        scoreboardDisplay.setScore(4,String.valueOf(""), 4);
                        scoreboardDisplay.setScore(3,"§7Discord: §3discord.nycuro.us", 3);
                    }
                }
            } else {
                scoreboardDisplay.setScore(11,"  §3Kills: §8" + profile.getKills(), 11);
                scoreboardDisplay.setScore(10,"  §3Deaths: §8" + profile.getDeaths(), 10);
                scoreboardDisplay.setScore(9,"  §3OnlineTime: §8" + Core.time(profile.getTime()), 9);
                if (profile.getJob() != 0) {
                    scoreboardDisplay.setScore(8,"  §3Job: §8" + JobsAPI.jobs.get(profile.getJob()), 8);
                    scoreboardDisplay.setScore(7,"  §3Coins: §8" + Core.round(profile.getCoins(), 2), 7);
                    scoreboardDisplay.setScore(6,"  §3Level: §8" + player.getExperienceLevel(), 6);
                    if ((coords.getOrDefault(player.getName(), null).equals(false)) || (coords.getOrDefault(player.getName(), null) == null)) {
                        scoreboardDisplay.setScore(5,String.valueOf(""), 5);
                        scoreboardDisplay.setScore(4,"§7Discord: §3discord.nycuro.us", 4);
                    } else if (coords.getOrDefault(player.getName(), null).equals(true)) {
                        scoreboardDisplay.setScore(5,"  §3Coords: §8" + (int) player.getX() + ":" + (int) player.getY() + ":" + (int) player.getZ(), 5);
                        scoreboardDisplay.setScore(4,String.valueOf(""), 4);
                        scoreboardDisplay.setScore(3,"§7Discord: §3discord.nycuro.us", 3);
                    }
                } else {
                    scoreboardDisplay.setScore(8,"  §3Coins: §8" + Core.round(profile.getCoins(), 2), 8);
                    scoreboardDisplay.setScore(7,"  §3Level: §8" + player.getExperienceLevel(), 7);
                    if ((coords.getOrDefault(player.getName(), null).equals(false)) || (coords.getOrDefault(player.getName(), null) == null)) {
                        scoreboardDisplay.setScore(6,String.valueOf(""), 6);
                        scoreboardDisplay.setScore(5,"§7Discord: §3discord.nycuro.us", 5);
                    } else if (coords.getOrDefault(player.getName(), null).equals(true)) {
                        scoreboardDisplay.setScore(6,"  §3Coords: §8" + (int) player.getX() + ":" + (int) player.getY() + ":" + (int) player.getZ(), 6);
                        scoreboardDisplay.setScore(5,String.valueOf(""), 5);
                        scoreboardDisplay.setScore(4,"§7Discord: §3discord.nycuro.us", 4);
                    }
                }
            }
        } catch (Exception e) {
            // ignore
        }

        scoreboard.objective = dobj;

        scoreboard.update();
        scoreboard.addPlayer(player);
    }
}