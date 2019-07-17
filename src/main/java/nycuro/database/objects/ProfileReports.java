package nycuro.database.objects;

import lombok.Data;

@Data
public class ProfileReports {

    public String name;
    public String reason;
    public String contact;
    public String reporter;

    public ProfileReports(String name, String reason, String contact, String reporter) {
        this.name = name;
        this.reason = reason;
        this.contact = contact;
        this.reporter = reporter;
    }
}
