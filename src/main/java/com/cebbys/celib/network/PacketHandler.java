package com.cebbys.celib.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;

public class PacketHandler {
    @Environment(EnvType.CLIENT)
    public static void sendToServer(IPacket packet) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);

        ClientPlayNetworkHandler netHandler = MinecraftClient.getInstance().getNetworkHandler();
        if (netHandler != null)
            netHandler.getConnection().send(new CustomPayloadC2SPacket(packet.getID(), buf));
    }

    public static void sendToClient(IPacket packet, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);
        player.networkHandler.sendPacket(new CustomPayloadC2SPacket(packet.getID(), buf));
    }
}
