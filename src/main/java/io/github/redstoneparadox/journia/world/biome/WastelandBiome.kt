package io.github.redstoneparadox.journia.world.biome

import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.EntityType
import net.minecraft.particle.ParticleTypes
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.BiomeParticleConfig
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.StrongholdFeature
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import java.util.*

// Water Color: 6388580
// Water Fog Color: 2302743
class WastelandBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.DESERT)
        .depth(0.125F).scale(0.05F)
        .temperature(1.2f)
        .downfall(0.0f)
        .effects(
            BiomeEffects.Builder()
                .fogColor(colorToInt(0.8, 0.8, 0.6))
                .waterColor(6388580)
                .waterFogColor(2302743)
                .particleConfig(BiomeParticleConfig(ParticleTypes.ASH, 0.00625f))
                .build())
        .parent("null")
) {

    init {
        addStructureFeature(StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addBadlandsGrass(this)
        DefaultBiomeFeatures.addFossils(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        JourniaFeatures.addWastelandTrees(this)
        JourniaFeatures.addWastelandSurfacePatches(this)

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

    @Environment(EnvType.CLIENT)
    override fun getFoliageColor(): Int {
        return 10387789
    }

    @Environment(EnvType.CLIENT)
    override fun getGrassColorAt(x: Double, z: Double): Int {
        return 9470285
    }
}