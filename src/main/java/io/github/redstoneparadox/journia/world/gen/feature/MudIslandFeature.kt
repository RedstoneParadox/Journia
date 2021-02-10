package io.github.redstoneparadox.journia.world.gen.feature

import net.minecraft.util.math.BlockPos
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class MudIslandFeature: Feature<MudIslandFeatureConfig>(MudIslandFeatureConfig.CODEC) {
    override fun generate(world: StructureWorldAccess, chunkGenerator: ChunkGenerator, random: Random, pos: BlockPos, config: MudIslandFeatureConfig): Boolean {
        TODO("Not yet implemented")
    }
}