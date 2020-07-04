package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.world.gen.OpenSimplexSampler
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class SurfacePatchFeature: Feature<SurfacePatchFeatureConfig>(SurfacePatchFeatureConfig.CODEC) {
    private val samplerMap: MutableMap<SurfacePatchFeatureConfig, Pair<OpenSimplexSampler, OpenSimplexSampler>> = mutableMapOf()

    override fun generate(world: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: SurfacePatchFeatureConfig): Boolean {
        val samplers = samplerMap.computeIfAbsent(config) { OpenSimplexSampler(config.size, 1.0, config.size, 1.0) to OpenSimplexSampler(1.0, 1.0, 1.0, 1.0) }

        for (x in 0..15) {
            for (z in 0..15) {
                val noise = samplers.first.eval(x + pos.x, z + pos.z)
                val chance = samplers.second.eval(x + pos.x, z + pos.z)

                if ((noise + 1)/2 <= config.coverage && (chance + 1)/2 <= config.integrity) {
                    val basePos = pos.add(x, 0, z)
                    val topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, basePos).down()
                    if (world.testBlockState(topPos) { config.targets.contains(it) }) {
                        if (config.below) {
                            for (y in 0..4) {
                                world.setBlockState(topPos.down(y), config.state, 19)
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