package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.ProbabilityConfig
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.feature.*

// Water: 4159204
// Water Fog: 329011
class JungleWetlandsBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.WETLANDS, JourniaSurfaceBuilders.WETLANDS_CONFIG)
        .precipitation(Precipitation.RAIN).category(Category.JUNGLE)
        .depth(-0.2f).scale(0.0f)
        .temperature(0.8f).downfall(0.95f)
        .effects(BiomeEffects.Builder()
            .fogColor(12638463)
            .waterColor(4159204)
            .waterFogColor(329011)
            .build())
        .parent("null")
) {

    init {
        addStructureFeature(
            JungleTempleFeature.JUNGLE_PYRAMID.configure(
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
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addBamboo(this)
        DefaultBiomeFeatures.addExtraDefaultFlowers(this)
        DefaultBiomeFeatures.addJungleGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addJungleVegetation(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.SEAGRASS.configure(
                SeagrassFeatureConfig(
                    256,
                    0.6
                )
            ).createDecoratedFeature(
                Decorator.TOP_SOLID_HEIGHTMAP.configure(
                    DecoratorConfig.DEFAULT
                )
            )
        )
        addJungleWetlandsTrees()
        addJungleWetlandsDisks()

        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 6, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 5, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.CHICKEN, 5, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.COW, 4, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PARROT, 40, 4, 8))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PANDA, 1, 1, 2))
        addSpawn(SpawnGroup.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.OCELOT, 2, 1, 3))
        addSpawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.TROPICAL_FISH, 5, 6, 12))
        addSpawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.TROPICAL_FISH, 10, 2, 5))

    }

    fun addBamboo() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION, Feature.BAMBOO.configure(
                ProbabilityConfig(0.0f)
            ).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(CountDecoratorConfig(64)))
        )
    }

    fun addJungleWetlandsDisks() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.DISK.configure(
                DiskFeatureConfig(
                    Blocks.CLAY.defaultState,
                    4,
                    1,
                    arrayListOf(
                        Blocks.DIRT.defaultState, Blocks.CLAY.defaultState, JourniaBlocks.MUD.defaultState
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_TOP_SOLID.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.DISK.configure(
                DiskFeatureConfig(
                    JourniaBlocks.MUD.defaultState,
                    8,
                    2,
                    Lists.newArrayList(
                        Blocks.GRASS_BLOCK.defaultState, Blocks.DIRT.defaultState
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_TOP_SOLID.configure(
                    CountDecoratorConfig(8)
                )
            )
        )
    }

    private fun addJungleWetlandsTrees() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withChance(0.1f),
                        Feature.TREE.configure(
                            DefaultBiomeFeatures.JUNGLE_GROUND_BUSH_CONFIG
                        ).withChance(0.5f)
                    ), Feature.TREE.configure(
                        DefaultBiomeFeatures.JUNGLE_TREE_CONFIG
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        20,
                        0.1f,
                        1
                    )
                )
            )
        )
    }
}