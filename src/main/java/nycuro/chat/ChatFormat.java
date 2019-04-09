package nycuro.chat;

/**
 * author: uselesswaifu
 * FactionsCore Project
 * API 1.0.0
 */
public enum ChatFormat {
    DEFAULT("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &3%name &b» &7%msg"),
    VIP("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&6VIP&7] &3%name &b» &7%msg"),
    VIPPLUS("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&eVIP&c+&7] &3%name &b» &7%msg"),
    MVP("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&bMVP&7] &3%name &b» &7%msg"),
    MVPPLUS("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&dMVP&a+&7] &3%name &b» &7%msg"),
    HELPERJR("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&aHelperJr&7] &3%name &b» &7%msg"),
    HELPER("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&2Helper&7] &3%name &b» &7%msg"),
    YT("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&cYT&7] &3%name &b» &7%msg"),
    ADMIN("&7[&e%job&7] &a[&c%lvl&a] &7[FACTION] &7[&4Admin&7] &3%name &b» &7%msg");

    private String group = "";

    ChatFormat(String g) {
        this.group = g;
    }

    public String toString() {
        return this.group;
    }
}
