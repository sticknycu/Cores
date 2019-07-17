package nycuro.utils.vote.objects;

import lombok.Data;

@Data
public class Mechanic {
    public int dropParty;
    public long timeDropParty;

    public int getDropParty() {
        return dropParty;
    }

    public void setDropParty(int dropParty) {
        this.dropParty = dropParty;
    }

    public long getTimeDropParty() {
        return timeDropParty;
    }

    public void setTimeDropParty(long timeDropParty) {
        this.timeDropParty = timeDropParty;
    }
}
