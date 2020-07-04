package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class DeadTreeFeatureConfig(private val trunk: BlockState, private val minHeight: Int, private val additionalHeight: Int): FeatureConfig {
    fun getTrunk(): BlockState = trunk
    fun getMinHeight(): Int = minHeight
    fun getAdditionalHeight(): Int = additionalHeight

    companion object {
        val CODEC: Codec<DeadTreeFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockState.CODEC.fieldOf("trunk").forGetter { it.trunk },
                Codec.INT.fieldOf("minHeight").forGetter { it.minHeight },
                Codec.INT.fieldOf("additionalHeight").forGetter { it.additionalHeight }
            ).apply(instance, ::DeadTreeFeatureConfig)
        }
    }
}