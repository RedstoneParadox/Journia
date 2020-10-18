package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature
import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.BlockBandsFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object BandedMountainsBiomes {
    fun register() {
        if (BiomesConfig.BandedMountains.enabled) {
            val template = TerraformBiomeBuilder.create()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.BANDED_MOUNTAINS_CONFIG)
                .temperature(0.7f)
                .downfall(0.8f)
                .precipitation(Biome.Precipitation.RAIN)
                .effects(
                    BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                )
                .category(Biome.Category.EXTREME_HILLS)
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.SURFACE_PATCH.configure(
                        SurfacePatchFeatureConfig(
                            Blocks.STONE.defaultState,
                            0.25,
                            listOf(
                                Blocks.GRASS_BLOCK.defaultState
                            ),
                            true)
                    )
                )
                .addFeature(
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
                .addTreeFeature(
                    ConfiguredFeatures.OAK_BEES_005,
                    4
                )
                .addTreeFeature(
                    ConfiguredFeatures.BIRCH_BEES_005,
                    3
                )
                .addDefaultSpawnEntries()
                .addDefaultFeatures(
                    LAND_CARVERS,
                    //STRUCTURES,
                    LAKES,
                    DUNGEONS,
                    MINEABLES,
                    ORES,
                    DISKS,
                    DEFAULT_MUSHROOMS,
                    DEFAULT_VEGETATION,
                    SPRINGS,
                    FOREST_GRASS,
                    FOREST_FLOWERS
                )

            JourniaBiomes.BANDED_MOUNTAINS = JourniaBiomes.register("banded_mountains",
                TerraformBiomeBuilder.create(template)
                    .depth(0.9f)
                    .scale(0.7f)
                    .build()
            )

            JourniaBiomes.MODIFIED_BANDED_MOUNTAINS = JourniaBiomes.register("modified_banded_mountains",
                TerraformBiomeBuilder.create(template)
                    .depth(0.8f)
                    .scale(0.8f)
                    .build()
            )

            JourniaBiomes.BANDED_MOUNTAINS_RIVER = JourniaBiomes.register("banded_mountains_river",
                TerraformBiomeBuilder.create(template)
                    .category(Biome.Category.RIVER)
                    .depth(-0.95F)
                    .scale(-0.15F)
                    .temperature(0.5F)
                    .downfall(0.5F)
                    .build()
            )

            JourniaBiomes.BANDED_SHORE = JourniaBiomes.register("banded_shore",
                TerraformBiomeBuilder.create(template)
                    .category(Biome.Category.NONE)
                    .depth(0.1F)
                    .scale(0.5F)
                    .temperature(0.2F)
                    .downfall(0.3F)
                    .build()
            )

            OverworldBiomes.addContinentalBiome(JourniaBiomes.BANDED_MOUNTAINS, OverworldClimate.TEMPERATE, BiomesConfig.BandedMountains.weight)
            OverworldBiomes.addBiomeVariant(JourniaBiomes.BANDED_MOUNTAINS, JourniaBiomes.MODIFIED_BANDED_MOUNTAINS, BiomesConfig.BandedMountains.modified_chance)
            OverworldBiomes.setRiverBiome(JourniaBiomes.BANDED_MOUNTAINS, JourniaBiomes.BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.setRiverBiome(JourniaBiomes.MODIFIED_BANDED_MOUNTAINS, JourniaBiomes.BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.addShoreBiome(JourniaBiomes.BANDED_MOUNTAINS, JourniaBiomes.BANDED_SHORE, 1.0)
            OverworldBiomes.addShoreBiome(JourniaBiomes.MODIFIED_BANDED_MOUNTAINS, JourniaBiomes.BANDED_SHORE, 1.0)
        }
    }
}