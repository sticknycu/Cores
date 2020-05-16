package nycuro.messages;

/**
 * author: uselesswaifu
 * RoleplayCore Project
 * API 1.0.0
 */
public enum ChatFormat {
    // default , premium, vip, helper, mod, admin si yt
    DEFAULT("&l&o&6PLAYER &r&l&e» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    PREMIUM("&l&o&ePREMIUM &r&l&6» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    VIP("&l&o&9VIP &r&l&3» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    HELPER("&l&o&2HELPER &r&l&a» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    MODERATOR("&l&o&3MODERATOR &r&l&b» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    YT("&l&o&fYT &r&l&c» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    ADMIN("&l&o&4ADMIN &r&l&c» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg");

    private String group = "";

    ChatFormat(String g) {
        this.group = g;
    }

    public String toString() {
        return this.group;
    }
}
