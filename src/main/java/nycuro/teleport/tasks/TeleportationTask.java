package nycuro.teleport.tasks;

import cn.nukkit.scheduler.Task;
import nycuro.teleport.objects.TPCooldown;
import nycuro.teleport.api.TeleportationAPI;

import java.util.Iterator;

public class TeleportationTask extends Task {
    private final TeleportationAPI api;

    public TeleportationTask(TeleportationAPI api) {
        this.api = api;
    }

    @Override
    public void onRun(int i) {
        Iterator<TPCooldown> iter = api.getTpCooldowns().iterator();
        long time = System.currentTimeMillis();

        while (iter.hasNext()) {
            TPCooldown cooldown = iter.next();
            if (cooldown.getTimestamp() <= time) {
                cooldown.execute();
                iter.remove();
            }
        }
    }
}
