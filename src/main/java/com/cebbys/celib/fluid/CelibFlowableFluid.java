package com.cebbys.celib.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static net.minecraft.item.Items.BUCKET;

public abstract class CelibFlowableFluid extends FlowableFluid {
    @Override
    public Item getBucketItem() {
        return new BucketItem(this, new Item.Settings().recipeRemainder(BUCKET).maxCount(1).group(ItemGroup.MISC));
    }
}
