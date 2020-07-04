package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.config.BiomesConfig
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.feature.RockFormationFeatureConfig
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object JourniaBiomes {
    private val PLAYGROUND: Biome = TerraformBiome.builder()
        .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
        .precipitation(Biome.Precipitation.RAIN)
        .category(Biome.Category.PLAINS)
        .depth(0.0F).scale(0.0F)
        .temperature(0.8F).downfall(0.4F)
        .waterColor(4159204).waterFogColor(329011)
        .addCustomFeature(
            GenerationStep.Feature.RAW_GENERATION,
            JourniaFeatures.ROCK_FORMATION.configure(
                RockFormationFeatureConfig(
                    3..8,
                    8..18,
                    3..6,
                    5..9
                )
            ).createDecoratedFeature(
                JourniaDecorators.RANDOM_HEIGHTMAP.configure(
                    ChanceDecoratorConfig(20)
                )
            )
        )
        .build()
    val SHATTERED_BADLANDS_PLATEAU: Biome = ShatteredBadlandsPlateauBiome()
    val JUNGLE_WETLANDS: Biome = JungleWetlandsBiome()

    fun registerAll() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            register("playground", PLAYGROUND)
        }

        ShoreBiomes.register()
        RockyTaigaBiomes.register()
        CubenForestBiomes.register()
        BandedMountainsBiomes.register()
        WastelandBiomes.register()


        if (BiomesConfig.ShatteredBadlandsPlateau.enabled) {
            register("shattered_badlands_plateau", SHATTERED_BADLANDS_PLATEAU)
            variantBiome(Biomes.BADLANDS_PLATEAU, SHATTERED_BADLANDS_PLATEAU, BiomesConfig.ShatteredBadlandsPlateau.chance, OverworldClimate.DRY)
        }
        if (BiomesConfig.JungleWetlands.enabled) {
            register("jungle_wetlands", JUNGLE_WETLANDS)
            variantBiome(Biomes.JUNGLE, JUNGLE_WETLANDS, BiomesConfig.JungleWetlands.chance)
        }
    }

    fun register(id: String, biome: Biome) {
        Registry.register(Registry.BIOME, "journia:$id", biome)
    }

    fun spawnBiome(biome: Biome) {
        FabricBiomes.addSpawnBiome(biome)
    }

    fun continentalBiome(biome: Biome, climate: OverworldClimate, weight: Double) {
        OverworldBiomes.addContinentalBiome(biome, climate, weight)
    }

    fun hillsBiome(parent: Biome, hills: Biome, weight: Double) {
        OverworldBiomes.addHillsBiome(parent, hills, weight)
    }

    fun shoreBiome(parent: Biome, shore: Biome, weight: Double) {
        OverworldBiomes.addShoreBiome(parent, shore, weight)
    }

    fun edgeBiome(parent: Biome, edge: Biome, weight: Double) {
        OverworldBiomes.addEdgeBiome(parent, edge, weight)
    }

    fun variantBiome(replaced: Biome, variant: Biome, chance: Double, vararg climates: OverworldClimate) {
        OverworldBiomes.addBiomeVariant(replaced, variant, chance, *climates)
    }

    fun riverBiome(parent: Biome, river: Biome) {
        OverworldBiomes.setRiverBiome(parent, river)
    }
}