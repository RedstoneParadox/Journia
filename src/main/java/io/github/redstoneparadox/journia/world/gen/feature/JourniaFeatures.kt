package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.foliage.GroenwoodFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.foliage.PineFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.trunk.GroenwoodTrunkPlacer
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import java.util.function.Function

object JourniaFeatures {
    private val PINE_LOG: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    val PINE_TREE_CONFIG = pineTreeConfig(PINE_LOG, PINE_LEAVES, 8, 2, 1)
    val DEAD_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.OAK_LOG.defaultState, 4, 2)
    val DEAD_BIRCH_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.BIRCH_LOG.defaultState, 4, 2)

    val PINE_TREE: PineTreeFeature = PineTreeFeature(Function { TreeFeatureConfig.deserialize(it) })
    val DEAD_TREE = DeadTreeFeature(Function { DeadTreeFeatureConfig.deserialize(it) })
    val SURFACE_PATCH = SurfacePatchFeature(Function { SurfacePatchFeatureConfig.deserialize(it) })

    fun registerAll() {
        register("pine_tree", PINE_TREE)

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

        Feature.STRUCTURES.put("New Dungeon", DungeonFeature.NEW_DUNGEON_FEATURE)

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
            .baseHeight(baseHeight)
            .build()
    }

    fun groenwoodTreeConfig(): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(Blocks.OAK_LOG.defaultState),
            SimpleBlockStateProvider(Blocks.OAK_LEAVES.defaultState.with(Properties.PERSISTENT, true)),
            GroenwoodFoliagePlacer(3, 0, 0, 0),
            GroenwoodTrunkPlacer(5, 10, 0),
            TwoLayersFeatureSize(3, 0, 3)
        )
            .baseHeight(5)
            .build()
    }

    /*
        private fun treeConfig(trunk: BlockState, leaves: BlockState, minHeight: Int, maxHeight: Int, foliageHeight: Int): BranchedTreeFeatureConfig {
        return BranchedTreeFeatureConfig.Builder(
            SimpleStateProvider(trunk),
            SimpleStateProvider(leaves),
            BlobFoliagePlacer(0, 0)
        )
            .baseHeight(minHeight)
            .trunkHeight(minHeight)
            .heightRandA(maxHeight)
            .trunkHeightRandom(maxHeight)
            .foliageHeight(foliageHeight)
            .build()
    }
    */
}