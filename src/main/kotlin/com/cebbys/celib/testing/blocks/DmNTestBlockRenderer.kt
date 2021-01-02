package com.cebbys.celib.testing.blocks

import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.FontStorage
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import java.util.function.Function
import kotlin.math.sin
import kotlin.random.Random


class DmNTestBlockRenderer(dispatcher: BlockEntityRenderDispatcher?) : BlockEntityRenderer<DmNTestBlockEntity>(
    dispatcher
) {
    override fun render(
        entity: DmNTestBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        matrices.push()

        // Calculate the current offset in the y value
//        val offset = sin((entity.world!!.time + tickDelta) / 8.0) / 4.0
        // Move the item
//        matrices.translate(0.5, 1.25 + offset, 0.5)

        // Rotate the item
//        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((entity.world!!.time + tickDelta) * 4))
//
//        val lightAbove = WorldRenderer.getLightmapCoordinates(entity.world, entity.pos.up())
//        MinecraftClient.getInstance().itemRenderer.renderItem(
//            ItemStack(Items.BLAZE_ROD),
//            ModelTransformation.Mode.HEAD,
//            lightAbove,
//            OverlayTexture.DEFAULT_UV,
//            matrices,
//            vertexConsumers
//        )

        // Mandatory call after GL calls
        matrices.pop()

    }
}