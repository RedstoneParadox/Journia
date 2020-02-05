package io.github.redstoneparadox.journia.world.biome

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.SurfacePatchFeatureConfig
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
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
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureConfig
import net.minecraft.world.gen.feature.RandomFeatureEntry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class WastelandEdgeBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_EDGE_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.DESERT)
        .depth(0.125F).scale(0.05F)
        .temperature(1.0F).downfall(0.0F)
        .method_24379(
            class_4763.class_4764()
                .method_24392(colorToInt(1.0, 1.0, 0.75))
                .method_24395(6388580)
                .method_24397(2302743)
                .method_24391())
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

        JourniaFeatures.addWastelandTrees(this)
        addWastelandEdgeTrees()
        addWastelandEdgeSurfacePatches()

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

    private fun addWastelandEdgeSurfacePatches() {
        addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    JourniaBlocks.CRACKED_GROUND.defaultState,
                    2,
                    SurfacePatchFeatureConfig.Target.DIRT,
                    0.4
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
                    2,
                    SurfacePatchFeatureConfig.Target.DIRT,
                    0.2
                )
            ).createDecoratedFeature(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountDecoratorConfig(4)
                )
            )
        )
    }

    fun addWastelandEdgeTrees() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.NORMAL_TREE.configure(
                            DefaultBiomeFeatures.BIRCH_TREE_CONFIG
                        ).withChance(0.2f),
                        Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withChance(0.1f)
                    ), Feature.NORMAL_TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        5,
                        0.1f,
                        1
                    )
                )
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