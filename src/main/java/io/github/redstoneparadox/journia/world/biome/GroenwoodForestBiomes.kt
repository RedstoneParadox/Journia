package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.*


object GroenwoodForestBiomes {
    val GROENWOOD_FOREST: Biome
    val GROENWOOD_FOREST_HILLS: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
                .temperature(0.7F).downfall(0.8F)
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .addTreeFeature(Feature.TREE.configure(JourniaFeatures.groenwoodTreeConfig()), 3)
                .foliageColor(0x47CD33)
                .addDefaultSpawnEntries()
                .addStructureFeature<MineshaftFeatureConfig>(MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)))
                .addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
                    PLAINS_FEATURES, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
                .addStructureFeature<DefaultFeatureConfig>(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT))
                .waterColor(4159204)
                .waterFogColor(329011)
        )

        GROENWOOD_FOREST = template.builder()
            .depth(0.1f)
            .scale(0.2f)
            .build()

        GROENWOOD_FOREST_HILLS = template.builder()
            .depth(0.45f)
            .scale(0.3f)
            .build()
    }

    fun register() {
        if (true) {
            JourniaBiomes.register("groenwood_forest", GROENWOOD_FOREST)
            JourniaBiomes.register("groenwood_forest_hills", GROENWOOD_FOREST_HILLS)
            JourniaBiomes.continentalBiome(GROENWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0)
            JourniaBiomes.variantBiome(GROENWOOD_FOREST, GROENWOOD_FOREST_HILLS, 0.2, OverworldClimate.TEMPERATE)
        }
    }
}