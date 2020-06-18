package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.world.gen.feature.BlockBandsFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object AzureBiomes {
    val AZURE: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
                .temperature(0.95f)
                .downfall(0.0f)
                .precipitation(Biome.Precipitation.RAIN)
                .waterColor(0x47d1d1)
                .waterFogColor(0x2eb8b8)
                .category(Biome.Category.EXTREME_HILLS)
                .addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.BLOCK_BANDS.configure(
                        BlockBandsFeatureConfig(
                            listOf(
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 2, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 5, 1)
                            ),
                            3,
                            7
                        )
                    )
                )
        )

        AZURE = template.builder()
            .depth(0.125f)
            .scale(1.0f)
            .build()
    }

    fun register() {
        JourniaBiomes.register("azure", AZURE)
    }
}