package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object JourniaSurfaceBuilders {
    private val GRASS = Blocks.GRASS_BLOCK.defaultState
    private val RED_SAND = Blocks.RED_SAND.defaultState
    private val COARSE_DIRT = Blocks.COARSE_DIRT.defaultState
    private val CRACKED_GROUND = JourniaBlocks.CRACKED_GROUND.defaultState
    private val STONE = Blocks.STONE.defaultState
    private val ANDESITE = Blocks.ANDESITE.defaultState
    private val GRAVEL = Blocks.GRAVEL.defaultState

    val WASTELAND_CONFIG = PentaSurfaceConfig(RED_SAND, CRACKED_GROUND, COARSE_DIRT, CRACKED_GROUND, GRAVEL, -0.4, 1.95)
    val ROCKY_TAIGA_CONFIG = PentaSurfaceConfig(GRASS, STONE, STONE, secondaryCutoff = 1.95, tertiaryCutoff = 1.96, scale = 0.4)

    val PENTA: SurfaceBuilder<PentaSurfaceConfig> = PentaSurfaceBuilder(PentaSurfaceConfig.Companion::deserialize)


    fun registerAll() {
        register("penta", PENTA)
    }

    fun register(id: String, builder: SurfaceBuilder<*>) {
        Registry.register(Registry.SURFACE_BUILDER, "journia:$id", builder)
    }
}