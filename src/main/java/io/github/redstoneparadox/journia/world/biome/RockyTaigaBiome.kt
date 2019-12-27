package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.gen.feature.JourniaTreeFeatureConfig
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.state.property.Properties
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.*

class RockyTaigaBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.PENTA, JourniaSurfaceBuilders.ROCKY_TAIGA_CONFIG)
        .precipitation(Precipitation.RAIN)
        .category(Category.TAIGA)
        .depth(0.2F).scale(0.2F)
        .temperature(0.25F)
        .downfall(0.8F)
        .waterColor(4159204)
        .waterFogColor(329011)
        .parent("null")
) {

    private val PINE_TRUNK: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    init {
        addStructureFeature(Feature.VILLAGE.configure(VillageFeatureConfig("village/taiga/town_centers", 6)))
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
        DefaultBiomeFeatures.addTaigaTrees(this)
        DefaultBiomeFeatures.addDefaultFlowers(this)
        DefaultBiomeFeatures.addTaigaGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addSweetBerryBushes(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addPineTrees()
        addMineables()

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

    fun addPineTrees() {
        this.addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                    ),
                    JourniaFeatures.PINE_TREE.configure(JourniaTreeFeatureConfig(PINE_TRUNK, PINE_LEAVES, 8, 10))
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(10, 0.1f, 1)
                )
            )
        )
    }

    fun addMineables() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                OreFeatureConfig(
                    OreFeatureConfig.Target.NATURAL_STONE,
                    Blocks.COARSE_DIRT.defaultState,
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
    }

    override fun getGrassColorAt(x: Double, z: Double): Int {
        return 0x4e5837
    }
}