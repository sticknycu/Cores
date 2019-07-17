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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
}
