package io.github.redstoneparadox.journia.world.biome

import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureEntry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class WastelandBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
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
        addSurfacePatches()

        addSpawn(EntityCategory.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SPIDER, 80, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE, 75, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SKELETON, 80, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.CREEPER, 80, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SLIME, 80, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.HUSK, 200, 4, 4))
    }

    private fun addSurfacePatches() {
        addFeature(
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
        addFeature(
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
        addFeature(
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
        addFeature(
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
                .configure(RandomFeatureConfig(
                    mutableListOf(
                        JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_BIRCH_TREE_CONFIG).withChance(0.2f)
                    ) as List<RandomFeatureEntry<*>>, JourniaFeatures.DEAD_TREE.configure(JourniaFeatures.DEAD_TREE_CONFIG)))
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