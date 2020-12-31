package com.cebbys.celib.mixin;

import com.cebbys.celib.Celib;
import com.cebbys.celib.fluid.DropletValues;
import com.cebbys.celib.fluid.FluidInstance;
import com.cebbys.celib.fluid.ItemWithFluid;
import com.cebbys.celib.loggers.CelibLogger;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BucketItem.class)
public class BucketItemMixin implements ItemWithFluid {
    @Override
    public FluidInstance getFluid(ItemStack stack) {
        if (stack.getItem() instanceof BucketItem) {
            try {
                return new FluidInstance((Fluid) Celib.BucketItem_fluid_field.get(stack.getItem()), DropletValues.BUCKET);
            } catch (Throwable t) {
                CelibLogger.error("Runtime reflection error", t.getMessage());
            }
        }

        return FluidInstance.EMPTY;
    }
}
