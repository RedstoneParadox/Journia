package io.github.redstoneparadox.journia.client

import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.entity.JourniaEntityTypes
import io.github.redstoneparadox.journia.item.JourniaItems
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.BoatEntityRenderer

@Suppress("unused")
fun init() {
    ColorProviderRegistry.BLOCK.register(BlockColorProvider { state, view, pos, tintIndex ->
        if (view != null && pos != null) BiomeColors.getFoliageColor(view, pos) else FoliageColors.getSpruceColor()
    }, JourniaBlocks.PINE_LEAVES)
    ColorProviderRegistry.ITEM.register(ItemColorProvider { _, _ -> FoliageColors.getSpruceColor() }, JourniaItems.PINE_LEAVES)

    ColorProviderRegistry.BLOCK.register(BlockColorProvider { state, view, pos, tintIndex ->
        if (view != null && pos != null) BiomeColors.getFoliageColor(view, pos) else FoliageColors.getDefaultColor()
    }, JourniaBlocks.CUBEN_LEAVES)
    ColorProviderRegistry.ITEM.register(ItemColorProvider { _, _ -> FoliageColors.getDefaultColor() }, JourniaItems.PINE_LEAVES)


    BlockRenderLayerMap.INSTANCE.putBlock(JourniaBlocks.PINE_SAPLING, RenderLayer.getCutout())

    EntityRendererRegistry.INSTANCE.register(
        JourniaEntityTypes.PINE_BOAT
    ) { dispatcher, context -> BoatEntityRenderer(dispatcher) }
    EntityRendererRegistry.INSTANCE.register(
        JourniaEntityTypes.CUBEN_BOAT
    ) { dispatcher, context -> BoatEntityRenderer(dispatcher) }
}