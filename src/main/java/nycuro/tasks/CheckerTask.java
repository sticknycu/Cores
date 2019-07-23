package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.ai.entity.BossEntity;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;
import nycuro.database.objects.ProfileSkyblock;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class CheckerTask extends Task {

    private AtomicBoolean randomBool = new AtomicBoolean(true);

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileSkyblock profileSkyblock = Database.profileSkyblock.get(player.getName());
            double[] d1 = new double[2];
            double[] d2 = new double[2];
            double[] d3 = new double[2];

            Location loc = player.getLocation();

            // PVP and Arena and Spawn only if is in default level, not skyblock
            if (loc.getLevel().equals(API.getMainAPI().getServer().getDefaultLevel())) {
                API.getMainAPI().isOnSpawn.replace(player, true);
                API.getMainAPI().isOnPvP.replace(player, false);
                API.getMainAPI().isOnArena.replace(player, false);
                // Vector3 from = new Vector3(1153, 31, 1187);
                // Vector3 to = new Vector3(1059, 0, 1280);
                /*d1[0] = 89; // x from
                d1[1] = -75; // x to
                d2[0] = 81; // y from
                d2[1] = 256; // y to
                d3[0] = 76; // z from
                d3[1] = -77; // z to
                Arrays.sort(d1);
                Arrays.sort(d2);
                Arrays.sort(d3);
                if (API.getMechanicAPI().isPlayerInsideOfArea(player, d1, d2, d3)) {
                    API.getMainAPI().isOnSpawn.replace(player, true);
                    API.getMainAPI().isOnPvP.replace(player, false);
                    API.getMainAPI().isOnArena.replace(player, false);
                }

                // Arena Check
                //Vector3 vectorRA = new Vector3(1057, 5, 1175);
                //Vector3 vectorLA = new Vector3(1154, 29, 1120);
                d1[0] = 1057; // x from
                d1[1] = 1154; // x to
                d2[0] = 5; // y from
                d2[1] = 29; // y to
                d3[0] = 1175; // z from
                d3[1] = 1120; // z to
                Arrays.sort(d1);
                Arrays.sort(d2);
                Arrays.sort(d3);
                if (API.getMechanicAPI().isPlayerInsideOfArea(player, d1, d2, d3)) {
                    API.getMainAPI().isOnArena.replace(player, true);
                } else {
                    API.getMainAPI().isOnArena.replace(player, false);
                }*/
            } else {
                // Skyblock World
                API.getMainAPI().isOnPvP.replace(player, false);
                API.getMainAPI().isOnArena.replace(player, false);
                API.getMainAPI().isOnSpawn.replace(player, false);
            }

            if (API.getMechanicAPI().isOnArena(player)) {
                if (profileSkyblock.getLevel() < 10) {
                    player.sendMessage(API.getMessageAPI().sendArenaWarningMessage(player));
                    player.teleport(API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation());
                    API.getMessageAPI().sendCommandSpawnMessage(player);
                }
            }

            Instant instant = Instant.now() ;  // Capture current moment in UTC.

            ZoneId zoneId = ZoneId.of("Europe/Bucharest");
            ZonedDateTime timeZone = instant.atZone(zoneId);
            if (timeZone.getHour() == 21 && timeZone.getMinute() == 0 && timeZone.getSecond() == 0) {
                if (API.getMechanicAPI().getBossHealth() == 0) {
                    API.getMessageAPI().sendBossSpawnedMessage(player);
                    new BossEntity();
                }
            }

            // Drop Party
            if ((1000 * 60 * 60 * 24 - (System.currentTimeMillis() - Loader.dropPartyTime)) <= 0) {
                Loader.dropPartyTime = System.currentTimeMillis();
                Loader.dropPartyVotes = 0;
            }
            if (Loader.dropPartyVotes >= 50) {
                API.getMechanicAPI().sendDropPartyMessageBroadcast(player);
                Loader.dropPartyTime = System.currentTimeMillis();
                Loader.dropPartyVotes = 0;
                API.getMainAPI().getServer().getScheduler().scheduleDelayedTask(new Task() {
                    @Override
                    public void onRun(int i) {
                        API.getMechanicAPI().spawnDropParty();
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
                            player.sendPopup("§3+" + resultCoins + " DOLLARS" + "\n" +
                                    "+" + result + " EXP");
                        } else {
                            player.sendPopup("§3+" + resultGem + " GEMS" + "\n" +
                                    "+15min");
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
