package com.cebbys.celib.testing

import com.cebbys.celib.registrators.BlockRegistry
import com.cebbys.celib.testing.blocks.DmNTestBlock
import com.cebbys.celib.testing.blocks.DmNTestBlockEntity
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object TestMain {
    var TestBlock: DmNTestBlock? = null
    var TestBlockEntity: BlockEntityType<DmNTestBlockEntity>? = null

    @JvmStatic
    fun init() {
        // Testing init
        val testGroup = FabricItemGroupBuilder.build(
            Identifier("celib", "testing")
        ) { ItemStack(Items.LAVA_BUCKET) }

        TestBlock = BlockRegistry.registerBlockWithItem(DmNTestBlock(), "celib", "tb", Item.Settings().group(testGroup)).left as DmNTestBlock
        TestBlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier("celib", "tb"), BlockEntityType.Builder.create(::DmNTestBlockEntity, TestBlock).build(null))
    }
}