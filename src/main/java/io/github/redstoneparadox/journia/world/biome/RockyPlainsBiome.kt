package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.RockFormationFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*

class RockyPlainsBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.PENTA, JourniaSurfaceBuilders.ROCKY_TAIGA_CONFIG)
        .precipitation(Precipitation.RAIN)
        .category(Category.PLAINS)
        .depth(0.125F)
        .scale(0.05F)
        .temperature(0.25F)
        .downfall(0.4F)
        .effects(
            (BiomeEffects.Builder())
                .fogColor(12638463)
                .waterColor(4159204)
                .waterFogColor(329011)
            .build()
        ).parent("null").noises(
            ImmutableList.of(MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 1.0F))
        )
) {
    init {
        addStructureFeature(
            VillageFeature.VILLAGE.configure(
                StructurePoolFeatureConfig(Identifier("village/taiga/town_centers"), 6)
            )
        )
        addStructureFeature(
            PillagerOutpostFeature.PILLAGER_OUTPOST.configure(
                FeatureConfig.DEFAULT
            )
        )
        addStructureFeature(
            MineshaftFeature.MINESHAFT.configure(
                MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)
            )
        )
        addStructureFeature(
            StrongholdFeature.STRONGHOLD.configure(
                FeatureConfig.DEFAULT
            )
        )
        DefaultBiomeFeatures.addLandCarvers(this)
        // DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addPlainsTallGrass(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        addOtherFeatures()
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addMineables()
        addSurfacePatches()
        addBoulders()

        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 12, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 10, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.COW, 8, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.HORSE, 5, 2, 6))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.DONKEY, 1, 1, 3))
        addSpawn(SpawnGroup.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
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
                ForestRockFeatureConfig(
                    Blocks.COBBLESTONE.defaultState,
                    2
                )
            ).createDecoratedFeature(
                Decorator.FOREST_ROCK.configure(
                    CountDecoratorConfig(1)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            Feature.FOREST_ROCK.configure(
                ForestRockFeatureConfig(
                    Blocks.MOSSY_COBBLESTONE.defaultState,
                    2
                )
            ).createDecoratedFeature(
                Decorator.FOREST_ROCK.configure(
                    CountDecoratorConfig(1)
                )
            )
        )
    }

    private fun addOtherFeatures() {
       addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.TREE.configure(
                            JourniaFeatures.PINE_TREE_CONFIG
                        ).withChance(0.33333334f)
                    ),
                    Feature.TREE.configure(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG)
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(0, 0.05f, 1)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.FLOWER.configure(DefaultBiomeFeatures.PLAINS_FLOWER_CONFIG).createDecoratedFeature(
                Decorator.NOISE_HEIGHTMAP_32.configure(
                    NoiseHeightmapDecoratorConfig(-0.8, 15, 4)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(
                Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(
                    NoiseHeightmapDecoratorConfig(-0.8, 5, 10)
                )
            )
        )
    }
}