package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.DefaultFeature.*
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object WastelandBiomes {
    fun register() {
        val template = TerraformBiomeBuilder.create()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.125F).scale(0.05F)
            .temperature(1.2f).downfall(0.0f)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(colorToInt(0.8, 0.8, 0.6))
                    .skyColor(JourniaBiomes.getSkyColor(1.2f))
                    .foliageColor(10387789)
                    .grassColor(9470285)
            )
            .addStructureFeatures(
                ConfiguredStructureFeatures.STRONGHOLD
            )
            .addDefaultFeatures(
                LAND_CARVERS,
                DUNGEONS,
                MINEABLES,
                ORES,
                DISKS,
                DEFAULT_MUSHROOMS,
                BADLANDS_GRASS,
                FOSSILS
            )
            .addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.RANDOM_SELECTOR
                    .configure(
                        RandomFeatureConfig(
                            mutableListOf(
                                JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_BIRCH_TREE_CONFIG).withChance(0.2f)
                            ) as List<RandomFeatureEntry>, JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_TREE_CONFIG))
                    )
                    .decorate(
                        Decorator.COUNT_EXTRA
                            .configure(CountExtraDecoratorConfig(0, 0.2F, 1))
                    )
            )
            .addDefaultMonsterSpawnEntries()
            .addSpawnEntry(SpawnSettings.SpawnEntry(EntityType.HUSK, 200, 4, 6))

        JourniaBiomes.WASTELAND = JourniaBiomes.register("wasteland",
            TerraformBiomeBuilder.create(template)
                .addWastelandSurfacePatches()
                .build()
        )
        JourniaBiomes.WASTELAND_RIVER = JourniaBiomes.register("wasteland_river",
            TerraformBiomeBuilder.create(template)
                .addWastelandSurfacePatches()
                .category(Biome.Category.RIVER)
                .depth(-0.5F).scale(0.0F)
                .temperature(1.0F).downfall(0.0F)
                .build()
        )
        JourniaBiomes.WASTELAND_SHORE = JourniaBiomes.register("wasteland_shore",
            TerraformBiomeBuilder.create(template)
                .category(Biome.Category.BEACH)
                .depth(0.0F).scale(0.025F)
                .temperature(1.0F).downfall(0.0f)
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.SURFACE_PATCH.configure(
                        SurfacePatchFeatureConfig(
                            state = Blocks.COARSE_DIRT.defaultState,
                            coverage = 0.5,
                            targets = listOf(
                                JourniaBlocks.CRACKED_GROUND.defaultState,
                                Blocks.COARSE_DIRT.defaultState
                            ),
                            size = 24.0,
                            integrity = 0.2
                        )
                    )
                )
                .build()
        )
        JourniaBiomes.WASTELAND_EDGE = JourniaBiomes.register("wasteland_edge",
            TerraformBiomeBuilder.create(template)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_EDGE_CONFIG)
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    ConfiguredFeatures.OAK_BADLANDS
                )
                .addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    ConfiguredFeatures.BIRCH
                )
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.SURFACE_PATCH.configure(
                        SurfacePatchFeatureConfig(
                            state = Blocks.RED_SAND.defaultState,
                            coverage = 0.2,
                            targets = listOf(
                                Blocks.COARSE_DIRT.defaultState
                            ),
                            below = true,
                            integrity = 0.2
                        )
                    )
                )
                .addFeature(
                    GenerationStep.Feature.RAW_GENERATION,
                    JourniaFeatures.SURFACE_PATCH.configure(
                        SurfacePatchFeatureConfig(
                            state = JourniaBlocks.CRACKED_GROUND.defaultState,
                            coverage = 0.4,
                            targets = listOf(
                                Blocks.COARSE_DIRT.defaultState
                            ),
                            below = true,
                            integrity = 0.2
                        )
                    )
                )
                .build()
        )

        /*
        if (BiomesConfig.Wasteland.enabled) {
            OverworldBiomes.addContinentalBiome(JourniaBiomes.WASTELAND, OverworldClimate.DRY, BiomesConfig.Wasteland.weight)
            OverworldBiomes.setRiverBiome(JourniaBiomes.WASTELAND, JourniaBiomes.WASTELAND_RIVER)
            OverworldBiomes.addShoreBiome(JourniaBiomes.WASTELAND, JourniaBiomes.WASTELAND_SHORE, 1.0)
            OverworldBiomes.addEdgeBiome(JourniaBiomes.WASTELAND, JourniaBiomes.WASTELAND_EDGE, 1.0)
        }
        */
    }

    private fun TerraformBiomeBuilder.addWastelandSurfacePatches(): TerraformBiomeBuilder {
        return this.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    state = Blocks.COARSE_DIRT.defaultState,
                    coverage = 0.3,
                    targets = listOf(
                        JourniaBlocks.CRACKED_GROUND.defaultState
                    ),
                    size = 24.0,
                    below = true,
                    integrity = 0.9
                )
            )
        ).addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    state = Blocks.RED_SAND.defaultState,
                    coverage = 0.6,
                    targets = listOf(
                        JourniaBlocks.CRACKED_GROUND.defaultState
                    ),
                    size = 24.0,
                    below = true,
                    integrity = 0.5
                )
            )
        )
    }
}