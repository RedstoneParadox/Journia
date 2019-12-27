package io.github.redstoneparadox.item

import io.github.redstoneparadox.block.JourniaBlocks
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object JourniaItems {
    val PINE_LOG = blockItem(JourniaBlocks.PINE_LOG)
    val PINE_PLANKS = blockItem(JourniaBlocks.PINE_PLANKS)

    fun registerAll() {
        register("pine_log", PINE_LOG)
        register("pine_planks", PINE_PLANKS)
    }

    private fun register(id: String, item: Item) {
        Registry.register(Registry.ITEM, "journia$id", item)
    }

    private fun blockItem(block: Block): BlockItem {
        return BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    }
}