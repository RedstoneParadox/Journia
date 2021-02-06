package io.github.redstoneparadox.journia.item

import com.terraformersmc.terraform.boat.TerraformBoatEntity
import com.terraformersmc.terraform.boat.TerraformBoatItem
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.entity.JourniaEntityTypes
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object JourniaItems {
    val CRACKED_GROUND = blockItem(JourniaBlocks.CRACKED_GROUND)

    val GREEN_MUSHROOM_BLOCK = blockItem(JourniaBlocks.GREEN_MUSHROOM_BLOCK)

    val CUBEN_LOG = blockItem(JourniaBlocks.CUBEN_LOG)
    val STRIPPED_CUBEN_LOG = blockItem(JourniaBlocks.STRIPPED_CUBEN_LOG)
    val CUBEN_WOOD = blockItem(JourniaBlocks.CUBEN_WOOD)
    val STRIPPED_CUBEN_WOOD = blockItem(JourniaBlocks.STRIPPED_CUBEN_WOOD)
    val CUBEN_LEAVES = blockItem(JourniaBlocks.CUBEN_LEAVES)
    val CUBEN_SAPLING = blockItem(JourniaBlocks.CUBEN_SAPLING, ItemGroup.DECORATIONS)
    val CUBEN_PLANKS = blockItem(JourniaBlocks.CUBEN_PLANKS)
    val CUBEN_SLAB = blockItem(JourniaBlocks.CUBEN_SLAB)
    val CUBEN_STAIRS = blockItem(JourniaBlocks.CUBEN_STAIRS)
    val CUBEN_FENCE = blockItem(JourniaBlocks.CUBEN_FENCE, ItemGroup.DECORATIONS)
    val CUBEN_FENCE_GATE = blockItem(JourniaBlocks.CUBEN_FENCE_GATE, ItemGroup.DECORATIONS)
    val CUBEN_BUTTON = blockItem(JourniaBlocks.CUBEN_BUTTON, ItemGroup.REDSTONE)
    val CUBEN_PRESSURE_PLATE = blockItem(JourniaBlocks.CUBEN_PRESSURE_PLATE, ItemGroup.REDSTONE)
    val CUBEN_BOAT = boatItem("cuben") { JourniaEntityTypes.CUBEN_BOAT }

    val PINE_LOG = blockItem(JourniaBlocks.PINE_LOG)
    val STRIPPED_PINE_LOG = blockItem(JourniaBlocks.STRIPPED_PINE_LOG)
    val PINE_WOOD = blockItem(JourniaBlocks.PINE_WOOD)
    val STRIPPED_PINE_WOOD = blockItem(JourniaBlocks.STRIPPED_PINE_WOOD)
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

    fun init() {
        register("cracked_ground", CRACKED_GROUND)

        // register("green_mushroom_block", GREEN_MUSHROOM_BLOCK)

        register("cuben_log", CUBEN_LOG)
        register("stripped_cuben_log", STRIPPED_CUBEN_LOG)
        register("cuben_wood", CUBEN_WOOD)
        register("stripped_cuben_wood", STRIPPED_CUBEN_WOOD)
        register("cuben_sapling", CUBEN_SAPLING)
        register("cuben_leaves", CUBEN_LEAVES)
        register("cuben_planks", CUBEN_PLANKS)
        register("cuben_slab", CUBEN_SLAB)
        register("cuben_stairs", CUBEN_STAIRS)
        register("cuben_fence", CUBEN_FENCE)
        register("cuben_fence_gate", CUBEN_FENCE_GATE)
        register("cuben_button", CUBEN_BUTTON)
        register("cuben_pressure_plate", CUBEN_PRESSURE_PLATE)
        register("cuben_boat", CUBEN_BOAT)

        register("mud", MUD)

        register("pine_log", PINE_LOG)
        register("stripped_pine_log", STRIPPED_PINE_LOG)
        register("pine_wood", PINE_WOOD)
        register("stripped_pine_wood", STRIPPED_PINE_WOOD)
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