package io.github.redstoneparadox.journia.world.biome

import io.github.redstoneparadox.journia.config.BiomesConfig
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes

object JourniaBiomes {
    val WASTELAND: Biome = WastelandBiome()
    val WASTELAND_RIVER: Biome = WastelandRiverBiome()
    val WASTELAND_SHORE: Biome = WastelandShoreBiome()
    val WASTELAND_EDGE: Biome = WastelandEdgeBiome()
    val SHATTERED_BADLANDS_PLATEAU: Biome = ShatteredBadlandsPlateauBiome()
    val JUNGLE_WETLANDS: Biome = JungleWetlandsBiome()
    val ROCKY_PLAINS: Biome = RockyPlainsBiome()

    fun registerAll() {
        RockyTaigaBiomes.register()
        CubenForestBiomes.register()
        // BiancoBiomes.register()
        BandedMountainsBiomes.register()


        if (BiomesConfig.Wasteland.enabled) {
            register("wasteland", WASTELAND)
            register("wasteland_river", WASTELAND_RIVER)
            register("wasteland_shore", WASTELAND_SHORE)
            register("wasteland_edge", WASTELAND_EDGE)
            continentalBiome(WASTELAND, OverworldClimate.DRY, BiomesConfig.Wasteland.weight)
            riverBiome(WASTELAND, WASTELAND_RIVER)
            shoreBiome(WASTELAND, WASTELAND_SHORE, 1.0)
            edgeBiome(WASTELAND, WASTELAND_EDGE, 1.0)
        }
        if (BiomesConfig.ShatteredBadlandsPlateau.enabled) {
            register("shattered_badlands_plateau", SHATTERED_BADLANDS_PLATEAU)
            variantBiome(Biomes.BADLANDS_PLATEAU, SHATTERED_BADLANDS_PLATEAU, BiomesConfig.ShatteredBadlandsPlateau.chance, OverworldClimate.DRY)
        }
        if (BiomesConfig.JungleWetlands.enabled) {
            register("jungle_wetlands", JUNGLE_WETLANDS)
            variantBiome(Biomes.JUNGLE, JUNGLE_WETLANDS, BiomesConfig.JungleWetlands.chance)
        }
        if (BiomesConfig.RockyPlains.enabled) {
            register("rocky_plains", ROCKY_PLAINS)
            continentalBiome(ROCKY_PLAINS, OverworldClimate.COOL, BiomesConfig.RockyPlains.weight)
        }

        // register("salt_flats", SaltFlatsBiomes.SALT_FLATS)
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