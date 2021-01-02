package com.cebbys.celib.testing

import com.cebbys.celib.testing.blocks.DmNTestBlockRenderer
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry

object TestClientMain {
    @JvmStatic
    fun init() {
        BlockEntityRendererRegistry.INSTANCE.register(TestMain.TestBlockEntity, ::DmNTestBlockRenderer)
    }
}