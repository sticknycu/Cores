package nycuro.tasks;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import nycuro.API;
import nycuro.Loader;
import nycuro.api.UtilsAPI;
import nycuro.database.Database;
import nycuro.database.objects.ProfileFactions;
import nycuro.database.objects.ProfileProxy;

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
            int x = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getX();
            int y = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getY();
            int z = (int) API.getMainAPI().getServer().getDefaultLevel().getSpawnLocation().getZ();
            Vector3 vector3 = new Vector3(x, y, z);
            if (player.getLevel().getName().equalsIgnoreCase("world") && player.getPosition().distance(vector3) <= 300 && !player.isOp()) {
                Loader.isOnSpawn.put(player.getName(), true);
            } else {
                Loader.isOnSpawn.put(player.getName(), false);
            }

            Vector3 vec = new Vector3(0, 0, 0);
            Location loc = player.getLocation();
            if (loc.distance(vec) <= 7500) { /// NOT REALLY 7500, just ~5300 idk why
                Loader.isOnBorder.put(player.getName(), true);
            } else {
                Loader.isOnBorder.put(player.getName(), false);
            }

            // Border Check
            if (!Loader.isOnBorder.getBoolean(player.getName())) {
                API.getMessageAPI().sendBorderMessage(player);
                Vector3 directionVector = player.getLocation().getDirectionVector().normalize();
                player.setMotion(player.getMotion().add(directionVector.multiply(-0.2)));
            }

            // RandomTP
            Location location = player.getLocation();
            if (location.getLevelBlock().getId() == BlockID.NETHER_PORTAL) {
                if (UtilsAPI.teleported) return;
                API.getUtilsAPI().handleRandomTeleport(player);
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
                        ProfileFactions profileFactions = Database.profileFactions.get(player.getName());
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
