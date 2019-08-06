package nycuro.teleport.tasks;

import nycuro.teleport.api.TeleportationAPI;
import nycuro.teleport.objects.TPRequest;

import java.util.Iterator;

import static nycuro.api.API.teleportationAPI;

public class TeleportationExpireTask implements Runnable {

    @Override
    public void run() {
        Iterator<TPRequest> requestIterator = teleportationAPI.tpRequests.values().iterator();

        long currentTime = System.currentTimeMillis();

        while (requestIterator.hasNext()) {
            TPRequest request = requestIterator.next();

            long expirationTime = request.getStartTime() + TeleportationAPI.TP_EXPIRATION;
            if (currentTime >= expirationTime) {
                requestIterator.remove();
            }
        }
    }
}
