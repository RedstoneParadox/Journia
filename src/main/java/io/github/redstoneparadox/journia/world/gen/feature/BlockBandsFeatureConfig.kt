package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class BlockBandsFeatureConfig(val bands: List<BlockBand>, val minSeparation: Int, val maxSeparation: Int, val waveSize: Int, val waveVariation: Int): FeatureConfig {
    init {
        if (maxSeparation <= minSeparation) {
            throw IllegalStateException("Attempted to make min separation of block bands higher than the max separation.")
        }
    }

    fun getWeightedBands(): MutableList<BlockBand> {
        val weightedBands = mutableListOf<BlockBand>()

        for (band in bands) {
            for (i in 0..band.weight) {
                weightedBands.add(band.copy(weight = 0))
            }
        }

        return weightedBands
    }

    companion object {
        val CODEC: Codec<BlockBandsFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.list(BlockBand.CODEC).fieldOf("bands").forGetter { it.bands },
                Codec.INT.fieldOf("minSeparation").forGetter { it.minSeparation },
                Codec.INT.fieldOf("maxSeparation").forGetter { it.maxSeparation },
                Codec.INT.fieldOf("waveSize").forGetter { it.waveSize },
                Codec.INT.fieldOf("waveVariation").forGetter { it.waveVariation }
            ).apply(instance, ::BlockBandsFeatureConfig)
        }
    }

    data class BlockBand(val state: BlockState, val size: Int, val weight: Int) {
        companion object {
            val CODEC: Codec<BlockBand> = RecordCodecBuilder.create { instance ->
                instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter { it.state },
                    Codec.INT.fieldOf("size").forGetter { it.size },
                    Codec.INT.fieldOf("weight").forGetter { it.weight }
                ).apply(instance, ::BlockBand)
            }
        }
    }
}