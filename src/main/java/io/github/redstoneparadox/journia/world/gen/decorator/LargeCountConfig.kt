package io.github.redstoneparadox.journia.world.gen.decorator

import com.mojang.serialization.Codec
import net.minecraft.world.gen.UniformIntDistribution
import net.minecraft.world.gen.decorator.DecoratorConfig

class LargeCountConfig(private val count: UniformIntDistribution): DecoratorConfig {
    constructor(count: Int): this(UniformIntDistribution.of(count))

    fun getCount(): UniformIntDistribution = count

    companion object {
        val CODEC: Codec<LargeCountConfig> = UniformIntDistribution
            .createValidatedCodec(-20, 512, 512)
            .fieldOf("count")
            .xmap(::LargeCountConfig, LargeCountConfig::getCount)
            .codec()
    }
}