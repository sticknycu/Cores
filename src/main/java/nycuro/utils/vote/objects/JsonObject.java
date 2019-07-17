package nycuro.utils.vote.objects;

import lombok.Data;

@Data
public class JsonObject {
    public Mechanic mechanic;

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}
