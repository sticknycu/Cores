package nycuro.dropparty;


import cn.nukkit.item.ItemFirework;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.DyeColor;

public class DropPartyAPI {

    private ItemFirework initiateBallExplosion() {
        ItemFirework itemFirework = new ItemFirework();
        ItemFirework.FireworkExplosion fireworkExplosion = new ItemFirework.FireworkExplosion();
        fireworkExplosion.setFlicker(false);
        fireworkExplosion.setTrail(true);
        fireworkExplosion.addColor(DyeColor.PURPLE);
        fireworkExplosion.addColor(DyeColor.BLACK);
        fireworkExplosion.addColor(DyeColor.YELLOW);
        fireworkExplosion.addFade(DyeColor.PINK);
        fireworkExplosion.type(ItemFirework.FireworkExplosion.ExplosionType.LARGE_BALL);
        itemFirework.clearExplosions();
        itemFirework.addExplosion(fireworkExplosion);
        return itemFirework;
    }

    private ItemFirework initiateCreeperHeadExplosion() {
        ItemFirework itemFirework = new ItemFirework();
        ItemFirework.FireworkExplosion fireworkExplosion = new ItemFirework.FireworkExplosion();
        fireworkExplosion.type(ItemFirework.FireworkExplosion.ExplosionType.CREEPER_SHAPED);
        fireworkExplosion.setFlicker(false);
        fireworkExplosion.setTrail(true);
        fireworkExplosion.addColor(DyeColor.PURPLE);
        fireworkExplosion.addColor(DyeColor.BLACK);
        fireworkExplosion.addColor(DyeColor.YELLOW);
        fireworkExplosion.addFade(DyeColor.PINK);
        itemFirework.clearExplosions();
        itemFirework.addExplosion(fireworkExplosion);
        return itemFirework;
    }

    public CompoundTag getFirstNBT() {
        return new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", 113))
                        .add(new DoubleTag("", 74))
                        .add(new DoubleTag("", 51)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(initiateBallExplosion()));
    }

    public CompoundTag getSecondNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 100))
                        .add(new DoubleTag("", 74))
                        .add(new DoubleTag("", 64)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(initiateBallExplosion()));
    }

    public CompoundTag getThreeNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 113))
                        .add(new DoubleTag("", 74))
                        .add(new DoubleTag("", 77)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(initiateBallExplosion()));
    }

    public CompoundTag getFourNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 126))
                        .add(new DoubleTag("", 74))
                        .add(new DoubleTag("", 64)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(initiateBallExplosion()));
    }

    public CompoundTag getFiveNBT() {
        return new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", 113))
                        .add(new DoubleTag("", 85))
                        .add(new DoubleTag("", 64)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putCompound("FireworkItem", NBTIO.putItemHelper(initiateCreeperHeadExplosion()));
    }
}
