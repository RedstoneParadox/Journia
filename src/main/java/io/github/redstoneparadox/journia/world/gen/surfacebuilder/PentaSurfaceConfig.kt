package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig

class PentaSurfaceConfig(
    private val topMaterial: BlockState,
    private val secondaryMaterial: BlockState,
    private val tertiaryMaterial: BlockState,
    private val underMaterial: BlockState = Blocks.STONE.defaultState,
    private val underwaterMaterial: BlockState = Blocks.GRAVEL.defaultState,
    private val secondaryCutoff: Double = -0.95,
    private val tertiaryCutoff: Double = 1.75,
    private val scale: Double = 1.0
): SurfaceConfig {

    override fun getTopMaterial(): BlockState = topMaterial

    fun getSecondaryMaterial(): BlockState = secondaryMaterial

    fun getTertiaryMaterial(): BlockState = tertiaryMaterial

    override fun getUnderMaterial(): BlockState = underMaterial

    fun getUnderwaterMaterial(): BlockState = underwaterMaterial

    fun getSecondaryCutoff(): Double = secondaryCutoff

    fun getTertiaryCutoff(): Double = tertiaryCutoff

    fun getScale(): Double = scale

    companion object {
        fun deserialize(dynamic: Dynamic<*>): PentaSurfaceConfig {
            val topMaterial = dynamic["top_material"].map { return@map BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val secondaryMaterial = dynamic["secondary_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val tertiaryMaterial = dynamic["tertiary_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val underMaterial = dynamic["under_material"].map { BlockState.deserialize(it) }.orElse(Blocks.STONE.defaultState)
            val underwaterMaterial = dynamic["underwater_material"].map { BlockState.deserialize(it) }.orElse(Blocks.GRAVEL.defaultState)
            val secondaryCutoff = dynamic["secondary_cutoff"].map {
                val value = it.value
                if (value is Double) value else -0.95
            }.orElse(-0.95)
            val tertiaryCutoff = dynamic["tertiary_cutoff"].map {
                val value = it.value
                if (value is Double) value else 1.75
            }.orElse(1.75)
            val scale = dynamic["scale"].map {
                val value = it.value
                if (value is Double) value else 1.0
            }.orElse(1.0)

            return PentaSurfaceConfig(topMaterial, secondaryMaterial, tertiaryMaterial, underMaterial, underwaterMaterial, secondaryCutoff, tertiaryCutoff)
        }
    }
}