package com.cebbys.celib.utilities;

import net.minecraft.nbt.CompoundTag;

public interface TagSerializable {

    public CompoundTag toTag(CompoundTag tag);

    public void fromTag(CompoundTag tag);
}