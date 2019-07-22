package nycuro.crate.item;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.ByteEntityData;
import cn.nukkit.entity.data.IntEntityData;
import cn.nukkit.entity.data.NBTEntityData;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemFirework;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddEntityPacket;
import cn.nukkit.network.protocol.EntityEventPacket;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.network.protocol.PlaySoundPacket;

import java.util.Random;

/**
 * author: NycuRO
 * SkyblockCore Project
 * API 1.0.0
 */
public class EntityFirework extends Entity {

    public static final int NETWORK_ID = 72;
    private int fireworkAge = 0;
    private int lifetime;
    private Item firework;

    public EntityFirework(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        Random rand = new Random();
        this.lifetime = 1;
        this.motionX = rand.nextGaussian() * 0.001D;
        this.motionZ = rand.nextGaussian() * 0.001D;
        this.motionY = 0.05D;
        if (nbt.contains("FireworkItem")) {
            this.firework = NBTIO.getItemHelper(nbt.getCompound("FireworkItem"));
        } else {
            this.firework = new ItemFirework();
        }

        this.setDataProperty(new NBTEntityData(16, this.firework.getNamedTag()));
        this.setDataProperty(new IntEntityData(17, 1));
        this.setDataProperty(new ByteEntityData(18, 1));
    }

    @Override
    public int getNetworkId() {
        return 72;
    }

    @Override
    public boolean onUpdate(int currentTick) {
        if (this.closed) {
            return false;
        } else {
            int tickDiff = currentTick - this.lastUpdate;
            if (tickDiff <= 0 && !this.justCreated) {
                return true;
            } else {
                this.lastUpdate = currentTick;
                this.timing.startTiming();
                boolean hasUpdate = this.entityBaseTick(tickDiff);
                if (this.isAlive()) {
                    this.motionX *= 1.15D;
                    this.motionZ *= 1.15D;
                    this.motionY += 0.04D;
                    this.move(this.motionX, this.motionY, this.motionZ);
                    this.updateMovement();
                    float f = (float) Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                    this.yaw = (double) ((float) (Math.atan2(this.motionX, this.motionZ) * 57.29577951308232D));
                    this.pitch = (double) ((float) (Math.atan2(this.motionY, (double) f) * 57.29577951308232D));
                    if (this.fireworkAge == 0) {
                        PlaySoundPacket pk = new PlaySoundPacket();
                        pk.name = Sound.RANDOM_EXPLODE.toString();
                        //pk.name = new ExplodeSound(new Vector3(0.5, 0.5, 0.5)).toString();
                        pk.volume = 1.0F;
                        pk.pitch = 1.0F;
                        pk.x = this.getFloorX();
                        pk.y = this.getFloorY();
                        pk.z = this.getFloorZ();
                        this.level.addChunkPacket(this.getFloorX() >> 4, this.getFloorZ() >> 4, pk);
                    }

                    ++this.fireworkAge;
                    hasUpdate = true;
                    if (this.fireworkAge >= this.lifetime) {
                        EntityEventPacket pk = new EntityEventPacket();
                        pk.data = 0;
                        pk.event = 25;
                        pk.eid = this.getId();
                        LevelSoundEventPacket pk2 = new LevelSoundEventPacket();
                        pk2.sound = 57;
                        pk2.extraData = -1;
                        //pk.pitch = -1;
                        pk2.x = (float) this.getX();
                        pk2.y = (float) this.getY();
                        pk2.z = (float) this.getZ();
                        Server.broadcastPacket(this.getViewers().values(), pk);
                        this.level.addChunkPacket(this.getFloorX() >> 4, this.getFloorZ() >> 4, pk2);
                        this.kill();
                        hasUpdate = true;
                    }
                }

                this.timing.stopTiming();
                return hasUpdate || !this.onGround || Math.abs(this.motionX) > 1.0E-5D || Math.abs(this.motionY) > 1.0E-5D || Math.abs(this.motionZ) > 1.0E-5D;
            }
        }
    }

    @Override
    public boolean attack(EntityDamageEvent source) {
        return (source.getCause() == DamageCause.VOID || source.getCause() == DamageCause.FIRE_TICK || source.getCause() == DamageCause.ENTITY_EXPLOSION || source.getCause() == DamageCause.BLOCK_EXPLOSION) && super.attack(source);
    }

    public void setFirework(Item item) {
        this.firework = item;
    }

    @Override
    public void spawnTo(Player player) {
        super.spawnTo(player);
        AddEntityPacket pk = new AddEntityPacket();
        pk.type = 72;
        pk.entityUniqueId = this.getId();
        pk.entityRuntimeId = this.getId();
        pk.x = (float) this.x;
        pk.y = (float) this.y;
        pk.z = (float) this.z;
        pk.speedX = (float) this.motionX;
        pk.speedY = (float) this.motionY;
        pk.speedZ = (float) this.motionZ;
        pk.metadata = this.dataProperties;
        player.dataPacket(pk);
    }

    @Override
    public float getWidth() {
        return 0.25F;
    }

    @Override
    public float getHeight() {
        return 0.25F;
    }
}
