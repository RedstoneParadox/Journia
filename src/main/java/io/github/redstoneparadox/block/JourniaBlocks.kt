package io.github.redstoneparadox.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.LogBlock
import net.minecraft.block.MaterialColor
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE).build())

    val PINE_LOG = LogBlock(MaterialColor.SPRUCE, FabricBlockSettings.copy(Blocks.OAK_LOG).build())
    val PINE_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).build())

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)
        register("pine_log", PINE_LOG)
        register("pine_planks", PINE_PLANKS)
    }

    private fun register(id: String, block: Block) {
        Registry.register(Registry.BLOCK, "journia:$id", block)
    }
}