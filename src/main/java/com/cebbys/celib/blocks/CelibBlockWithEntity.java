package com.cebbys.celib.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;

public abstract class CelibBlockWithEntity extends BlockWithEntity {
    public CelibBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
