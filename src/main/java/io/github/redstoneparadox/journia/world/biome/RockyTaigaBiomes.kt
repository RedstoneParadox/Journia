package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.RockFormationFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.util.Identifier
import net.minecraft.world.biome.*
import net.minecraft.world.biome.BiomeKeys.STONE_SHORE
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object RockyTaigaBiomes {
    fun register() {
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
            .addTreeFeature(Feature.TREE.configure(JourniaFeatures.PINE_TREE_CONFIG), 4)
            .addTreeFeature(ConfiguredFeatures.PINE, 2)
            .addTreeFeature(ConfiguredFeatures.SPRUCE, 2)
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaFeatures.ROCK_FORMATION.configure(
                    RockFormationFeatureConfig(
                        3..8,
                        8..18,
                        3..6,
                        5..9
                    )
                ).decorate(
                    JourniaDecorators.RANDOM_HEIGHTMAP.configure(
                        ChanceDecoratorConfig(80)
                    )
                )
            )
            .addFeature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                Feature.FOREST_ROCK.configure(
                    SingleStateFeatureConfig(
                        Blocks.MOSSY_COBBLESTONE.defaultState
                    )
                ).decorate(
                    ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.repeatRandomly(2)
                )
            )
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaFeatures.SURFACE_PATCH.configure(
                    SurfacePatchFeatureConfig(Blocks.COARSE_DIRT.defaultState,
                        0.3,
                        listOf(
                            Blocks.GRASS_BLOCK.defaultState
                        )
                    )
                )
            ).addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaFeatures.SURFACE_PATCH.configure(
                    SurfacePatchFeatureConfig(
                        Blocks.STONE.defaultState,
                        0.3,
                        listOf(
                            Blocks.GRASS_BLOCK.defaultState,
                            Blocks.DIRT.defaultState,
                            Blocks.COARSE_DIRT.defaultState
                        ),
                        true
                    )
                )
            )
            .addStructureFeatures(
                MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004f, MineshaftFeature.Type.NORMAL)),
                StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT)
            )
            .addDefaultFeatures(
                LAKES,
                DUNGEONS,
                LARGE_FERNS,
                ORES,
                DISKS,
                DEFAULT_FLOWERS,
                TAIGA_GRASS,
                DEFAULT_MUSHROOMS,
                DEFAULT_VEGETATION,
                SPRINGS,
                SWEET_BERRY_BUSHES,
                FROZEN_TOP_LAYER
            )
            .addDefaultSpawnEntries()
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))

        if (BiomesConfig.RockyTaiga.enabled) {
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
                        JourniaFeatures.SURFACE_PATCH.configure(
                            SurfacePatchFeatureConfig(
                                Blocks.DIRT.defaultState,
                                0.4,
                                listOf(
                                    Blocks.STONE.defaultState,
                                    Blocks.ANDESITE.defaultState,
                                    Blocks.GRANITE.defaultState,
                                    Blocks.DIORITE.defaultState
                                )
                            )
                        )
                    )
                    .addFeature(
                        GenerationStep.Feature.RAW_GENERATION,
                        JourniaFeatures.ROCK_FORMATION.configure(
                            RockFormationFeatureConfig(
                                3..8,
                                8..18,
                                3..6,
                                5..9
                            )
                        ).decorate(
                            JourniaDecorators.RANDOM_HEIGHTMAP.configure(
                                ChanceDecoratorConfig(60)
                            )
                        )
                    )
                    .addFeature(
                        GenerationStep.Feature.LOCAL_MODIFICATIONS,
                        Feature.FOREST_ROCK.configure(
                            SingleStateFeatureConfig(
                                Blocks.MOSSY_COBBLESTONE.defaultState
                            )
                        ).decorate(
                            ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP.repeatRandomly(2)
                        )
                    )
                    .build()
            )

            JourniaBiomes.ROCKY_GIANT_TREE_TAIGA = JourniaBiomes.register("rocky_giant_tree_taiga",
                TerraformBiomeBuilder.create(template)
                    .addTreeFeature(
                        Feature.TREE.configure(JourniaFeatures.GIANT_PINE_TREE_CONFIG),
                        2
                    )
                    .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.STONE_CONFIG)
                    .addStructureFeatures(
                        ConfiguredStructureFeatures.PILLAGER_OUTPOST
                    )
                    .addFeature(
                        GenerationStep.Feature.RAW_GENERATION,
                        JourniaFeatures.SURFACE_PATCH.configure(
                            SurfacePatchFeatureConfig(
                                Blocks.DIRT.defaultState,
                                0.4,
                                listOf(
                                    Blocks.STONE.defaultState,
                                    Blocks.ANDESITE.defaultState,
                                    Blocks.GRANITE.defaultState,
                                    Blocks.DIORITE.defaultState
                                )
                            )
                        )
                    )
                    .build()
            )

            JourniaBiomes.ROCKY_TAIGA_HILLS = JourniaBiomes.register("rocky_taiga_hills",
                TerraformBiomeBuilder.create(template)
                    .depth(0.45f).scale(0.3f)
                    .build()
            )

            OverworldBiomes.addContinentalBiome(JourniaBiomes.ROCKY_TAIGA, OverworldClimate.COOL, BiomesConfig.RockyTaiga.weight)
            OverworldBiomes.addHillsBiome(JourniaBiomes.ROCKY_TAIGA, JourniaBiomes.ROCKY_TAIGA_HILLS, 1.0)
            OverworldBiomes.addShoreBiome(JourniaBiomes.ROCKY_TAIGA, JourniaBiomes.GRAVEL_BEACH, 1.0)
        }
        if (BiomesConfig.RockyTaigaMountains.enabled) {
            JourniaBiomes.ROCKY_TAIGA_MOUNTAINS = JourniaBiomes.register("rocky_taiga_mountains", TerraformBiomeBuilder.create(template)
                .depth(0.8f).scale(0.8f)
                .category(Biome.Category.EXTREME_HILLS)
                .build())

            OverworldBiomes.addContinentalBiome(JourniaBiomes.ROCKY_TAIGA_MOUNTAINS, OverworldClimate.COOL, BiomesConfig.RockyTaigaMountains.weight)
            OverworldBiomes.addShoreBiome(JourniaBiomes.ROCKY_TAIGA_MOUNTAINS, STONE_SHORE, 1.0)
        }
    }
}