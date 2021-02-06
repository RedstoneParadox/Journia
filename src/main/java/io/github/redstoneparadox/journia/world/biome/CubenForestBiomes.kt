package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder


object CubenForestBiomes {
   fun init() {
       val template = TerraformBiomeBuilder.create()
           .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
           .temperature(0.7F).downfall(0.8F)
           .precipitation(Biome.Precipitation.RAIN)
           .category(Biome.Category.FOREST)
           .addFeature(
               GenerationStep.Feature.VEGETAL_DECORATION,
               Feature.TREE
                   .configure(JourniaFeatures.cubenTreeConfig())
                   .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                   .decorate(
                       Decorator.COUNT_EXTRA.configure(CountExtraDecoratorConfig(4, 0.1f, 1))
                   )
           )
           .addDefaultSpawnEntries()
           .addStructureFeature<MineshaftFeatureConfig>(MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004f, MineshaftFeature.Type.NORMAL)))
           .addDefaultFeatures(LAND_CARVERS, /*STRUCTURES,*/ LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
               PLAINS_FEATURES, DEFAULT_MUSHROOMS, FOREST_FLOWERS, FOREST_GRASS, SPRINGS, FROZEN_TOP_LAYER)
           .addStructureFeature<DefaultFeatureConfig>(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT))
           .effects(
               BiomeEffects.Builder()
                   .waterColor(4159204)
                   .waterFogColor(329011)
                   .fogColor(12638463)
                   .skyColor(JourniaBiomes.getSkyColor(0.7f))
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

       /*
       if (BiomesConfig.CubenForest.enabled) {
           OverworldBiomes.addContinentalBiome(JourniaBiomes.CUBEN_FOREST, OverworldClimate.TEMPERATE, BiomesConfig.CubenForest.weight)
           OverworldBiomes.addHillsBiome(JourniaBiomes.CUBEN_FOREST, JourniaBiomes.CUBEN_FOREST_HILLS, BiomesConfig.CubenForest.hills_chance)
       }
       */
    }
}