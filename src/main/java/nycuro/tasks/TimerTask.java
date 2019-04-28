package nycuro.tasks;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

public class TimerTask implements Runnable {

    @Override
    public void run() {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            try {
                ProfileProxy profileProxy = Database.profileProxy.get(player.getName());
                if (profileProxy != null) {
                    profileProxy.setTime(1000 + profileProxy.getTime());
                }
            } catch (Exception e) {
                //
            }
        }
    }
}
