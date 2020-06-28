package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class NewSurfacePatchFeatureConfig(val state: BlockState, val coverage: Double, val targets: List<BlockState>, val below: Boolean = false): FeatureConfig {
    companion object {
        val CODEC: Codec<NewSurfacePatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockState.CODEC.fieldOf("state").forGetter { it.state },
                Codec.DOUBLE.fieldOf("coverage").forGetter { it.coverage },
                Codec.list(BlockState.CODEC).fieldOf("targets").forGetter { it.targets },
                Codec.BOOL.fieldOf("below").forGetter { it.below }
            ).apply(instance, ::NewSurfacePatchFeatureConfig)
        }
    }
}