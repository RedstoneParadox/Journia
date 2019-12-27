package io.github.redstoneparadox.journia.client

import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.item.JourniaItems
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors

@Suppress("unused")
fun init() {
    ColorProviderRegistry.BLOCK.register(BlockColorProvider { state, view, pos, tintIndex ->
        if (view != null && pos != null) BiomeColors.getFoliageColor(view, pos) else FoliageColors.getSpruceColor()
    }, JourniaBlocks.PINE_LEAVES)
    ColorProviderRegistry.ITEM.register(ItemColorProvider { _, _ -> FoliageColors.getSpruceColor() }, JourniaItems.PINE_LEAVES)
}