package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.journia.util.field
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class SurfacePatchFeatureConfig(
    val state: BlockState,
    val coverage: Double = 0.5,
    val targets: List<BlockState> = listOf(),
    val below: Boolean = false,
    val size: Double = 16.0,
    val integrity: Double = 1.0
    ): FeatureConfig {
    companion object {
        val CODEC: Codec<SurfacePatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockState.CODEC.field("state") { state },
                Codec.DOUBLE.field("coverage") { coverage },
                Codec.list(BlockState.CODEC).field("targets") { targets },
                Codec.BOOL.field("below") { below },
                Codec.DOUBLE.field("size") { size },
                Codec.DOUBLE.field("integrity") { integrity }
            ).apply(instance, ::SurfacePatchFeatureConfig)
        }
    }
}