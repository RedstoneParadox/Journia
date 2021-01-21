package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.world.gen.feature.JourniaDecoratedFeatures
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.BiomeKeys
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object ShoreBiomes {
    fun register() {
        val template = TerraformBiomeBuilder.create()
            .category(Biome.Category.BEACH)
            .depth(0.0f).scale(0.125f)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
            )
            .precipitation(Biome.Precipitation.RAIN)

        JourniaBiomes.GRAVEL_BEACH = JourniaBiomes.register("gravel_beach", TerraformBiomeBuilder.create(template)
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRAVEL_CONFIG)
            .temperature(0.25F)
            .downfall(0.8F)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .skyColor(JourniaBiomes.getSkyColor(0.25f))
            )
            .build()
        )

        JourniaBiomes.GRASSY_BEACH = JourniaBiomes.register("grassy_beach", TerraformBiomeBuilder.create(template)
            .surfaceBuilder(ConfiguredSurfaceBuilders.DESERT)
            .temperature(0.25F)
            .downfall(0.8F)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .skyColor(JourniaBiomes.getSkyColor(0.25f))
            )
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaDecoratedFeatures.GRASS_PATCH_8
            )
            .addDefaultFeatures(
                DefaultFeature.DEFAULT_GRASS,
                DefaultFeature.DEFAULT_MUSHROOMS,
                DefaultFeature.DEFAULT_UNDERGROUND_STRUCTURES,
                DefaultFeature.DEFAULT_VEGETATION
            )
            .build()
        )

        OverworldBiomes.addShoreBiome(BiomeKeys.PLAINS, JourniaBiomes.GRASSY_BEACH, 1.0)
    }
}