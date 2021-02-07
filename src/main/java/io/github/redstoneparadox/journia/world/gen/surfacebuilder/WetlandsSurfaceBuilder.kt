package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.BlockState
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*

class WetlandsSurfaceBuilder : SurfaceBuilder<TernarySurfaceConfig>(TernarySurfaceConfig.CODEC) {
    override fun generate(random: Random, chunk: Chunk, biome: Biome, x: Int, z: Int, height: Int, noise: Double, defaultBlock: BlockState, defaultFluid: BlockState, seaLevel: Int, seed: Long, surfaceBlocks: TernarySurfaceConfig) {
        if (chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x and  15, z and  15) < seaLevel) {
            SWAMP.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, JourniaSurfaceBuilders.MUD_CONFIG)
        }
        else {
            SWAMP.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, surfaceBlocks)
        }
    }
}