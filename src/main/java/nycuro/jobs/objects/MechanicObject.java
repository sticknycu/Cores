package nycuro.jobs.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

public class MechanicObject {

    @Getter
    @Setter
    public UUID user;

    @Getter
    @Setter
    public Map<UUID, Long> players;

    @Getter
    @Setter
    public Map<UUID, Integer> kills;

    public MechanicObject(UUID user, Map<UUID, Long> players, Map<UUID, Integer> kills) {
        this.user = user;
        this.players = players;
        this.kills = kills;
    }
}
