package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig

class SurfacePatchFeatureConfig(val state: BlockState, val startRadius: Int, val target: Target, val integrity: Double = 1.0): FeatureConfig {
    companion object {
        val CODEC: Codec<SurfacePatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockState.CODEC.fieldOf("state").forGetter { it.state },
                Codec.INT.fieldOf("startRadius").forGetter { it.startRadius },
                Target.CODEC.fieldOf("target").forGetter { it.target },
                Codec.DOUBLE.fieldOf("integrity").forGetter { it.integrity }
            ).apply(instance, ::SurfacePatchFeatureConfig)
        }
    }

    enum class Target(private val predicate: (BlockState) -> Boolean) {
        GRASS({ it.block == Blocks.GRASS_BLOCK }),
        DIRT({it.block == Blocks.DIRT || it.block == Blocks.COARSE_DIRT}),
        STONE({it.block == Blocks.STONE || it.block == Blocks.ANDESITE}),
        WASTELAND({it.block == JourniaBlocks.CRACKED_GROUND});

        fun test(state: BlockState) = this.predicate(state)

        companion object {
            val CODEC: Codec<Target> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("name").forGetter { it.name }
                ).apply(instance, ::valueOf)
            }
        }
    }
}