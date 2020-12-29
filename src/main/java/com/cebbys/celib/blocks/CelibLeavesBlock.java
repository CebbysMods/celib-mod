package com.cebbys.celib.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class CelibLeavesBlock extends LeavesBlock {
    public CelibLeavesBlock() {
        super(FabricBlockSettings.of(Material.LEAVES).hardness(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS));
    }
}
