package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.BlockBandsFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.NewSurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object BandedMountainsBiomes {
    val BANDED_MOUNTAINS: Biome
    val SHATTERD_BANDED_MOUNTAINS: Biome
    val BANDED_MOUNTAINS_RIVER: Biome
    val BANDED_SHORE: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.BANDED_MOUNTAINS_CONFIG)
                .temperature(0.7f)
                .downfall(0.8f)
                .precipitation(Biome.Precipitation.RAIN)
                .waterColor(4159204)
                .waterFogColor(329011)
                .category(Biome.Category.EXTREME_HILLS)
                .addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.NEW_SURFACE_PATCH.configure(
                        NewSurfacePatchFeatureConfig(
                            Blocks.GRASS_BLOCK.defaultState,
                            0.3,
                            listOf(
                                Blocks.STONE.defaultState,
                                Blocks.CYAN_TERRACOTTA.defaultState,
                                Blocks.BLUE_TERRACOTTA.defaultState,
                                Blocks.GRAY_TERRACOTTA.defaultState,
                                Blocks.BLACK_TERRACOTTA.defaultState
                            )
                        )
                    )
                )
                .addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.BLOCK_BANDS.configure(
                        BlockBandsFeatureConfig(
                            listOf(
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_TERRACOTTA.defaultState, 3, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_TERRACOTTA.defaultState, 2, 4),
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_TERRACOTTA.defaultState, 1, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 3, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 2, 4),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 1, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.GRAY_TERRACOTTA.defaultState, 2, 1),
                                BlockBandsFeatureConfig.BlockBand(Blocks.GRAY_TERRACOTTA.defaultState, 1, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLACK_TERRACOTTA.defaultState, 2, 1),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLACK_TERRACOTTA.defaultState, 1, 2)
                            ),
                            1,
                            4,
                            16,
                            4
                        )
                    )
                )
                .addCustomFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                        OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.DIRT.defaultState, 33)
                    ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 63)))
                )
                .addCustomFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                        OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GRAVEL.defaultState, 33)
                    ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(8, 0, 0, 256)))
                )
                .addCustomFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                        OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GRANITE.defaultState, 33)
                    ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 63)))
                )
                .addCustomFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                        OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.DIORITE.defaultState, 33)
                    ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 63)))
                )
                .addCustomFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                        OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.ANDESITE.defaultState, 33)
                    ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 80)))
                )
                .addTreeFeature(
                    Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_WITH_RARE_BEEHIVES_CONFIG),
                    3
                )
                .addTreeFeature(
                    Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_WITH_BEEHIVES_CONFIG),
                    2
                )
                .addDefaultSpawnEntries()
                .addDefaultFeatures(
                    LAND_CARVERS,
                    STRUCTURES,
                    LAKES,
                    DUNGEONS,
                    // MINEABLES,
                    ORES,
                    DISKS,
                    DEFAULT_MUSHROOMS,
                    DEFAULT_VEGETATION,
                    SPRINGS,
                    FOREST_GRASS,
                    FOREST_FLOWERS
                )
        )

        BANDED_MOUNTAINS = template.builder()
            .depth(0.5f)
            .scale(0.75f)
            .build()

        SHATTERD_BANDED_MOUNTAINS = template.builder()
            .depth(0.8f)
            .scale(1.2f)
            .build()

        BANDED_MOUNTAINS_RIVER = template.builder()
            .category(Biome.Category.RIVER)
            .depth(-0.5F)
            .scale(0.0F)
            .temperature(0.5F)
            .downfall(0.5F)
            .build()

        BANDED_SHORE = template.builder()
            .category(Biome.Category.NONE)
            .depth(0.1F)
            .scale(0.8F)
            .temperature(0.2F)
            .downfall(0.3F)
            .build()
    }

    fun register() {
        if (BiomesConfig.BandedMountains.enabled) {
            JourniaBiomes.register("banded_mountains", BANDED_MOUNTAINS)
            JourniaBiomes.register("shattered_banded_mountains", SHATTERD_BANDED_MOUNTAINS)
            JourniaBiomes.register("banded_mountains_river", BANDED_MOUNTAINS_RIVER)
            JourniaBiomes.register("banded_shore", BANDED_SHORE)

            OverworldBiomes.addContinentalBiome(BANDED_MOUNTAINS, OverworldClimate.TEMPERATE, BiomesConfig.BandedMountains.weight)
            OverworldBiomes.addBiomeVariant(BANDED_MOUNTAINS, SHATTERD_BANDED_MOUNTAINS, BiomesConfig.BandedMountains.shattered_chance)
            OverworldBiomes.setRiverBiome(BANDED_MOUNTAINS, BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.setRiverBiome(SHATTERD_BANDED_MOUNTAINS, BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.addShoreBiome(BANDED_MOUNTAINS, BANDED_SHORE, 1.0)
            OverworldBiomes.addShoreBiome(SHATTERD_BANDED_MOUNTAINS, BANDED_SHORE, 1.0)
        }
    }
}