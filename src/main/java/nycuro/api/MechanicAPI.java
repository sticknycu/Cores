package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.network.protocol.ScriptCustomEventPacket;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.DyeColor;
import cn.nukkit.utils.LogLevel;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import nycuro.API;
import nycuro.Loader;
import nycuro.crate.item.EntityFirework;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class MechanicAPI {

    public static Int2IntMap counts = new Int2IntOpenHashMap();

    static {
        counts.put(0, 0); // Skyblock
        counts.put(1, 0); // Factions
        counts.put(2, 0); // SkyPvP
        counts.put(3, 0); // ALl
    }

    public boolean isOnSpawn(Player player) {
        return Loader.isOnSpawn.getBoolean(player.getName());
    }

    /*public void spawnFireworks() {
        entities.forEach(Entity::spawnToAll);
    }*/

    public void createBossBar(Player player) {
        API.getMainAPI().bossbar.put(player.getName(), new DummyBossBar.Builder(player)
                .text("bossbar")
                .length(100)
                .color(BlockColor.GREEN_BLOCK_COLOR)
                .build());
        player.createBossBar(API.getMainAPI().bossbar.get(player.getName()));
    }

    public void createScoreboard(Player player) {
        FakeScoreboard fakeScoreboard = new FakeScoreboard();
        Objective object = new Objective("§f§l•§e•§6• HUB §6•§e•§f•", new ObjectiveCriteria("dummy", true));
        DisplayObjective newObject = new DisplayObjective(
                object,
                ObjectiveSortOrder.DESCENDING,
                ObjectiveDisplaySlot.SIDEBAR
        );

        fakeScoreboard.objective = newObject;
        fakeScoreboard.addPlayer(player);
        API.getMainAPI().scoreboard.put(player.getName(), fakeScoreboard);
    }

    /*private void sendInfoServers(Player player) {
        FormWindowCustom infoMenu = new FormWindowCustom("Info Partner");
        ProfileProxy profile = Database.profileProxy.get(player.getName());
        int lang = profile.getLanguage();
        switch (lang) {
            case 0:
                infoMenu.addElement(new ElementLabel("                      Hello!\n" +
                        "         Welcome to Partner Info!\n\n" +
                        "§c» §aHow can i be Partner?:\n" +
                        "§eFor be a Partner, you need just ask. Contact me on Discord.\n" +
                        "§eDiscord: §7NycuRO#6842\n\n" +
                        "§c» §aWhat benefits i have?: \n" +
                        "§eYour Server appereal here.\n" +
                        "§eYou can get a custom dns for your server.\n" +
                        "§eYour DNS need to be like that: §7{name}.nycuro.us\n\n" +
                        "§c» §aThanks: \n" +
                        "§eThanks so much for all persons who tried to be Partners and who wants.\n" +
                        "§eWe waiting with a message on Discord :).\n" +
                        "§eHave a nice day!"));
                break;
            case 1:
                infoMenu.addElement(new ElementLabel("                      Salut!\n" +
                        "      Bine ai venit la Info Partener!\n\n" +
                        "§c» §aCum pot deveni Partener?:\n" +
                        "§ePentru a deveni partener, trebuie doar sa ceri acest lucru. Contacteaza-ma pe Discord.\n" +
                        "§eDiscord: §7NycuRO#6842\n\n" +
                        "§c» §aCe beneficii am?:\n" +
                        "§eServerul tau va aparea aici.\n" +
                        "§eVei primi un DNS Custom pentru Serverul tau.\n" +
                        "§eAcesta va fi de forma: §7{name}.nycuro.us\n\n" +
                        "§c» §aMultumiri:\n" +
                        "§eVreau sa le multumesc tuturor persoanelor care au incercat sa fie Parteneri si care vor.\n" +
                        "§eVa asteptam cu un mesaj pe Discord :).\n" +
                        "§eO zi placuta!"));
                break;
        }
        player.showFormWindow(infoMenu);
    }*/

    public void transferPlayer(Player p, String destination) {
        ScriptCustomEventPacket pk = new ScriptCustomEventPacket();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream a = new DataOutputStream(out);
        try {
            a.writeUTF("Connect");
            a.writeUTF(destination);
            pk.eventName = "bungeecord:main";
            pk.eventData = out.toByteArray();
            p.dataPacket(pk);
        } catch (Exception e) {
            API.getMainAPI().getLogger().log(LogLevel.ALERT,"Error while transferring ( PLAYER: " + p.getName() + " | DEST: " + destination + " )");
            e.printStackTrace();
        }
    }

    public void requestPlayerCount(String server) {
        ScriptCustomEventPacket pk = new ScriptCustomEventPacket();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream a = new DataOutputStream(out);
        try {
            a.writeUTF("PlayerCount");
            a.writeUTF(server);
            pk.eventName = "bungeecord:main";
            pk.eventData = out.toByteArray();
            Player player = API.getMainAPI().getServer().getOnlinePlayers().values().iterator().next();
            player.dataPacket(pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void spawnFirework(Vector3 pos) {
        Level level = API.getMainAPI().getServer().getDefaultLevel();
        ItemFirework item = new ItemFirework();
        CompoundTag tag = new CompoundTag();
        CompoundTag ex = new CompoundTag()
                .putByteArray("FireworkColor", new byte[]{(byte) DyeColor.ORANGE.getDyeData()})
                .putByteArray("FireworkFade", new byte[]{(byte) DyeColor.YELLOW.getDyeData()})
                .putBoolean("FireworkFlicker", true)
                .putBoolean("FireworkTrail", true)
                .putByte("FireworkType", 4);
        tag.putCompound("Fireworks", new CompoundTag("Fireworks")
                .putList(new ListTag<CompoundTag>("Explosions").add(ex))
                .putByte("Flight", 1));
        item.setNamedTag(tag);
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", pos.x + 0.5))
                        .add(new DoubleTag("", pos.y + 0.5))
                        .add(new DoubleTag("", pos.z + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(item));
        EntityFirework entity = new EntityFirework(level.getChunk((int) pos.x >> 4, (int) pos.z >> 4), nbt);
        entity.spawnToAll();
    }
}
