package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import java.util.*
import java.util.function.Function

class SurfacePatchFeature(configDeserializer: Function<Dynamic<*>, out FeatureConfig>) : Feature<FeatureConfig>(configDeserializer) {

    override fun generate(world: IWorld, generator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, pos: BlockPos, config: FeatureConfig): Boolean {


        return true
    }
}