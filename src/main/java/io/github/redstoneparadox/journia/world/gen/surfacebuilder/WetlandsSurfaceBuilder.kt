package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*

class WetlandsSurfaceBuilder : SurfaceBuilder<TernarySurfaceConfig>(TernarySurfaceConfig.CODEC) {
    override fun generate(random: Random, chunk: Chunk, biome: Biome, x: Int, z: Int, height: Int, noise: Double, defaultBlock: BlockState, defaultFluid: BlockState, seaLevel: Int, seed: Long, surfaceBlocks: TernarySurfaceConfig) {
        if (height < seaLevel) {
            SWAMP.generate(random, chunk, biome, x, z, height, noise, JourniaBlocks.MUD.defaultState, defaultFluid, seaLevel, seed, JourniaSurfaceBuilders.MUD_CONFIG)
        }
        else {
            SWAMP.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, surfaceBlocks)
        }
    }
}