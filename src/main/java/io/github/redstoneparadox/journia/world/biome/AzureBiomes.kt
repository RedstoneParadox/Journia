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
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.AZURE_CONFIG)
                .temperature(0.95f)
                .downfall(0.9f)
                .precipitation(Biome.Precipitation.RAIN)
                .waterColor(0x47d1d1)
                .waterFogColor(0x2eb8b8)
                .grassColor(0x00cc00)
                .foliageColor(0x00cc00)
                .category(Biome.Category.EXTREME_HILLS)
                .addCustomFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.BLOCK_BANDS.configure(
                        BlockBandsFeatureConfig(
                            listOf(
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_CONCRETE.defaultState, 2, 3),
                                BlockBandsFeatureConfig.BlockBand(Blocks.CYAN_CONCRETE.defaultState, 5, 1),
                                BlockBandsFeatureConfig.BlockBand(Blocks.LIGHT_BLUE_TERRACOTTA.defaultState, 3, 2),
                                BlockBandsFeatureConfig.BlockBand(Blocks.BLUE_TERRACOTTA.defaultState, 3, 1)
                            ),
                            4,
                            7
                        )
                    )
                )
        )

        AZURE = template.builder()
            .depth(0.3f)
            .scale(0.75f)
            .build()
    }

    fun register() {
        JourniaBiomes.register("azure", AZURE)
    }
}