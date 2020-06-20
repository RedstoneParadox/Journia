package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.foliage.CubenFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.foliage.PineFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.trunk.CubenTrunkPlacer
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer

object JourniaFeatures {
    private val PINE_LOG: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    val PINE_TREE_CONFIG = pineTreeConfig(PINE_LOG, PINE_LEAVES, 8, 2, 1)
    val DEAD_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.OAK_LOG.defaultState, 4, 2)
    val DEAD_BIRCH_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.BIRCH_LOG.defaultState, 4, 2)

    val PINE_TREE: TreeFeature = TreeFeature(TreeFeatureConfig.CODEC)
    val DEAD_TREE = DeadTreeFeature()
    val SURFACE_PATCH = SurfacePatchFeature()
    val NEW_SURFACE_PATCH = NewSurfacePatchFeature()
    val BLOCK_BANDS = BlockBandsFeature()

    fun registerAll() {
        register("pine_tree", PINE_TREE)
        register("new_surface_patch", NEW_SURFACE_PATCH)
        register("block_bands", BLOCK_BANDS)

        // Feature.STRUCTURES.put("Fort", FortFeature.FORT_FEATURE)

        /*
        FortFeature
        FortFeature.FortStructureStart
        FortFeature.FORT_STRUCTURE_FEATURE
        FortFeature.FORT_PIECE
        */
        /*
        Registry.BIOME.forEach {
            it.addFeature(GenerationStep.Feature.RAW_GENERATION, FortFeature.FORT_FEATURE.configure(DefaultFeatureConfig()).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)))
            it.addStructureFeature(FortFeature.FORT_FEATURE.configure(DefaultFeatureConfig()))
        }
        */

        DungeonFeature
        DungeonFeature.DungeonStructureStart
        DungeonFeature.NEW_DUNGEON_STRUCTURE_FEATURE
        DungeonFeature.NEW_DUNGEON_PIECE

        StructureFeature.STRUCTURES["New Dungeon"] = DungeonFeature.NEW_DUNGEON_STRUCTURE_FEATURE

        /*
        Registry.BIOME.forEach {
            it.addFeature(GenerationStep.Feature.RAW_GENERATION, DungeonFeature.NEW_DUNGEON_FEATURE.configure(DefaultFeatureConfig()).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)))
            it.addStructureFeature(DungeonFeature.NEW_DUNGEON_FEATURE.configure(DefaultFeatureConfig()))
        }
        */
    }

    private fun register(id: String, feature: Feature<*>) {
        Registry.register(Registry.FEATURE, "journia:$id", feature)
    }

    fun addWastelandSurfacePatches(biome: Biome) {
        biome.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.COARSE_DIRT.defaultState,
                    1,
                    SurfacePatchFeatureConfig.Target.WASTELAND
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
        biome.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.RED_SAND.defaultState,
                    1,
                    SurfacePatchFeatureConfig.Target.WASTELAND
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
        biome.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.COARSE_DIRT.defaultState,
                    2,
                    SurfacePatchFeatureConfig.Target.WASTELAND,
                    0.2
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(4)
                )
            )
        )
        biome.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.RED_SAND.defaultState,
                    2,
                    SurfacePatchFeatureConfig.Target.WASTELAND,
                    0.2
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(4)
                )
            )
        )
    }

    fun addWastelandTrees(biome: Biome) {
        biome.addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR
                .configure(RandomFeatureConfig(mutableListOf(), Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)))
                .createDecoratedFeature(
                    Decorator.COUNT_EXTRA_HEIGHTMAP
                        .configure(CountExtraChanceDecoratorConfig(0, 0.03F, 1))
                )
        )
        biome.addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR
                .configure(
                    RandomFeatureConfig(
                        mutableListOf(
                            DEAD_TREE.configure(DEAD_BIRCH_TREE_CONFIG).withChance(0.2f)
                        ) as List<RandomFeatureEntry<*>>, DEAD_TREE.configure(DEAD_TREE_CONFIG))
                )
                .createDecoratedFeature(
                    Decorator.COUNT_EXTRA_HEIGHTMAP
                        .configure(CountExtraChanceDecoratorConfig(0, 0.2F, 1))
                )
        )
    }


    private fun pineTreeConfig(trunk: BlockState, leaves: BlockState, baseHeight: Int, randomHeight: Int, foliageHeight: Int): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(trunk),
            SimpleBlockStateProvider(leaves),
            PineFoliagePlacer(3, 0, 0, 0),
            StraightTrunkPlacer(baseHeight, randomHeight, 0),
            TwoLayersFeatureSize(2, 0, 2)
        )
            .build()
    }

    fun cubenTreeConfig(): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(JourniaBlocks.CUBEN_LOG.defaultState),
            SimpleBlockStateProvider(JourniaBlocks.CUBEN_LEAVES.defaultState.with(Properties.PERSISTENT, true)),
            CubenFoliagePlacer(3, 0, 0, 0),
            CubenTrunkPlacer(5, 10, 0),
            TwoLayersFeatureSize(3, 0, 3)
        )
            .build()
    }
}