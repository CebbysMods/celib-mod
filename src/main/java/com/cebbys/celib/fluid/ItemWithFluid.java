package com.cebbys.celib.fluid;

import net.minecraft.item.ItemStack;

/**
 * An item, like a bucket or bottle, that has a fluid in it.
 */
public interface ItemWithFluid {
    FluidInstance getFluid(ItemStack stack);
}
