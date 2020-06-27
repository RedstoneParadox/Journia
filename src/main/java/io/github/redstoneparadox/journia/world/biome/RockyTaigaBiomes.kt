package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature
import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.NewSurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biome.SpawnEntry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*

object RockyTaigaBiomes {
    val ROCKY_TAIGA: Biome
    val ROCKY_TAIGA_HILLS: Biome
    val ROCKY_TAIGA_MOUNTAINS: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(JourniaSurfaceBuilders.PENTA, JourniaSurfaceBuilders.ROCKY_TAIGA_CONFIG)
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.25F)
                .downfall(0.8F)
                .waterColor(4159204)
                .waterFogColor(329011)
                .addTreeFeature(Feature.TREE.configure(JourniaFeatures.PINE_TREE_CONFIG), 4)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.PINE_TREE_CONFIG), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG), 2)
                .addCustomFeature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    Feature.FOREST_ROCK.configure(
                        ForestRockFeatureConfig(
                            Blocks.ANDESITE.defaultState,
                            0
                        )
                    ).createDecoratedFeature(
                        Decorator.FOREST_ROCK.configure(
                            CountDecoratorConfig(3)
                        )
                    )
                ).addCustomFeature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    Feature.FOREST_ROCK.configure(
                        ForestRockFeatureConfig(
                            Blocks.COBBLESTONE.defaultState,
                            0
                        )
                    ).createDecoratedFeature(
                        Decorator.FOREST_ROCK.configure(
                            CountDecoratorConfig(3)
                        )
                    )
                )
                .addCustomFeature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    Feature.FOREST_ROCK.configure(
                        ForestRockFeatureConfig(
                            Blocks.MOSSY_COBBLESTONE.defaultState,
                            0
                        )
                    ).createDecoratedFeature(
                        Decorator.FOREST_ROCK.configure(
                            CountDecoratorConfig(3)
                        )
                    )
                ).addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.NEW_SURFACE_PATCH.configure(
                        NewSurfacePatchFeatureConfig(Blocks.COARSE_DIRT.defaultState, 0.3, listOf(
                            Blocks.GRASS_BLOCK.defaultState,
                            Blocks.STONE.defaultState,
                            Blocks.ANDESITE.defaultState
                        ))
                    )
                ).addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.NEW_SURFACE_PATCH.configure(
                        NewSurfacePatchFeatureConfig(Blocks.STONE.defaultState, 0.3, listOf(
                            Blocks.GRASS_BLOCK.defaultState
                        ))
                    )
                )
                .addStructureFeatures(
                    MineshaftFeature.MINESHAFT.configure(MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)),
                    StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT)
                )
                .addDefaultFeatures(
                    DefaultFeature.LAKES,
                    DefaultFeature.DUNGEONS,
                    DefaultFeature.LARGE_FERNS,
                    DefaultFeature.ORES,
                    DefaultFeature.DISKS,
                    DefaultFeature.DEFAULT_FLOWERS,
                    DefaultFeature.TAIGA_GRASS,
                    DefaultFeature.DEFAULT_MUSHROOMS,
                    DefaultFeature.DEFAULT_VEGETATION,
                    DefaultFeature.SPRINGS,
                    DefaultFeature.SWEET_BERRY_BUSHES,
                    DefaultFeature.FROZEN_TOP_LAYER
                )
                .addDefaultSpawnEntries()
                .addSpawnEntry(SpawnEntry(EntityType.WOLF, 8, 4, 4))
                .addSpawnEntry(SpawnEntry(EntityType.RABBIT, 4, 2, 3))
                .addSpawnEntry(SpawnEntry(EntityType.FOX, 8, 2, 4))
        )

        ROCKY_TAIGA = template.builder()
            .depth(0.2F).scale(0.2F)
            .category(Biome.Category.TAIGA)
            .addStructureFeatures(
                VillageFeature.VILLAGE.configure(StructurePoolFeatureConfig(Identifier("village/taiga/town_centers"), 6)),
                PillagerOutpostFeature.PILLAGER_OUTPOST.configure(FeatureConfig.DEFAULT)
            )
            .build()

        ROCKY_TAIGA_HILLS = template.builder()
            .depth(0.45f).scale(0.3f)
            .category(Biome.Category.TAIGA)
            .build()

        ROCKY_TAIGA_MOUNTAINS = template.builder()
            .depth(0.8f).scale(0.8f)
            .category(Biome.Category.EXTREME_HILLS)
            .build()
    }

    fun register() {
        if (BiomesConfig.RockyTaiga.enabled) {
            JourniaBiomes.register("rocky_taiga", ROCKY_TAIGA)
            JourniaBiomes.register("rocky_taiga_hills", ROCKY_TAIGA_HILLS)
        }
        if (BiomesConfig.RockyTaigaMountains.enabled) {
            JourniaBiomes.register("rocky_taiga_mountains", ROCKY_TAIGA_MOUNTAINS)
        }
    }
}