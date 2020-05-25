package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.RandomFeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureEntry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class GroenwoodForestBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
        .precipitation(Precipitation.RAIN)
        .category(Category.EXTREME_HILLS)
        .depth(0.2F).scale(0.2F)
        .temperature(0.25F)
        .downfall(0.8F)
        .effects(
            BiomeEffects.Builder()
                .fogColor(12638463)
                .waterColor(4159204)
                .waterFogColor(329011)
                .build())
        .parent("null")
) {
    init {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(

                    ), Feature.TREE.configure(
                        JourniaFeatures.groenwoodTreeConfig()
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        1,
                        0.5f,
                        1
                    )
                )
            )
        )
    }
}