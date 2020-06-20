package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import io.github.redstoneparadox.journia.config.BiomesConfig
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.*


object CubenForestBiomes {
    val CUBEN_FOREST: Biome
    val CUBEN_FOREST_HILLS: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
                .temperature(0.7F).downfall(0.8F)
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .addTreeFeature(Feature.TREE.configure(JourniaFeatures.cubenTreeConfig()), 5)
                .addDefaultSpawnEntries()
                .addStructureFeature<MineshaftFeatureConfig>(MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)))
                .addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
                    PLAINS_FEATURES, DEFAULT_MUSHROOMS, FOREST_FLOWERS, FOREST_GRASS, SPRINGS, FROZEN_TOP_LAYER)
                .addStructureFeature<DefaultFeatureConfig>(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT))
                .waterColor(4159204)
                .waterFogColor(329011)
        )

        CUBEN_FOREST = template.builder()
            .depth(0.1f)
            .scale(0.2f)
            .build()

        CUBEN_FOREST_HILLS = template.builder()
            .depth(0.45f)
            .scale(0.3f)
            .build()
    }

    fun register() {
        if (BiomesConfig.CubenForest.enabled) {
            JourniaBiomes.register("cuben_forest", CUBEN_FOREST)
            JourniaBiomes.register("cuben_forest_hills", CUBEN_FOREST_HILLS)
            JourniaBiomes.continentalBiome(CUBEN_FOREST, OverworldClimate.TEMPERATE, BiomesConfig.CubenForest.weight)
            JourniaBiomes.hillsBiome(CUBEN_FOREST, CUBEN_FOREST_HILLS, BiomesConfig.CubenForest.hills_chance)
        }
    }
}