package nycuro.api;

import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.particle.GenericParticle;
import cn.nukkit.level.particle.Particle;
import nycuro.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ParticlesAPI {

    public void playWitchTornado(final Location loc) {
        final HashMap<Integer, Integer> S = new HashMap<>();
        API.getMainAPI().getServer().getScheduler().scheduleRepeatingTask(API.getMainAPI(), new Runnable() {
            Location l = loc.clone().add(.5, 0, .5);
            Random r = new Random();
            void startWitchTornado(final Location loc) {
                final int num = r.nextInt(Integer.MAX_VALUE);
                S.put(num, API.getMainAPI().getServer().getScheduler().scheduleRepeatingTask(API.getMainAPI(), new Runnable() {
                    int nextLocation = 0;
                    int diamaterSwitch = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    Location height = loc.clone().add(0, 5, 0);
                    @Override
                    public void run() {
                        ArrayList<Location> locs = getCircle(height, radius, 50);
                        height.getLevel().addParticle(new GenericParticle(locs.get(nextLocation), Particle.TYPE_MOB_SPELL_INSTANTANEOUS, 3));
                        nextLocation++;
                        diamaterSwitch++;
                        lifeSpan++;
                        if(nextLocation == 50) nextLocation = 0;
                        height = height.subtract(0, .02, 0); //Controls how far each particle goes Down.
                        if(diamaterSwitch == 7) { //Controls when diameter Changes.
                            diamaterSwitch = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if(lifeSpan == 207) { //Controls how far the particle effect go down.
                            API.getMainAPI().getServer().getScheduler().cancelTask(S.get(num));
                            S.remove(num);
                        }
                        //height.getLevel().addParticle(new GenericParticle(locs.get(nextLocation), Particle.TYPE_WITCH_SPELL, 1));
                    }
                }, 1).getTaskId());
            }

            @Override
            public void run() {
                startWitchTornado(l);
            }
        }, 30);
    }

    private static ArrayList<Location> getCircle(Location center, double radius, int amount) {
        Level world = center.getLevel();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(x, center.getY(), z, world));
        }
        return locations;
    }

    private static ArrayList<Location> getCircleReverse(Location center, double radius, int amount) {
        Level world = center.getLevel();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() - (radius * Math.cos(angle));
            double z = center.getZ() - (radius * Math.sin(angle));
            locations.add(new Location(x, center.getY(), z, world));
        }
        return locations;
    }
}
