package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig

object JourniaSurfaceBuilders {
    private val GRASS = Blocks.GRASS_BLOCK.defaultState
    private val RED_SAND = Blocks.RED_SAND.defaultState
    private val COARSE_DIRT = Blocks.COARSE_DIRT.defaultState
    private val CRACKED_GROUND = JourniaBlocks.CRACKED_GROUND.defaultState
    private val STONE = Blocks.STONE.defaultState
    private val ANDESITE = Blocks.ANDESITE.defaultState
    private val GRAVEL = Blocks.GRAVEL.defaultState

    val WASTELAND_CONFIG = TernarySurfaceConfig(CRACKED_GROUND, CRACKED_GROUND, GRAVEL)
    val WASTELAND_EDGE_CONFIG = TernarySurfaceConfig(COARSE_DIRT, COARSE_DIRT, GRAVEL)
    val ROCKY_TAIGA_CONFIG = PentaSurfaceConfig(GRASS, STONE, GRAVEL, secondaryCutoff = 1.75, tertiaryCutoff = 1.95, scale = 0.4)
    val WETLANDS_CONFIG = TernarySurfaceConfig(GRASS, Blocks.DIRT.defaultState, JourniaBlocks.MUD.defaultState)
    val MUD_CONFIG = TernarySurfaceConfig(JourniaBlocks.MUD.defaultState, JourniaBlocks.MUD.defaultState, JourniaBlocks.MUD.defaultState)
    val SALT_FLATS_CONFIG = TernarySurfaceConfig(Blocks.WHITE_CONCRETE_POWDER.defaultState, Blocks.WHITE_CONCRETE_POWDER.defaultState, Blocks.WHITE_CONCRETE_POWDER.defaultState)
    val BIANCO_CONFIG = TernarySurfaceConfig(Blocks.WHITE_TERRACOTTA.defaultState, Blocks.WHITE_TERRACOTTA.defaultState, Blocks.WHITE_TERRACOTTA.defaultState)
    val BANDED_MOUNTAINS_CONFIG = TernarySurfaceConfig(Blocks.GRASS_BLOCK.defaultState, Blocks.DIRT.defaultState, Blocks.GRAVEL.defaultState)

    val PENTA: SurfaceBuilder<PentaSurfaceConfig> = PentaSurfaceBuilder()
    val WETLANDS: SurfaceBuilder<TernarySurfaceConfig> = WetlandsSurfaceBuilder()


    fun init() {
        register("penta", PENTA)
        register("wetlands", WETLANDS)
    }

    fun register(id: String, builder: SurfaceBuilder<*>) {
        Registry.register(Registry.SURFACE_BUILDER, "journia:$id", builder)
    }
}