package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaConfiguredFeatures
import io.github.redstoneparadox.journia.world.gen.feature.JourniaDecoratedFeatures
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.BiomeKeys.STONE_SHORE
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object RockyTaigaBiomes {
    fun init() {
        val template = TerraformBiomeBuilder.create()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
            .precipitation(Biome.Precipitation.RAIN)
            .category(Biome.Category.TAIGA)
            .temperature(0.25F)
            .downfall(0.8F)
            .depth(0.2F).scale(0.2F)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .skyColor(JourniaBiomes.getSkyColor(0.25f))
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                JourniaDecoratedFeatures.DENSE_PINE_TREE
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                JourniaDecoratedFeatures.SPARSE_VANILLA_PINE_TREE
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                JourniaDecoratedFeatures.SPARSE_SPRUCE_TREE
            )
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaDecoratedFeatures.LARGE_ROCK_80
            )
            .addFeature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                ConfiguredFeatures.FOREST_ROCK.decorate(
                    ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.repeatRandomly(2)
                )
            )
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaDecoratedFeatures.COARSE_DIRT_PATCH_4
            ).addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaDecoratedFeatures.STONE_PATCH_4
            )
            .addStructureFeatures(
                ConfiguredStructureFeatures.MINESHAFT,
                ConfiguredStructureFeatures.STRONGHOLD
            )
            .addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
                TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY,
                FROZEN_TOP_LAYER, DEFAULT_FLOWERS)
            .addDefaultSpawnEntries()
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))

        JourniaBiomes.ROCKY_TAIGA = JourniaBiomes.register("rocky_taiga",
            TerraformBiomeBuilder.create(template)
                .addStructureFeatures(
                    ConfiguredStructureFeatures.VILLAGE_TAIGA,
                    ConfiguredStructureFeatures.PILLAGER_OUTPOST
                )
                .build()

        )

        JourniaBiomes.MODIFIED_ROCKY_TAIGA = JourniaBiomes.register("modified_rocky_taiga",
            TerraformBiomeBuilder.create(template)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.STONE_CONFIG)
                .addStructureFeatures(
                    ConfiguredStructureFeatures.PILLAGER_OUTPOST
                )
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaConfiguredFeatures.STONE_PATCH
                )
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaDecoratedFeatures.LARGE_ROCK_60
                )
                .addFeature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    ConfiguredFeatures.FOREST_ROCK
                )
                .build()
        )

        JourniaBiomes.ROCKY_GIANT_TREE_TAIGA = JourniaBiomes.register("rocky_giant_tree_taiga",
            TerraformBiomeBuilder.create(template)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.STONE_CONFIG)
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    JourniaDecoratedFeatures.SPARSE_GIANT_PINE_TREE
                )
                .addStructureFeatures(
                    ConfiguredStructureFeatures.PILLAGER_OUTPOST
                )
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaDecoratedFeatures.COARSE_DIRT_PATCH_4
                )
                .build()
        )

        JourniaBiomes.ROCKY_TAIGA_HILLS = JourniaBiomes.register("rocky_taiga_hills",
            TerraformBiomeBuilder.create(template)
                .depth(0.45f).scale(0.3f)
                .build()
        )

        JourniaBiomes.ROCKY_TAIGA_MOUNTAINS = JourniaBiomes.register("rocky_taiga_mountains", TerraformBiomeBuilder.create(template)
            .depth(0.8f).scale(0.8f)
            .category(Biome.Category.EXTREME_HILLS)
            .build())

        if (BiomesConfig.RockyTaiga.enabled) {
            OverworldBiomes.addContinentalBiome(JourniaBiomes.ROCKY_TAIGA, OverworldClimate.COOL, BiomesConfig.RockyTaiga.weight)
            OverworldBiomes.addHillsBiome(JourniaBiomes.ROCKY_TAIGA, JourniaBiomes.ROCKY_TAIGA_HILLS, 1.0)
            OverworldBiomes.addShoreBiome(JourniaBiomes.ROCKY_TAIGA, JourniaBiomes.GRAVEL_BEACH, 1.0)
        }
        if (BiomesConfig.RockyTaigaMountains.enabled) {
            OverworldBiomes.addContinentalBiome(JourniaBiomes.ROCKY_TAIGA_MOUNTAINS, OverworldClimate.COOL, BiomesConfig.RockyTaigaMountains.weight)
            OverworldBiomes.addShoreBiome(JourniaBiomes.ROCKY_TAIGA_MOUNTAINS, STONE_SHORE, 1.0)
        }
    }
}