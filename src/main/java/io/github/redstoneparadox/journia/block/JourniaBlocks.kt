package io.github.redstoneparadox.journia.block

import com.terraformersmc.terraform.block.TerraformSaplingBlock
import io.github.redstoneparadox.journia.util.JourniaSaplingGenerator
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.tag.FabricItemTags
import net.fabricmc.fabric.api.tools.FabricToolTags
import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE).breakByTool(FabricToolTags.PICKAXES).build())

    val PINE_LOG = LogBlock(MaterialColor.SPRUCE, FabricBlockSettings.copy(Blocks.OAK_LOG).build())
    val PINE_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build())
    val PINE_SAPLING = JourniaSaplingBlock(JourniaSaplingGenerator.PINE)
    val PINE_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).build())
    val PINE_SLAB = SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).build())
    val PINE_STAIRS = JourniaStairsBlock(PINE_PLANKS.defaultState, FabricBlockSettings.copy(Blocks.OAK_STAIRS).build())
    val PINE_FENCE = FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).build())
    val PINE_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).build())
    val PINE_BUTTON = JourniaWoodButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON).build())
    val PINE_PRESSURE_PLATE = JourniaPressurePlateBlock.wood(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE).build())

    val MUD = Block(FabricBlockSettings.of(Material.CLAY).materialColor(MaterialColor.DIRT).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME).build())

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)

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