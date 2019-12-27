package io.github.redstoneparadox.journia.item

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object JourniaItems {
    val PINE_LOG = blockItem(JourniaBlocks.PINE_LOG)
    val PINE_PLANKS = blockItem(JourniaBlocks.PINE_PLANKS)
    val PINE_LEAVES = blockItem(JourniaBlocks.PINE_LEAVES)

    fun registerAll() {
        register("pine_log", PINE_LOG)
        register("pine_planks", PINE_PLANKS)
        register("pine_leaves", PINE_LEAVES)
    }

    private fun register(id: String, item: Item) {
        Registry.register(Registry.ITEM, "journia:$id", item)
    }

    private fun blockItem(block: Block, group: ItemGroup = ItemGroup.BUILDING_BLOCKS): BlockItem {
        return BlockItem(block, Item.Settings().group(group))
    }
}