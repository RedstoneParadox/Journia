package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.util.JavaRandom
import io.github.redstoneparadox.journia.world.gen.OpenSimplexSampler
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class BlockBandsFeature: Feature<BlockBandsFeatureConfig>(BlockBandsFeatureConfig.CODEC) {
    private var initialized: Boolean = false
    private val states: MutableList<BlockState> = mutableListOf()
    private var sampler: OpenSimplexSampler? = null

    override fun generate(world: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: BlockBandsFeatureConfig): Boolean {
        if (!initialized) {
            init(JavaRandom(random), config.getWeightedBands(), config.minSeparation, config.maxSeparation)
            sampler = OpenSimplexSampler(config.waveSize.toDouble(), 0.0, config.waveSize.toDouble(), config.waveVariation.toDouble())
            sampler?.setSeed(world.seed)
        }

        if (sampler != null) {
            for (y in 63..255) {
                for (x in 0..15) {
                    for (z in 0..15) {
                        val offset = sampler!!.eval(pos.x + x, pos.z + z).toInt()
                        val index = wrap(63, y - offset, 255)

                        val state = states[index]
                        val position = pos.add(x, y, z)
                        if (state != AIR_STATE && world.testBlockState(position) { it.block == STONE }) {
                            world.setBlockState(position, state, 19)
                        }
                    }
                }
            }

            return true
        }

        return false
    }

    private fun wrap(min: Int, value: Int, max: Int): Int {
        val difference = max - min
        var wrapped = value

        if (wrapped > max) {
            while (wrapped > max) {
                wrapped -= difference
            }
        }
        if (wrapped < min) {
            while (wrapped < min) {
                wrapped += difference
            }
        }

        return wrapped
    }

    private fun init(random: JavaRandom, bands: List<BlockBandsFeatureConfig.BlockBand>, minSeparation: Int, maxSeparation: Int) {
        while (states.size <= 256) {
            val band = bands.random(random)

            for (i in 0..band.size) {
                states.add(band.state)
            }

            val separation = random.nextInt(minSeparation, maxSeparation)

            for (i in 0..separation) {
                states.add(AIR_STATE)
            }
        }

        while (states.size > 256) {
            states.removeAt(256)
        }
        initialized = true
    }

    companion object {
        val AIR_STATE: BlockState = Blocks.AIR.defaultState
        val STONE: Block = Blocks.STONE
    }
}