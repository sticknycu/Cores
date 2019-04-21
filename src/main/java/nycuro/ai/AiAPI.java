package nycuro.ai;

import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class AiAPI {

    public CompoundTag getBossNBT() {
        return new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", 127))
                        .add(new DoubleTag("", 71))
                        .add(new DoubleTag("", 50)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putString("NameTag", "§a» §cThe Boss §a«§r");
    }

    public CompoundTag getPigNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 1050))
                        .add(new DoubleTag("", 70))
                        .add(new DoubleTag("", 1283)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));
    }

    public CompoundTag getSheepNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 1036))
                        .add(new DoubleTag("", 70))
                        .add(new DoubleTag("", 1265)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));
    }

    public CompoundTag getCowNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 1054))
                        .add(new DoubleTag("", 70))
                        .add(new DoubleTag("", 1233)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));
    }

    public CompoundTag getChickenNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 1050))
                        .add(new DoubleTag("", 70))
                        .add(new DoubleTag("", 1199)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));
    }

}