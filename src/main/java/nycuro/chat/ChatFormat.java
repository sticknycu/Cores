package nycuro.chat;

/**
 * author: uselesswaifu
 * HubCore Project
 * API 1.0.0
 */
public enum ChatFormat {
    DEFAULT("&l&o&6%rank &r&l&e» &r&7%name &l&8%slash &r&7%msg"),
    HELPERJR("&l&o&aHELPERJR &r&l&a» &r&7%name &l&8%slash &r&7%msg"),
    HELPER("&l&o&2HELPER &r&l&2» &r&7%name &l&8%slash &r&7%msg"),
    YT("&l&o&cYT &r&l&c» &r&7%name &l&8%slash &r&7%msg"),
    ADMIN("&l&o&4OWNER &r&l&c» &r&7%name &l&8%slash &r&7%msg");

    private String group = "";

    ChatFormat(String g) {
        this.group = g;
    }

    public String toString() {
        return this.group;
    }
}
