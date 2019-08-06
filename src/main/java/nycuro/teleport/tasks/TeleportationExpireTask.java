package nycuro.teleport.tasks;

import nycuro.api.API;
import nycuro.teleport.objects.TPRequest;
import nycuro.teleport.api.TeleportationAPI;

import java.util.Iterator;

public class TeleportationExpireTask implements Runnable {

    @Override
    public void run() {
        Iterator<TPRequest> requestIterator = API.getTeleportationAPI().tpRequests.values().iterator();

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
