package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.BlockBandsFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object BandedMountainsBiomes {
    val BANDED_MOUNTAINS: Biome
    val MODIFIED_BANDED_MOUNTAINS: Biome
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
                    Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_WITH_RARE_BEEHIVES_CONFIG),
                    4
                )
                .addTreeFeature(
                    Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_WITH_BEEHIVES_CONFIG),
                    3
                )
                .addDefaultSpawnEntries()
                .addDefaultFeatures(
                    LAND_CARVERS,
                    STRUCTURES,
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
        )

        BANDED_MOUNTAINS = template.builder()
            .depth(1.0f)
            .scale(0.6f)
            .build()

        MODIFIED_BANDED_MOUNTAINS = template.builder()
            .depth(0.8f)
            .scale(0.8f)
            .build()

        BANDED_MOUNTAINS_RIVER = template.builder()
            .category(Biome.Category.RIVER)
            .depth(-0.95F)
            .scale(-0.15F)
            .temperature(0.5F)
            .downfall(0.5F)
            .build()

        BANDED_SHORE = template.builder()
            .category(Biome.Category.NONE)
            .depth(0.1F)
            .scale(0.5F)
            .temperature(0.2F)
            .downfall(0.3F)
            .build()
    }

    fun register() {
        if (BiomesConfig.BandedMountains.enabled) {
            JourniaBiomes.register("banded_mountains", BANDED_MOUNTAINS)
            JourniaBiomes.register("modified_banded_mountains", MODIFIED_BANDED_MOUNTAINS)
            JourniaBiomes.register("banded_mountains_river", BANDED_MOUNTAINS_RIVER)
            JourniaBiomes.register("banded_shore", BANDED_SHORE)

            OverworldBiomes.addContinentalBiome(BANDED_MOUNTAINS, OverworldClimate.TEMPERATE, BiomesConfig.BandedMountains.weight)
            OverworldBiomes.addBiomeVariant(BANDED_MOUNTAINS, MODIFIED_BANDED_MOUNTAINS, BiomesConfig.BandedMountains.modified_chance)
            OverworldBiomes.setRiverBiome(BANDED_MOUNTAINS, BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.setRiverBiome(MODIFIED_BANDED_MOUNTAINS, BANDED_MOUNTAINS_RIVER)
            OverworldBiomes.addShoreBiome(BANDED_MOUNTAINS, BANDED_SHORE, 1.0)
            OverworldBiomes.addShoreBiome(MODIFIED_BANDED_MOUNTAINS, BANDED_SHORE, 1.0)
        }
    }
}