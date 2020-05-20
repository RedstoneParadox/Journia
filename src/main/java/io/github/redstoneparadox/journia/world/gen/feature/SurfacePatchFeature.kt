package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import io.github.redstoneparadox.journia.world.gen.OpenSimplexSampler
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*
import java.util.function.Function
import kotlin.math.abs

class SurfacePatchFeature(configDeserializer: Function<@ParameterName(name = "dynamic") Dynamic<*>, out SurfacePatchFeatureConfig>): Feature<SurfacePatchFeatureConfig>(configDeserializer) {

    private val sampler = OpenSimplexSampler()

    override fun generate(world: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: SurfacePatchFeatureConfig): Boolean {
        sampler.setSeed(world.seed)

        var blockPos = pos
        while (true) {
            outer@do {
                if (blockPos.y > 3) {
                    if (world.isAir(blockPos.down())) {
                        break@outer
                    }
                    val block = world.getBlockState(blockPos.down()).block
                    if (!isDirt(block) && !isStone(
                            block
                        )
                    ) {
                        break@outer
                    }
                }
                if (blockPos.y <= 3) {
                    return false
                }
                val i = config.startRadius
                var j = 0
                while (i >= 0 && j < 3) {
                    val k = i + random.nextInt(2)
                    val l = i + random.nextInt(2)
                    val m = i + random.nextInt(2)
                    val f = (k + l + m).toFloat() * 0.333f + 0.5f
                    val var12: Iterator<*> = BlockPos.iterate(blockPos.add(-k, -l, -m), blockPos.add(k, l, m)).iterator()
                    while (var12.hasNext()) {
                        val blockPos2 = var12.next() as BlockPos
                        val x = blockPos2.x
                        val z = blockPos2.z
                        if (config.integrity < abs(sampler.eval(x, z))) continue
                        if (blockPos2.getSquaredDistance(blockPos) <= (f * f).toDouble()) {
                            val y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.x, blockPos2.z) - 1
                            val pos = BlockPos(x, y, z)
                            if (config.target.test(world.getBlockState(pos))) {
                                world.setBlockState(pos, config.state, 4)
                            }
                        }
                    }
                    blockPos = blockPos.add(
                        -(i + 1) + random.nextInt(2 + i * 2),
                        0 - random.nextInt(2),
                        -(i + 1) + random.nextInt(2 + i * 2)
                    )
                    ++j
                }
                return true
            } while (true);
            blockPos = blockPos.down()
        }
    }
}