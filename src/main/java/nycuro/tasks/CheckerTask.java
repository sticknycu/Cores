package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.ai.entity.BossEntity;
import nycuro.api.UtilsAPI;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CheckerTask extends Task {

    private AtomicBoolean randomBool = new AtomicBoolean(true);

    @Override
    public void onRun(int i) {
        for (Player player : API.getMainAPI().getServer().getOnlinePlayers().values()) {
            ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
            int x = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getX();
            int y = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getY();
            int z = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getZ();
            Vector3 vector3 = new Vector3(x, y, z);
            if (player.getLevel().equals(API.getMainAPI().getServer().getDefaultLevel()) && player.getPosition().distance(vector3) <= 300 && !player.isOp()) {
                if (API.getMechanicAPI().isOnArena(player)) {
                    Loader.isOnSpawn.replace(player.getName(), false);
                } else {
                    Loader.isOnSpawn.replace(player.getName(), true);
                }
            } else {
                Loader.isOnSpawn.replace(player.getName(), false);
            }

            Vector3 vec = new Vector3(0, 0, 0);
            Location loc = player.getLocation();
            if (loc.distance(vec) <= 7500) { /// NOT REALLY 7500, just ~5300 idk why
                Loader.isOnBorder.replace(player.getName(), true);
            } else {
                Loader.isOnBorder.replace(player.getName(), false);
            }

            // Border Check
            if (!Loader.isOnBorder.getBoolean(player.getName())) {
                API.getMessageAPI().sendBorderMessage(player);
                Vector3 directionVector = player.getLocation().getDirectionVector().normalize();
                player.setMotion(player.getMotion().add(directionVector.multiply(-0.2)));
            }

            // RandomTP
            if (loc.getLevelBlock().getId() == BlockID.NETHER_PORTAL) {
                if (UtilsAPI.teleported) return;
                API.getUtilsAPI().handleRandomTeleport(player);
            }

            // Arena Check
            Vector3 vectorRA = new Vector3(1057, 5, 1175);
            Vector3 vectorLA = new Vector3(1154, 29,1120);
            if (API.getMechanicAPI().isPlayerInsideOfArea(player, vectorRA, vectorLA)) {
                API.getMainAPI().isOnArena.replace(player, true);
            } else {
                API.getMainAPI().isOnArena.replace(player, false);
            }

            if (API.getMechanicAPI().isOnArena(player)) {
                if (profileFactions.getLevel() < 10) {
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
                        if (randomBool.get()) profileFactions.setExperience(profileFactions.getExperience() + result);
                        int lowGem = 1;
                        int maxGem = 3;
                        int resultGem = r.nextInt(maxGem - lowGem) + lowGem;
                        ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
                        if (!randomBool.get()) profileProxy.setGems(profileProxy.getGems() + resultGem);
                        int lowCoins = 100;
                        int maxCoins = 500;
                        int resultCoins = r.nextInt(maxCoins - lowCoins) + lowCoins;
                        if (randomBool.get()) profileFactions.setDollars(profileFactions.getDollars() + resultCoins);
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
