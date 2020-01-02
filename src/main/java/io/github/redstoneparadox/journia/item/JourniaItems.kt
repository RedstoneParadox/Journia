package io.github.redstoneparadox.journia.item

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object JourniaItems {
    val CRACKED_GROUND = blockItem(JourniaBlocks.CRACKED_GROUND)

    val PINE_LOG = blockItem(JourniaBlocks.PINE_LOG)
    val PINE_PLANKS = blockItem(JourniaBlocks.PINE_PLANKS)
    val PINE_LEAVES = blockItem(JourniaBlocks.PINE_LEAVES)
    val PINE_SAPLING = blockItem(JourniaBlocks.PINE_SAPLING, ItemGroup.DECORATIONS)

    val MUD = blockItem(JourniaBlocks.MUD)

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)

        register("pine_log", PINE_LOG)
        register("pine_planks", PINE_PLANKS)
        register("pine_leaves", PINE_LEAVES)
        register("pine_sapling", PINE_SAPLING)

        register("mud", MUD)
    }

    private fun register(id: String, item: Item) {
        Registry.register(Registry.ITEM, "journia:$id", item)
    }

    private fun blockItem(block: Block, group: ItemGroup = ItemGroup.BUILDING_BLOCKS): BlockItem {
        return BlockItem(block, Item.Settings().group(group))
    }
}