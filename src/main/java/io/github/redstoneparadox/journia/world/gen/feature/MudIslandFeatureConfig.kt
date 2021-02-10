package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.journia.shape.ConfiguredShapeProvider
import io.github.redstoneparadox.journia.util.Codecs
import io.github.redstoneparadox.journia.util.field
import net.minecraft.world.gen.feature.FeatureConfig

class MudIslandFeatureConfig(
    val shapeProvider: ConfiguredShapeProvider<*, *>,
    val offsetRange: IntRange,
    val countRange: IntRange
): FeatureConfig {
    companion object {
        val CODEC: Codec<MudIslandFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                ConfiguredShapeProvider.CODEC.field("shape_provider") { shapeProvider },
                Codecs.INT_RANGE.field("offset_range") { offsetRange },
                Codecs.INT_RANGE.field("count_range") { countRange }
            ).apply(instance, ::MudIslandFeatureConfig)
        }
    }
}