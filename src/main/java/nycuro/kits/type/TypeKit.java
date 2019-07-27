package nycuro.kits.type;

public enum TypeKit {
    CLASSIC("Classic"),
    PREMIUM("Premium"),
    SPECIFIC("Specific");

    public String name;

    TypeKit(String name) {
        this.name = name;
    }
}
