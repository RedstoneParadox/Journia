package io.github.redstoneparadox.journia.world.biome

import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureConfig

class WastelandBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.PENTA, JourniaSurfaceBuilders.WASTELAND_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.DESERT)
        .depth(0.125F).scale(0.05F)
        .temperature(1.2f)
        .downfall(0.0f)
        .waterColor(6388580).waterFogColor(2302743)
        .parent("null")
) {

    init {
        addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addBadlandsGrass(this)
        DefaultBiomeFeatures.addFossils(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addWastelandTrees()

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

    private fun addWastelandTrees() {
        addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR
                .configure(RandomFeatureConfig(mutableListOf(), Feature.NORMAL_TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)))
                .createDecoratedFeature(
                    Decorator.COUNT_EXTRA_HEIGHTMAP
                        .configure(CountExtraChanceDecoratorConfig(0, 0.05F, 1))
                )
        )
        addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR
                .configure(RandomFeatureConfig(mutableListOf(), JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_TREE_CONFIG)))
                .createDecoratedFeature(
                    Decorator.COUNT_EXTRA_HEIGHTMAP
                        .configure(CountExtraChanceDecoratorConfig(0, 0.3F, 1))
                )
        )
    }

    @Environment(EnvType.CLIENT)
    override fun getFoliageColor(): Int {
        return 10387789
    }

    @Environment(EnvType.CLIENT)
    override fun getGrassColorAt(x: Double, z: Double): Int {
        return 9470285
    }
}