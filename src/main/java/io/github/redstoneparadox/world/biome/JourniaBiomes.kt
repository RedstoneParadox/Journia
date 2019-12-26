package io.github.redstoneparadox.world.biome

import net.fabricmc.fabric.api.biomes.v1.FabricBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes

object JourniaBiomes {
    val WASTELAND = WastelandBiome()
    val ROCKY_TAIGA = RockyTaigaBiome()

    fun registerAll() {
        register("wasteland", WASTELAND)
        continentalBiome(WASTELAND, OverworldClimate.DRY, 0.5)
        register("rocky_taiga", ROCKY_TAIGA)
        continentalBiome(ROCKY_TAIGA, OverworldClimate.COOL, 1.0)
        shoreBiome(ROCKY_TAIGA, Biomes.STONE_SHORE, 1.0)
    }

    private fun register(id: String, biome: Biome) {
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