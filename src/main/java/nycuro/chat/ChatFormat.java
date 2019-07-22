package nycuro.chat;

/**
 * author: uselesswaifu
 * SkyblockCore Project
 * API 1.0.0
 */
public enum ChatFormat {
    // default , premium, vip, helper, mod, admin si yt
    DEFAULT("&7[FACTION] &l&o&6PLAYER &r&l&e» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    PREMIUM("&7[FACTION] &l&o&ePREMIUM &r&l&6» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    VIP("&7[FACTION] &l&o&9VIP &r&l&3» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    HELPER("&7[FACTION] &l&o&2HELPER &r&l&a» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    MODERATOR("&7[FACTION] &l&o&3MODERATOR &r&l&b» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    YT("&7[FACTION] &l&o&fYT &r&l&c» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg"),
    ADMIN("&7[FACTION] &l&o&4ADMIN &r&l&c» &r&8[&3%lvl&8]&r&7%name &l&8%slash &r&7%msg");

    private String group = "";

    ChatFormat(String g) {
        this.group = g;
    }

    public String toString() {
        return this.group;
    }
}
