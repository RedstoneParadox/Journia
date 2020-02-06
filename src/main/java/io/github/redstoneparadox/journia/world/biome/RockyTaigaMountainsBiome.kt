package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.Blocks
import net.minecraft.class_4763
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.*

class RockyTaigaMountainsBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.PENTA, JourniaSurfaceBuilders.ROCKY_TAIGA_CONFIG)
        .precipitation(Precipitation.RAIN)
        .category(Category.TAIGA)
        .depth(1.0F).scale(1.3F)
        .temperature(0.25F)
        .downfall(0.8F)
        .method_24379(
            class_4763.class_4764()
                .method_24392(12638463)
                .method_24395(4159204)
                .method_24397(329011)
                .method_24391())
        .parent("null")
) {
    init {
        addStructureFeature(Feature.PILLAGER_OUTPOST.configure(FeatureConfig.DEFAULT))
        addStructureFeature(Feature.MINESHAFT.configure(MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)))
        addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT))
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addLargeFerns(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addDefaultFlowers(this)
        DefaultBiomeFeatures.addTaigaGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addSweetBerryBushes(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addPineTrees()
        addMineables()
        addBoulders()
        addSurfacePatches()

        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.SHEEP, 12, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.PIG, 10, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.COW, 8, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.WOLF, 8, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.RABBIT, 4, 2, 3))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.FOX, 8, 2, 4))
        addSpawn(EntityCategory.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
    }

    private fun addPineTrees() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.NORMAL_TREE.configure(
                            DefaultBiomeFeatures.PINE_TREE_CONFIG
                        ).withChance(0.33333334f)
                    ), Feature.NORMAL_TREE.configure(
                        DefaultBiomeFeatures.SPRUCE_TREE_CONFIG
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        8,
                        0.1f,
                        1
                    )
                )
            )
        )
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of(),
                    JourniaFeatures.PINE_TREE.configure(JourniaFeatures.PINE_TREE_CONFIG)
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(8, 0.1f, 1)
                )
            )
        )
    }

    private fun addMineables() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.DIRT.defaultState,
                    33
                )
            ).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 256))
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.GRAVEL.defaultState,
                    33
                )
            ).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(8, 0, 0, 256))
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.ANDESITE.defaultState,
                    33
                )
            ).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(10, 0, 0, 80))
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.GRANITE.defaultState,
                    33
                )
            ).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(8, 0, 0, 56))
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.DIORITE.defaultState,
                    33
                )
            ).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(8, 0, 0, 56))
            )
        )
    }

    private fun addSurfacePatches() {
        addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.STONE.defaultState,
                    2,
                    SurfacePatchFeatureConfig.Target.GRASS
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.COARSE_DIRT.defaultState,
                    1,
                    SurfacePatchFeatureConfig.Target.STONE
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
    }

    private fun addBoulders() {
        addFeature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            Feature.FOREST_ROCK.configure(
                BoulderFeatureConfig(
                    Blocks.ANDESITE.defaultState,
                    0
                )
            ).createDecoratedFeature(
                Decorator.FOREST_ROCK.configure(
                    CountDecoratorConfig(3)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            Feature.FOREST_ROCK.configure(
                BoulderFeatureConfig(
                    Blocks.COBBLESTONE.defaultState,
                    0
                )
            ).createDecoratedFeature(
                Decorator.FOREST_ROCK.configure(
                    CountDecoratorConfig(3)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            Feature.FOREST_ROCK.configure(
                BoulderFeatureConfig(
                    Blocks.MOSSY_COBBLESTONE.defaultState,
                    0
                )
            ).createDecoratedFeature(
                Decorator.FOREST_ROCK.configure(
                    CountDecoratorConfig(3)
                )
            )
        )
    }
}