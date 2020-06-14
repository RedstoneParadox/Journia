package io.github.redstoneparadox.journia.world.gen.surfacebuilder

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig

class PentaSurfaceConfig(
    private val topMaterial: BlockState,
    private val secondaryMaterial: BlockState,
    private val tertiaryMaterial: BlockState,
    private val underMaterial: BlockState = Blocks.STONE.defaultState,
    private val underwaterMaterial: BlockState = Blocks.GRAVEL.defaultState,
    private val secondaryCutoff: Double = -0.95,
    private val tertiaryCutoff: Double = 1.75,
    private val scale: Double = 1.0
): SurfaceConfig {

    override fun getTopMaterial(): BlockState = topMaterial

    fun getSecondaryMaterial(): BlockState = secondaryMaterial

    fun getTertiaryMaterial(): BlockState = tertiaryMaterial

    override fun getUnderMaterial(): BlockState = underMaterial

    fun getUnderwaterMaterial(): BlockState = underwaterMaterial

    fun getSecondaryCutoff(): Double = secondaryCutoff

    fun getTertiaryCutoff(): Double = tertiaryCutoff

    fun getScale(): Double = scale

    companion object {
        val CODEC: Codec<PentaSurfaceConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockState.CODEC.fieldOf("topMaterial").forGetter { it.topMaterial },
                BlockState.CODEC.fieldOf("secondaryMaterial").forGetter { it.topMaterial },
                BlockState.CODEC.fieldOf("tertiaryMaterial").forGetter { it.topMaterial },
                BlockState.CODEC.fieldOf("underMaterial").forGetter { it.topMaterial },
                BlockState.CODEC.fieldOf("underwaterMaterial").forGetter { it.topMaterial },
                Codec.DOUBLE.fieldOf("secondaryCutoff").forGetter { it.secondaryCutoff },
                Codec.DOUBLE.fieldOf("tertiaryCutoff").forGetter { it.tertiaryCutoff },
                Codec.DOUBLE.fieldOf("scale").forGetter { it.scale }
            ).apply(instance, ::PentaSurfaceConfig)
        }
    }
}