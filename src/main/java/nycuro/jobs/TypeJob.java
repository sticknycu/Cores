package nycuro.jobs;

import lombok.Getter;
import lombok.Setter;

public enum TypeJob {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    EXTREME("EXTREME!");

    @Getter
    @Setter
    public String name;

    TypeJob(String name) {
        this.name = name;
    }
}
