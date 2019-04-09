package nycuro.generator;

import cn.nukkit.block.*;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.generator.Normal;
import cn.nukkit.level.generator.object.ore.OreType;
import cn.nukkit.level.generator.populator.impl.PopulatorOre;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * author: NycuRO
 * FactionsCore Project
 * API 1.0.0
 */
public class CustomGenerator extends Normal {

    private final List<Populator> populators = new ArrayList<>();

    @Override
    public void init(ChunkManager level, NukkitRandom random) {
        super.init(level, random);
        int i = this.populators.lastIndexOf(new PopulatorOre());
        this.populators.remove(i);
        PopulatorOre ores = new PopulatorOre();
        ores.setOreTypes(new OreType[]{
                new OreType(new BlockOreCoal(), 7, 6, 0, 128),
                new OreType(new BlockOreIron(), 7, 3, 0, 64),
                new OreType(new BlockOreRedstone(), 3, 3, 0, 16),
                new OreType(new BlockOreLapis(), 1, 2, 0, 16),
                new OreType(new BlockOreGold(), 1, 3, 0, 32),
                new OreType(new BlockOreDiamond(), 2, 3, 0, 16),
                new OreType(new BlockDirt(), 3, 11, 0, 128),
                new OreType(new BlockGravel(), 3, 11, 0, 128),
                new OreType(new BlockStone(BlockStone.GRANITE), 3, 11, 0, 80),
                new OreType(new BlockStone(BlockStone.DIORITE), 3, 11, 0, 80),
                new OreType(new BlockStone(BlockStone.ANDESITE), 3, 11, 0, 80)
        });
        this.populators.add(ores);
    }
}
