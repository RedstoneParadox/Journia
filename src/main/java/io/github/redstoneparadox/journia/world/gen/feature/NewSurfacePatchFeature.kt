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
    private val samplers: MutableMap<NewSurfacePatchFeatureConfig, OpenSimplexSampler> = mutableMapOf()

    override fun generate(world: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: NewSurfacePatchFeatureConfig): Boolean {
        val sampler = samplers.computeIfAbsent(config) { OpenSimplexSampler(16.0, 1.0, 16.0, 1.0) }

        for (x in 0..15) {
            for (z in 0..15) {
                val noise = sampler!!.eval(x + pos.x, z + pos.z)

                if ((noise + 1)/2 <= config.coverage) {
                    val basePos = pos.add(x, 0, z)
                    val topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, basePos).down()
                    if (world.testBlockState(topPos) { config.targets.contains(it) }) {
                        if (config.below) {
                            for (y in 0..4) {
                                world.setBlockState(topPos.down(0), config.state, 19)
                            }
                        }
                        else {
                            world.setBlockState(topPos, config.state, 19)
                        }
                    }
                }
            }
        }

        return true
    }
}