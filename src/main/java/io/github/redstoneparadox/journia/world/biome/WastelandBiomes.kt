package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.util.skyFogColor
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object WastelandBiomes {
    val WASTELAND: Biome
    val WASTELAND_RIVER: Biome
    val WASTELAND_SHORE: Biome
    val WASTELAND_EDGE: Biome

    init {
        val template = TerraformBiome.Template(TerraformBiome.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.125F).scale(0.05F)
            .temperature(1.2f).downfall(0.0f)
            .skyFogColor(colorToInt(0.8, 0.8, 0.6))
            .foliageColor(10387789)
            .grassColor(9470285)
            .waterColor(6388580)
            .waterFogColor(2302743)
            .addStructureFeatures(
                StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT)
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
            .addCustomFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.RANDOM_SELECTOR
                    .configure(
                        RandomFeatureConfig(
                            mutableListOf(
                                JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_BIRCH_TREE_CONFIG).withChance(0.2f)
                            ) as List<RandomFeatureEntry<*>>, JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_TREE_CONFIG))
                    )
                    .createDecoratedFeature(
                        Decorator.COUNT_EXTRA_HEIGHTMAP
                            .configure(CountExtraChanceDecoratorConfig(0, 0.2F, 1))
                    )
            )
            .addDefaultMonsterSpawnEntries()
            .addSpawnEntry(Biome.SpawnEntry(EntityType.HUSK, 200, 4, 6))
        )

        WASTELAND = template.builder()
            .addWastelandSurfacePatches()
            .build()

        WASTELAND_RIVER = template.builder()
            .addWastelandSurfacePatches()
            .category(Biome.Category.RIVER)
            .depth(-0.5F).scale(0.0F)
            .temperature(1.0F).downfall(0.0F)
            .build()

        WASTELAND_SHORE = template.builder()
            .category(Biome.Category.BEACH)
            .depth(0.0F).scale(0.025F)
            .temperature(1.0F).downfall(0.0f)
            .addCustomFeature(
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

        WASTELAND_EDGE = template.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_EDGE_CONFIG)
            .addTreeFeature(
                Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG),
                3
            )
            .addTreeFeature(
                Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG),
                3
            )
            .addCustomFeature(
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
            .addCustomFeature(
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
    }

    fun register() {
        if (BiomesConfig.Wasteland.enabled) {
            JourniaBiomes.register("wasteland", WASTELAND)
            JourniaBiomes.register("wasteland_river", WASTELAND_RIVER)
            JourniaBiomes.register("wasteland_shore", WASTELAND_SHORE)
            JourniaBiomes.register("wasteland_edge", WASTELAND_EDGE)

            OverworldBiomes.addContinentalBiome(WASTELAND, OverworldClimate.DRY, BiomesConfig.Wasteland.weight)
            OverworldBiomes.setRiverBiome(WASTELAND, WASTELAND_RIVER)
            OverworldBiomes.addShoreBiome(WASTELAND, WASTELAND_SHORE, 1.0)
            OverworldBiomes.addEdgeBiome(WASTELAND, WASTELAND_EDGE, 1.0)
        }
    }

    private fun TerraformBiome.Builder.addWastelandSurfacePatches(): TerraformBiome.Builder {
        return this.addCustomFeature(
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
        ).addCustomFeature(
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