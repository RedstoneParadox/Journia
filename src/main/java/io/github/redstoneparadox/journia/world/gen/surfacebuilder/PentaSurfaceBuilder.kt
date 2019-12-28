package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*
import kotlin.reflect.KFunction1

class PentaSurfaceBuilder(function: KFunction1<@ParameterName(name = "dynamic") Dynamic<*>, PentaSurfaceConfig>) : SurfaceBuilder<PentaSurfaceConfig>(function) {

    @Override
    override fun generate(random: Random, chunk: Chunk, biome: Biome, i: Int, j: Int, k: Int, d: Double, blockState: BlockState, blockState2: BlockState, l: Int, m: Long, pentaSurfaceConfig: PentaSurfaceConfig) {
        val d2 = d * pentaSurfaceConfig.getScale()

        val primaryConfig = TernarySurfaceConfig(pentaSurfaceConfig.getTopMaterial(), pentaSurfaceConfig.getUnderMaterial(), pentaSurfaceConfig.getUnderwaterMaterial())
        val secondaryConfig = TernarySurfaceConfig(pentaSurfaceConfig.getSecondaryMaterial(), pentaSurfaceConfig.getUnderMaterial(), pentaSurfaceConfig.getUnderwaterMaterial())
        val tertiaryConfig = TernarySurfaceConfig(pentaSurfaceConfig.getTertiaryMaterial(), pentaSurfaceConfig.getUnderMaterial(), pentaSurfaceConfig.getUnderwaterMaterial())

        when {
            d2 > pentaSurfaceConfig.getTertiaryCutoff() -> DEFAULT.generate(random, chunk, biome, i, j, k, d2, blockState, blockState2, l, m, tertiaryConfig)
            d2 > pentaSurfaceConfig.getSecondaryCutoff() -> DEFAULT.generate(random, chunk, biome, i, j, k, d2, blockState, blockState2, l, m, secondaryConfig)
            else -> DEFAULT.generate(random, chunk, biome, i, j, k, d2, blockState, blockState2, l, m, primaryConfig)
        }
    }
}