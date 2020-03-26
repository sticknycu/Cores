package nycuro.tasks;


import cn.nukkit.level.Location;
import cn.nukkit.player.Player;
import cn.nukkit.scheduler.Task;
import nycuro.Loader;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static nycuro.api.API.mainAPI;
import static nycuro.api.API.messageAPI;
import static nycuro.api.API.mechanicAPI;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class CheckerTask extends Task {

    private AtomicBoolean randomBool = new AtomicBoolean(true);

    @Override
    public void onRun(int i) {
        for (Player player : mainAPI.getServer().getOnlinePlayers().values()) {
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            double[] d1 = new double[2];
            double[] d2 = new double[2];
            double[] d3 = new double[2];

            Location loc = player.getLocation();

            // PVP and Arena and Spawn only if is in default level, not skyblock
            if (loc.getLevel().equals(mainAPI.getServer().getDefaultLevel())) {
                // Vector3 from = new Vector3(1153, 31, 1187);
                // Vector3 to = new Vector3(1059, 0, 1280);
                // Arena Check
                d1[0] = 91; // x from
                d1[1] = 202; // x to
                d2[0] = 211; // y from
                d2[1] = 130; // y to
                d3[0] = 92; // z from
                d3[1] = -49; // z to
                Arrays.sort(d1);
                Arrays.sort(d2);
                Arrays.sort(d3);
                if (mechanicAPI.isPlayerInsideOfArea(player, d1, d2, d3)) {
                    mainAPI.isOnArena.replace(player.getServerId(), true);
                } else {
                    mainAPI.isOnArena.replace(player.getServerId(), false);
                }

                // Spawn Check
                //Vector3 vectorRA = new Vector3(1057, 5, 1175);
                //Vector3 vectorLA = new Vector3(1154, 29, 1120);
                d1[0] = -96; // x from
                d1[1] = 91; // x to
                d2[0] = 107; // y from
                d2[1] = 211; // y to
                d3[0] = -74; // z from
                d3[1] = 92; // z to
                Arrays.sort(d1);
                Arrays.sort(d2);
                Arrays.sort(d3);
                if (mechanicAPI.isPlayerInsideOfArea(player, d1, d2, d3)) {
                    mainAPI.isOnSpawn.replace(player.getServerId(), true);
                } else {
                    mainAPI.isOnSpawn.replace(player.getServerId(), false);
                }

                // Area Check
                //Vector3 vectorRA = new Vector3(1057, 5, 1175);
                //Vector3 vectorLA = new Vector3(1154, 29, 1120);
                d1[0] = 52; // x from
                d1[1] = -59; // x to
                d2[0] = 139; // y from
                d2[1] = 196; // y to
                d3[0] = -101; // z from
                d3[1] = -213; // z to
                Arrays.sort(d1);
                Arrays.sort(d2);
                Arrays.sort(d3);
                if (mechanicAPI.isPlayerInsideOfArea(player, d1, d2, d3)) {
                    mainAPI.isOnArea.replace(player.getServerId(), true);
                } else {
                    mainAPI.isOnArea.replace(player.getServerId(), false);
                }
            } else {
                // Skyblock World
                mainAPI.isOnArena.replace(player.getServerId(), false);
                mainAPI.isOnSpawn.replace(player.getServerId(), false);
                mainAPI.isOnArea.replace(player.getServerId(), false);
            }

            if (mechanicAPI.isOnArena(player)) {
                if (profileSkyblock.getLevel() < 10) {
                    player.sendMessage(messageAPI.messagesObject.translateMessage("area.boss.warning"));
                    player.teleport(mainAPI.getServer().getDefaultLevel().getSpawnLocation());
                    player.sendMessage(messageAPI.messagesObject.translateMessage("teleportation.spawn.teleported"));
                }
            }

            Instant instant = Instant.now() ;  // Capture current moment in UTC.

            ZoneId zoneId = ZoneId.of("Europe/Bucharest");
            ZonedDateTime timeZone = instant.atZone(zoneId);
            if (timeZone.getHour() == 21 && timeZone.getMinute() == 0 && timeZone.getSecond() == 0) {
                if (mechanicAPI.getBossHealth() == 0) {
                    player.sendMessage(messageAPI.messagesObject.translateMessage("generic.boss.spawn"));
                    //new BossEntity();
                }
            }

            // Drop Party
            if ((1000 * 60 * 60 * 24 - (System.currentTimeMillis() - Loader.dropPartyTime)) <= 0) {
                Loader.dropPartyTime = System.currentTimeMillis();
                Loader.dropPartyVotes = 0;
            }
            if (Loader.dropPartyVotes >= 50) {
                player.sendMessage(messageAPI.messagesObject.translateMessage("generic.dropparty.spawn"));
                Loader.dropPartyTime = System.currentTimeMillis();
                Loader.dropPartyVotes = 0;
                mainAPI.getServer().getScheduler().scheduleDelayedTask(new Task() {
                    @Override
                    public void onRun(int i) {
                        mechanicAPI.spawnDropParty();
                        Random r = new Random();
                        int low = 200;
                        int high = 250;
                        int result = r.nextInt(high-low) + low;
                        if (randomBool.get()) profileSkyblock.setExperience(profileSkyblock.getExperience() + result);
                        int lowGem = 1;
                        int maxGem = 3;
                        int resultGem = r.nextInt(maxGem - lowGem) + lowGem;
                        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
                        if (!randomBool.get()) profileProxy.setGems(profileProxy.getGems() + resultGem);
                        int lowCoins = 100;
                        int maxCoins = 500;
                        int resultCoins = r.nextInt(maxCoins - lowCoins) + lowCoins;
                        if (randomBool.get()) profileSkyblock.setDollars(profileSkyblock.getDollars() + resultCoins);
                        if (!randomBool.get()) profileProxy.setTime(profileProxy.getTime() + 1000 * 60 * 15);
                        if (randomBool.get()) {
                            player.sendPopup(messageAPI.messagesObject.translateMessage("generic.popup.message.one",
                                    mainAPI.emptyNoSpace + resultCoins, mainAPI.emptyNoSpace + result));
                        } else {
                            player.sendPopup(messageAPI.messagesObject.translateMessage("generic.popup.message.two",
                                    mainAPI.emptyNoSpace + resultGem));
                        }
                        if (randomBool.get()) {
                            randomBool.set(false);
                        } else if (!randomBool.get()) {
                            randomBool.set(true);
                        }
                    }
                }, 20 * 60, true);
            }
        }
    }
}
