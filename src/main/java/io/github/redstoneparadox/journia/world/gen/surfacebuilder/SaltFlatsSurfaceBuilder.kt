package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*
import java.util.function.Function

class SaltFlatsSurfaceBuilder(factory: Function<Dynamic<*>, out TernarySurfaceConfig>): SurfaceBuilder<TernarySurfaceConfig>(factory) {
    override fun generate(random: Random, chunk: Chunk, biome: Biome, x: Int, z: Int, height: Int, noise: Double, defaultBlock: BlockState, defaultFluid: BlockState, seaLevel: Int, seed: Long, config: TernarySurfaceConfig) {
        val top = config.topMaterial
        val under = config.underMaterial


    }
}