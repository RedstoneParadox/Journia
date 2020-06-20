package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.world.gen.OpenSimplexSampler
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class NewSurfacePatchFeature: Feature<NewSurfacePatchFeatureConfig>(NewSurfacePatchFeatureConfig.CODEC) {
    var sampler: OpenSimplexSampler? = null

    override fun generate(world: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: NewSurfacePatchFeatureConfig): Boolean {
        if (sampler == null) {
            sampler = OpenSimplexSampler(16.0, 1.0, 16.0, 1.0)
            sampler!!.setSeed(world.seed)
        }

        for (x in 0..15) {
            for (z in 0..15) {
                if (sampler!!.eval(x + pos.x, z + pos.z) <= config.coverage) {
                    val basePos = pos.add(x, 0, z)
                    val topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, basePos).down()
                    if (world.testBlockState(topPos) { config.targets.contains(it) }) {
                        world.setBlockState(topPos, config.state, 19)
                    }
                }
            }
        }

        return true
    }
}