package io.github.redstoneparadox.journia.block

import io.github.redstoneparadox.journia.util.JourniaSaplingGenerator
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE))

    val GREEN_MUSHROOM_BLOCK = GreenMushroomBlock()

    val GROENWOOD_LOG = PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG))
    val GROENWOOD_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES))
    val GROENWOOD_SAPLING = JourniaSaplingBlock(JourniaSaplingGenerator.PINE)
    val GROENWOOD_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val GROENWOOD_SLAB = SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB))
    val GROENWOOD_STAIRS = JourniaStairsBlock(GROENWOOD_PLANKS.defaultState, FabricBlockSettings.copy(Blocks.OAK_STAIRS))
    val GROENWOOD_FENCE = FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE))
    val GROENWOOD_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE))
    val GROENWOOD_BUTTON = JourniaWoodButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON))
    val GROENWOOD_PRESSURE_PLATE = JourniaPressurePlateBlock.wood(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE))

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

        register("green_mushroom_block", GREEN_MUSHROOM_BLOCK)

        register("groenwood_log", GROENWOOD_LOG)
        register("groenwood_leaves", GROENWOOD_LEAVES)
        register("groenwood_sapling", GROENWOOD_SAPLING)
        register("groenwood_planks", GROENWOOD_PLANKS)
        register("groenwood_slab", GROENWOOD_SLAB)
        register("groenwood_stairs", GROENWOOD_STAIRS)
        register("groenwood_fence", GROENWOOD_FENCE)
        register("groenwood_fence_gate", GROENWOOD_FENCE_GATE)
        register("groenwood_button", GROENWOOD_BUTTON)
        register("groenwood_pressure_plate", GROENWOOD_PRESSURE_PLATE)

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