package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.*
import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder


object CubenForestBiomes {
   fun register() {
        if (BiomesConfig.CubenForest.enabled) {
            val template = TerraformBiomeBuilder.create()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
                .temperature(0.7F).downfall(0.8F)
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .addTreeFeature(Feature.TREE.configure(JourniaFeatures.cubenTreeConfig()), 5)
                .addDefaultSpawnEntries()
                .addStructureFeature<MineshaftFeatureConfig>(MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004f, MineshaftFeature.Type.NORMAL)))
                .addDefaultFeatures(LAND_CARVERS, /*STRUCTURES,*/ LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
                    PLAINS_FEATURES, DEFAULT_MUSHROOMS, FOREST_FLOWERS, FOREST_GRASS, SPRINGS, FROZEN_TOP_LAYER)
                .addStructureFeature<DefaultFeatureConfig>(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT))
                .effects(
                    BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                )

            JourniaBiomes.CUBEN_FOREST = JourniaBiomes.register("cuben_forest",
                TerraformBiomeBuilder.create(template)
                    .depth(0.1f)
                    .scale(0.2f)
                    .build()
            )

            JourniaBiomes.CUBEN_FOREST_HILLS = JourniaBiomes.register("cuben_forest_hills",
                TerraformBiomeBuilder.create(template)
                    .depth(0.45f)
                    .scale(0.3f)
                    .build()
            )

            OverworldBiomes.addContinentalBiome(JourniaBiomes.CUBEN_FOREST, OverworldClimate.TEMPERATE, BiomesConfig.CubenForest.weight)
            OverworldBiomes.addHillsBiome(JourniaBiomes.CUBEN_FOREST, JourniaBiomes.CUBEN_FOREST_HILLS, BiomesConfig.CubenForest.hills_chance)
        }
    }
}