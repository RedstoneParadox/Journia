package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import net.minecraft.block.BlockState
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*

class SaltFlatsSurfaceBuilder: SurfaceBuilder<TernarySurfaceConfig>(TernarySurfaceConfig.CODEC) {
    override fun generate(random: Random, chunk: Chunk, biome: Biome, x: Int, z: Int, height: Int, noise: Double, defaultBlock: BlockState, defaultFluid: BlockState, seaLevel: Int, seed: Long, config: TernarySurfaceConfig) {
        val top = config.topMaterial
        val under = config.underMaterial


    }
}