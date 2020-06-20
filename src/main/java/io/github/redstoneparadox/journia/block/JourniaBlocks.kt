package io.github.redstoneparadox.journia.block

import io.github.redstoneparadox.journia.util.JourniaSaplingGenerator
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE))

    val GREEN_MUSHROOM_BLOCK = GreenMushroomBlock()

    val CUBEN_LOG = PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG))
    val STRIPPED_CUBEN_LOG = PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG))
    val CUBEN_WOOD = PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD))
    val STRIPPED_CUBEN_WOOD = PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD))
    val CUBEN_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES))
    val CUBEN_SAPLING = JourniaSaplingBlock(JourniaSaplingGenerator.PINE)
    val CUBEN_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val CUBEN_SLAB = SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB))
    val CUBEN_STAIRS = JourniaStairsBlock(CUBEN_PLANKS.defaultState, FabricBlockSettings.copy(Blocks.OAK_STAIRS))
    val CUBEN_FENCE = FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE))
    val CUBEN_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE))
    val CUBEN_BUTTON = JourniaWoodButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON))
    val CUBEN_PRESSURE_PLATE = JourniaPressurePlateBlock.wood(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE))

    val PINE_LOG = PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG))
    val PINE_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES))
    val PINE_SAPLING = JourniaSaplingBlock(JourniaSaplingGenerator.PINE)
    val PINE_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val PINE_SLAB = SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB))
    val PINE_STAIRS = JourniaStairsBlock(PINE_PLANKS.defaultState, FabricBlockSettings.copy(Blocks.OAK_STAIRS))
    val PINE_FENCE = FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE))
    val PINE_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE))
    val PINE_BUTTON = JourniaWoodButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON))
    val PINE_PRESSURE_PLATE = JourniaPressurePlateBlock.wood(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE))

    val MUD = Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).materialColor(MaterialColor.DIRT).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME))

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)

        // register("green_mushroom_block", GREEN_MUSHROOM_BLOCK)

        register("cuben_log", CUBEN_LOG)
        register("stripped_cuben_log", STRIPPED_CUBEN_LOG)
        register("cuben_wood", CUBEN_WOOD)
        register("stripped_cuben_wood", STRIPPED_CUBEN_WOOD)
        register("cuben_leaves", CUBEN_LEAVES)
        register("cuben_sapling", CUBEN_SAPLING)
        register("cuben_planks", CUBEN_PLANKS)
        register("cuben_slab", CUBEN_SLAB)
        register("cuben_stairs", CUBEN_STAIRS)
        register("cuben_fence", CUBEN_FENCE)
        register("cuben_fence_gate", CUBEN_FENCE_GATE)
        register("cuben_button", CUBEN_BUTTON)
        register("cuben_pressure_plate", CUBEN_PRESSURE_PLATE)

        register("pine_log", PINE_LOG)
        register("pine_leaves", PINE_LEAVES)
        register("pine_sapling", PINE_SAPLING)
        register("pine_planks", PINE_PLANKS)
        register("pine_slab", PINE_SLAB)
        register("pine_stairs", PINE_STAIRS)
        register("pine_fence", PINE_FENCE)
        register("pine_fence_gate", PINE_FENCE_GATE)
        register("pine_button", PINE_BUTTON)
        register("pine_pressure_plate", PINE_PRESSURE_PLATE)

        register("mud", MUD)
    }

    private fun register(id: String, block: Block) {
        Registry.register(Registry.BLOCK, "journia:$id", block)
    }
}