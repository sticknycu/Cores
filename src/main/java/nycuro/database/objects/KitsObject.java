package nycuro.database.objects;

import lombok.Getter;
import lombok.Setter;

public class KitsObject {

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public boolean premium1;

    @Getter
    @Setter
    public boolean premium2;

    @Getter
    @Setter
    public boolean premium3;

    @Getter
    @Setter
    public boolean premium4;

    @Getter
    @Setter
    public boolean premium5;

    public KitsObject(String name, boolean premium1, boolean premium2, boolean premium3, boolean premium4, boolean premium5) {
        this.name = name;
        this.premium1 = premium1;
        this.premium2 = premium2;
        this.premium3 = premium3;
        this.premium4 = premium4;
        this.premium5 = premium5;
    }
}
