package io.github.redstoneparadox.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.function.Function

class PentaSurfaceConfig(
    private val topMaterial: BlockState,
    private val secondaryMaterial: BlockState,
    private val tertiaryMaterial: BlockState,
    private val underMaterial: BlockState,
    private val underwaterMaterial: BlockState
): SurfaceConfig {

    override fun getTopMaterial(): BlockState = topMaterial

    fun getSecondaryMaterial(): BlockState = secondaryMaterial

    fun getTertiaryMaterial(): BlockState = tertiaryMaterial

    override fun getUnderMaterial(): BlockState = underMaterial

    fun getUnderwaterMaterial(): BlockState = underwaterMaterial

    companion object {
        fun deserialize(dynamic: Dynamic<*>): PentaSurfaceConfig {
            val topMaterial = dynamic["top_material"].map { return@map BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val secondaryMaterial = dynamic["secondary_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val tertiaryMaterial = dynamic["tertiary_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val underMaterial = dynamic["under_material"].map { BlockState.deserialize(it) }.orElse(Blocks.STONE.defaultState)
            val underwaterMaterial = dynamic["underwater_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRAVEL.defaultState)

            return PentaSurfaceConfig(topMaterial, secondaryMaterial, tertiaryMaterial, underMaterial, underwaterMaterial)
        }
    }
}