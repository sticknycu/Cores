package nycuro.api;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.DyeColor;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.*;
import nycuro.API;
import nycuro.crate.item.EntityFirework;
import nycuro.database.Database;
import nycuro.database.objects.ProfileProxy;

/**
 * author: NycuRO
 * HubCore Project
 * API 1.0.0
 */
public class MechanicAPI {

    public boolean isOnSpawn(Entity entity) {
        double x = entity.getLevel().getSpawnLocation().getX();
        double y = entity.getLevel().getSpawnLocation().getY();
        double z = entity.getLevel().getSpawnLocation().getZ();
        Vector3 vector3 = new Vector3(x, y, z);
        return entity.getPosition().distance(vector3) <= 300 && entity.getY() <= 29;
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

    private void sendInfoServers(Player player) {
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
