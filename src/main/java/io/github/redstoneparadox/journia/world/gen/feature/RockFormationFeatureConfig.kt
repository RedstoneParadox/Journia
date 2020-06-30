package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.journia.util.Codecs
import io.github.redstoneparadox.journia.util.field
import net.minecraft.world.gen.feature.FeatureConfig

class RockFormationFeatureConfig(
    val radiusRange: IntRange,
    val heightRange: IntRange,
    val offsetRange: IntRange,
    val countRange: IntRange
): FeatureConfig {
    companion object {
        val CODEC: Codec<RockFormationFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codecs.INT_RANGE.field("radius_range") { radiusRange },
                Codecs.INT_RANGE.field("height_range") { heightRange },
                Codecs.INT_RANGE.field("offset_range") { offsetRange },
                Codecs.INT_RANGE.field("count_range") { countRange }
            ).apply(instance, ::RockFormationFeatureConfig)
        }
    }
}