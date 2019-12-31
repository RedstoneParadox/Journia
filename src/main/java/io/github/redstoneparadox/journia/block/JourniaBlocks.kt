package io.github.redstoneparadox.journia.block

import com.terraformersmc.terraform.block.TerraformSaplingBlock
import io.github.redstoneparadox.journia.util.JourniaSaplingGenerator
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.util.registry.Registry

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE).build())

    val PINE_LOG = LogBlock(MaterialColor.SPRUCE, FabricBlockSettings.copy(Blocks.OAK_LOG).build())
    val PINE_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).build())
    val PINE_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build())
    val PINE_SAPLING = JourniaSaplingBlock(JourniaSaplingGenerator.PINE)

    fun registerAll() {
        register("cracked_ground", CRACKED_GROUND)

        register("pine_log", PINE_LOG)
        register("pine_planks", PINE_PLANKS)
        register("pine_leaves", PINE_LEAVES)
        register("pine_sapling", PINE_SAPLING)
    }

    private fun register(id: String, block: Block) {
        Registry.register(Registry.BLOCK, "journia:$id", block)
    }
}