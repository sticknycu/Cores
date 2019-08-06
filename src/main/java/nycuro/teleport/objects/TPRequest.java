package nycuro.teleport.objects;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import lombok.Getter;

public class TPRequest {

    @Getter
    private final long startTime;

    @Getter
    private final Player from;

    @Getter
    private final Player to;

    @Getter
    private final Location location;

    private final boolean isTo;

    public TPRequest(long startTime, Player from, Player to, Location location, boolean isTo) {
        this.startTime = startTime;
        this.from = from;
        this.to = to;
        this.location = location;
        this.isTo = isTo;
    }

    public boolean isTo() {
        return isTo;

    }
}
