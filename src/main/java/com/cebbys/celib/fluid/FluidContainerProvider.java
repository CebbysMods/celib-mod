package com.cebbys.celib.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Functions like the vanilla (no damn chocolla) InventoryProvider class.
 * It is recommended to use this or another component system for accessing fluid containers.
 */
public interface FluidContainerProvider {
    FluidContainer getContainer(BlockState state, World world, BlockPos pos);
}
