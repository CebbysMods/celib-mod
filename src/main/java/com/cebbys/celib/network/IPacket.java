package com.cebbys.celib.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public interface IPacket {
    void read(PacketByteBuf buf);
    void write(PacketByteBuf buf);
    Identifier getID();
}
