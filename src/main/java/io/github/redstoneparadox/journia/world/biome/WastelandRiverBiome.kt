package io.github.redstoneparadox.journia.world.biome

import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.class_4763
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.MineshaftFeature
import net.minecraft.world.gen.feature.MineshaftFeatureConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class WastelandRiverBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.RIVER)
        .depth(-0.5F)
        .scale(0.0F)
        .temperature(1.0F)
        .downfall(0.0F)
        .method_24379(
            class_4763.class_4764()
                .method_24392(colorToInt(0.8, 0.8, 0.6))
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

        JourniaFeatures.addWastelandSurfacePatches(this)
        JourniaFeatures.addWastelandTrees(this)

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
}