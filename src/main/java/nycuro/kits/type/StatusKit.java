package nycuro.kits.type;

public enum StatusKit {
    LOCKED("LOCKED"),
    UNLOCKED("UNLOCKED");

    public String status;

    StatusKit(String status) {
        this.status = status;
    }
}
