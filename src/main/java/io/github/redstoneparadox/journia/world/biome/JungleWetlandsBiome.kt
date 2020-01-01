package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class JungleWetlandsBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.SWAMP, SurfaceBuilder.GRASS_CONFIG)
        .precipitation(Precipitation.RAIN).category(Category.JUNGLE)
        .depth(-0.2f).scale(0.1f)
        .temperature(0.8f).downfall(0.95f)
        .waterColor(4159204).waterFogColor(329011)
        .parent("null")
) {

    init {
        addStructureFeature(
            Feature.JUNGLE_TEMPLE.configure(
                FeatureConfig.DEFAULT
            )
        )
        addStructureFeature(
            Feature.MINESHAFT.configure(
                MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)
            )
        )
        addStructureFeature(
            Feature.STRONGHOLD.configure(
                FeatureConfig.DEFAULT
            )
        )
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addBamboo(this)
        DefaultBiomeFeatures.addExtraDefaultFlowers(this)
        DefaultBiomeFeatures.addJungleGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addJungleVegetation(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addJungleWetlandsTrees()

        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.SHEEP, 12, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.PIG, 10, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.COW, 8, 4, 4))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.PARROT, 40, 1, 2))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.PANDA, 1, 1, 2))
        addSpawn(EntityCategory.CREATURE, SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
        addSpawn(EntityCategory.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.OCELOT, 2, 1, 3))
    }

    fun addJungleWetlandsTrees() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withChance(0.1f),
                        Feature.JUNGLE_GROUND_BUSH.configure(
                            DefaultBiomeFeatures.JUNGLE_GROUND_BUSH_CONFIG
                        ).withChance(0.5f)
                    ), Feature.NORMAL_TREE.configure(
                        DefaultBiomeFeatures.JUNGLE_TREE_CONFIG
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        50,
                        0.1f,
                        1
                    )
                )
            )
        )
    }
}