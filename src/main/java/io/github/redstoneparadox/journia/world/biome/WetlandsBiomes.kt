package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.world.gen.feature.JourniaDecoratedFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.*


object WetlandsBiomes {
    fun init() {
        val template = TerraformBiomeBuilder.create()
            .configureSurfaceBuilder(JourniaSurfaceBuilders.WETLANDS, JourniaSurfaceBuilders.WETLANDS_CONFIG)
            .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE)
            .depth(-0.2f).scale(0.1f)
            .temperature(0.8f).downfall(0.95f)
            .effects(
                BiomeEffects.Builder()
                    .fogColor(12638463)
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .skyColor(JourniaBiomes.getSkyColor(0.2f))
            )
            .addStructureFeature<FeatureConfig>(
                StructureFeature.JUNGLE_PYRAMID.configure(
                    FeatureConfig.DEFAULT
                )
            )
            .addStructureFeature<MineshaftFeatureConfig>(
                StructureFeature.MINESHAFT.configure(
                    MineshaftFeatureConfig(0.004f, MineshaftFeature.Type.NORMAL)
                )
            )
            .addStructureFeature<FeatureConfig>(
                JungleTempleFeature.STRONGHOLD.configure(
                    FeatureConfig.DEFAULT
                )
            )
            .addDefaultFeatures(
                LAND_CARVERS,
                DEFAULT_UNDERGROUND_STRUCTURES,
                LAKES,
                DUNGEONS,
                FOREST_FLOWERS,
                MINEABLES,
                ORES,
                // DISKS,
                DEFAULT_FLOWERS,
                DEFAULT_MUSHROOMS,
                FOREST_GRASS,
                DEFAULT_VEGETATION,
                SPRINGS,
                FROZEN_TOP_LAYER,
                TALL_BIRCH_TREES
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                JourniaDecoratedFeatures.WETLANDS_SEAGRASS_TALL
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                JourniaDecoratedFeatures.WETLANDS_SEAGRASS_SHORT
            )
            .addFeature(
                GenerationStep.Feature.RAW_GENERATION,
                JourniaDecoratedFeatures.MUD_PATCH_8
            )
            .addFeature(
                GenerationStep.Feature.UNDERGROUND_ORES,
                ConfiguredFeatures.DISK_CLAY
            )

        JourniaBiomes.WETLANDS = JourniaBiomes.register("wetlands",
            TerraformBiomeBuilder.create(template)
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    JourniaDecoratedFeatures.WETLANDS_JUNGLE_TREE
                )
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    JourniaDecoratedFeatures.WETLANDS_GRASS
                )
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    JourniaDecoratedFeatures.WETLANDS_BAMBOO
                )
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    JourniaDecoratedFeatures.WETLANDS_BAMBOO_VEGITATION
                )
                .build()
        )

        OverworldBiomes.addContinentalBiome(JourniaBiomes.WETLANDS, OverworldClimate.TEMPERATE, 1.0)
    }
}