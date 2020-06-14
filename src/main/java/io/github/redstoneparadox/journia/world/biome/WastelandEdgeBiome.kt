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
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class WastelandEdgeBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_EDGE_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.DESERT)
        .depth(0.125F).scale(0.05F)
        .temperature(1.0F).downfall(0.0F)
        .effects(
            BiomeEffects.Builder()
                .fogColor(colorToInt(0.8, 0.8, 0.6))
                .waterColor(6388580)
                .waterFogColor(2302743)
                .build())
        .parent("null")
) {
    init {
        addStructureFeature(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
        DefaultBiomeFeatures.addLandCarvers(this)
        //TODO: Figure out what this did
        // DefaultBiomeFeatures.addDefaultStructures(this)
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

        addSpawn(SpawnGroup.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SPIDER, 80, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE, 75, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SKELETON, 80, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.CREEPER, 80, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 80, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.HUSK, 200, 4, 4))
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
                        Feature.TREE.configure(
                            DefaultBiomeFeatures.BIRCH_TREE_CONFIG
                        ).withChance(0.2f),
                        Feature.TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withChance(0.1f)
                    ), Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)
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