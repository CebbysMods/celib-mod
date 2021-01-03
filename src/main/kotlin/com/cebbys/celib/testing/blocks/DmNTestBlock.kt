package com.cebbys.celib.testing.blocks

import com.cebbys.celib.block.CelibBlockWithEntity
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.world.BlockView

class DmNTestBlock : CelibBlockWithEntity(Settings.of(Material.METAL)) {
    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        TODO("Not yet implemented")
    }

}