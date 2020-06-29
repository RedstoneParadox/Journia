package io.github.redstoneparadox.journia.world.gen.feature

import com.terraformersmc.shapes.impl.filler.SimpleFiller
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class RockFormationFeature: Feature<RockFormationFeatureConfig>(RockFormationFeatureConfig.CODEC) {
    override fun generate(world: ServerWorldAccess, structureAccessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: RockFormationFeatureConfig): Boolean {
        return true
    }
}