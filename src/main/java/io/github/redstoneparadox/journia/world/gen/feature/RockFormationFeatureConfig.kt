package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.journia.util.field
import net.minecraft.world.gen.feature.FeatureConfig

class RockFormationFeatureConfig(val radius: Int): FeatureConfig {
    companion object {
        val CODEC: Codec<RockFormationFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.field("radius") { radius }
            ).apply(instance, ::RockFormationFeatureConfig)
        }
    }
}