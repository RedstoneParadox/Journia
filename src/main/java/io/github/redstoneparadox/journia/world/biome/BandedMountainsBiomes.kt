package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature
import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import com.terraformersmc.terraform.biome.builder.TerraformBiome
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
    val SHATTERD_BANDED_MOUNTAINS: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.AZURE_CONFIG)
                .temperature(0.95f)
                .downfall(0.9f)
                .precipitation(Biome.Precipitation.RAIN)
                .waterColor(4159204)
                .waterFogColor(329011)
                .grassColor(0x009900)
                .foliageColor(0x009900)
                .category(Biome.Category.EXTREME_HILLS)
                .addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.BLOCK_BANDS.configure(
                        BlockBandsFeatureConfig(
                            listOf(
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_TERRACOTTA.defaultState, 2, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_TERRACOTTA.defaultState, 1, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 3, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 2, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 1, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.GRAY_TERRACOTTA.defaultState, 2, 1),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLACK_TERRACOTTA.defaultState, 2, 1)
                            ),
                            1,
                            4
                        )
                    )
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
            .depth(0.5f)
            .scale(0.75f)
            .build()

        SHATTERD_BANDED_MOUNTAINS = template.builder()
            .depth(0.8f)
            .scale(1.2f)
            .build()
    }

    fun register() {
        JourniaBiomes.register("banded_mountains", BANDED_MOUNTAINS)
        JourniaBiomes.register("shattered_banded_mountains", SHATTERD_BANDED_MOUNTAINS)

        OverworldBiomes.addContinentalBiome(BANDED_MOUNTAINS, OverworldClimate.TEMPERATE, 0.8)
        OverworldBiomes.addBiomeVariant(BANDED_MOUNTAINS, SHATTERD_BANDED_MOUNTAINS, 0.3)
    }
}