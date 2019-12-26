package io.github.redstoneparadox.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE).build())

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)
    }

    private fun register(id: String, block: Block) {
        Registry.register(Registry.BLOCK, "journia:$id", block)
    }
}