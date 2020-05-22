package io.github.redstoneparadox.journia.item

import com.terraformersmc.terraform.entity.TerraformBoatEntity
import com.terraformersmc.terraform.item.TerraformBoatItem
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.entity.JourniaEntityTypes
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.BoatItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import java.util.function.Supplier

object JourniaItems {
    val CRACKED_GROUND = blockItem(JourniaBlocks.CRACKED_GROUND)

    val GREEN_MUSHROOM_BLOCK = blockItem(JourniaBlocks.GREEN_MUSHROOM_BLOCK)

    val PINE_LOG = blockItem(JourniaBlocks.PINE_LOG)
    val PINE_LEAVES = blockItem(JourniaBlocks.PINE_LEAVES)
    val PINE_SAPLING = blockItem(JourniaBlocks.PINE_SAPLING, ItemGroup.DECORATIONS)
    val PINE_PLANKS = blockItem(JourniaBlocks.PINE_PLANKS)
    val PINE_SLAB = blockItem(JourniaBlocks.PINE_SLAB)
    val PINE_STAIRS = blockItem(JourniaBlocks.PINE_STAIRS)
    val PINE_FENCE = blockItem(JourniaBlocks.PINE_FENCE, ItemGroup.DECORATIONS)
    val PINE_FENCE_GATE = blockItem(JourniaBlocks.PINE_FENCE_GATE, ItemGroup.DECORATIONS)
    val PINE_BUTTON = blockItem(JourniaBlocks.PINE_BUTTON, ItemGroup.REDSTONE)
    val PINE_PRESSURE_PLATE = blockItem(JourniaBlocks.PINE_PRESSURE_PLATE, ItemGroup.REDSTONE)
    val PINE_BOAT = boatItem("pine") { JourniaEntityTypes.PINE_BOAT }

    val MUD = blockItem(JourniaBlocks.MUD)

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)

        register("green_mushroom_block", GREEN_MUSHROOM_BLOCK)

        register("mud", MUD)

        register("pine_log", PINE_LOG)
        register("pine_sapling", PINE_SAPLING)
        register("pine_leaves", PINE_LEAVES)
        register("pine_planks", PINE_PLANKS)
        register("pine_slab", PINE_SLAB)
        register("pine_stairs", PINE_STAIRS)
        register("pine_fence", PINE_FENCE)
        register("pine_fence_gate", PINE_FENCE_GATE)
        register("pine_button", PINE_BUTTON)
        register("pine_pressure_plate", PINE_PRESSURE_PLATE)
        register("pine_boat", PINE_BOAT)
    }

    private fun register(id: String, item: Item) {
        Registry.register(Registry.ITEM, "journia:$id", item)
    }

    private fun blockItem(block: Block, group: ItemGroup = ItemGroup.BUILDING_BLOCKS): BlockItem {
        return BlockItem(block, Item.Settings().group(group))
    }

    private fun boatItem(name: String, supplier: () -> EntityType<TerraformBoatEntity>): TerraformBoatItem {
        return TerraformBoatItem(supplier, Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1))
    }
}