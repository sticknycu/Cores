package nycuro.teleport.objects;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.player.Player;
import lombok.Getter;

public class TPCooldown {

    private final Player player;

    private final Location location;

    @Getter
    private final long timestamp;

    private final String message;

    public TPCooldown(Player player, Location location, long timestamp, String message) {
        this.player = player;
        this.location = location;
        this.timestamp = timestamp;
        this.message = message;
    }

    public void execute() {
        player.teleport(location);
        player.sendMessage(message);
    }
}
