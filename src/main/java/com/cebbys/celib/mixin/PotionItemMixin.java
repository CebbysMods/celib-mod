package com.cebbys.celib.mixin;

import com.cebbys.celib.fluid.DropletValues;
import com.cebbys.celib.fluid.FluidInstance;
import com.cebbys.celib.fluid.ItemWithFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public class PotionItemMixin implements ItemWithFluid {
    @Override
    public FluidInstance getFluid(ItemStack stack) {
        if (stack.getItem() instanceof PotionItem) {
            return new FluidInstance(Fluids.WATER, DropletValues.BOTTLE);
        }

        return FluidInstance.EMPTY;
    }
}
