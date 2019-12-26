package io.github.redstoneparadox.world.gen.surfacebuilder

import io.github.redstoneparadox.block.JourniaBlocks
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object JourniaSurfaceBuilders {
    private val GRASS = Blocks.GRASS_BLOCK.defaultState
    private val RED_SAND = Blocks.RED_SAND.defaultState
    private val COARSE_DIRT = Blocks.COARSE_DIRT.defaultState
    private val CRACKED_GROUND = JourniaBlocks.CRACKED_GROUND.defaultState
    private val STONE = Blocks.STONE.defaultState
    private val GRAVEL = Blocks.GRAVEL.defaultState

    val WASTELAND_CONFIG = PentaSurfaceConfig(RED_SAND, CRACKED_GROUND, GRASS, STONE, GRAVEL, -0.95, 1.9)

    val PENTA: SurfaceBuilder<PentaSurfaceConfig> = PentaSurfaceBuilder(PentaSurfaceConfig.Companion::deserialize)


    fun registerAll() {
        register("penta", PENTA)
    }

    fun register(id: String, builder: SurfaceBuilder<*>) {
        Registry.register(Registry.SURFACE_BUILDER, "journia:$id", builder)
    }
}