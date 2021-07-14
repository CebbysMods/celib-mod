package lv.cebbys.mcmods.celib.registrators;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static Block registerBlock(Block block, String modId, String blockId) {
        Registry.register(
                Registry.BLOCK,
                new Identifier(modId, blockId),
                block
        );

        return block;
    }

    public static Pair<Block, Item> registerBlockWithItem(Block block, String modId, String blockId, Item.Settings itemSettings) {
        registerBlock(block, modId, blockId);
        return new Pair<>(
                block,
                Registry.register(
                        Registry.ITEM,
                        new Identifier(modId, blockId),
                        new BlockItem(block, itemSettings)
                )
        );
    }

    public static void registerBlockWithItem(Block block, String modId, String blockId, RenderLayer layer, Item.Settings itemSettings) {
        registerBlockWithItem(block, modId, blockId, itemSettings);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }

    public static void registerBlock(Block block, String modId, String blockId, RenderLayer layer) {
        registerBlock(block, modId, blockId);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }
}
