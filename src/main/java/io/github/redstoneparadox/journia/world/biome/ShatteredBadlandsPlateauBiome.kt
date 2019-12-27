package io.github.redstoneparadox.journia.world.biome

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class ShatteredBadlandsPlateauBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.BADLANDS, SurfaceBuilder.BADLANDS_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.MESA)
        .depth(1.05F).scale(1.2125001F)
        .temperature(2.0f)
        .downfall(0.0f)
        .waterColor(4159204).waterFogColor(329011)
        .parent("null")
) {

    init {
        addStructureFeature(
            Feature.MINESHAFT.configure(
                MineshaftFeatureConfig(0.004, MineshaftFeature.Type.MESA)
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
        DefaultBiomeFeatures.addBadlandsGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addBadlandsVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addExtraGoldOre()

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

    fun addExtraGoldOre() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GOLD_ORE.defaultState, 9)
            ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(32, 32, 32, 256)))
        )
    }
}