package io.github.redstoneparadox.journia.world.biome

import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome

object JourniaBiomes {
    lateinit var BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var MODIFIED_BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var BANDED_MOUNTAINS_RIVER: RegistryKey<Biome>
    lateinit var BANDED_SHORE: RegistryKey<Biome>

    lateinit var CUBEN_FOREST: RegistryKey<Biome>
    lateinit var CUBEN_FOREST_HILLS: RegistryKey<Biome>

    fun registerAll() {
        ShoreBiomes.register()
        RockyTaigaBiomes.register()
        CubenForestBiomes.register()
        BandedMountainsBiomes.register()
        WastelandBiomes.register()
    }

    fun register(name: String, biome: Biome): RegistryKey<Biome> {
        val id = Identifier("journia:$name")

        BuiltinRegistries.add(BuiltinRegistries.BIOME, id, biome)

        return RegistryKey.of(Registry.BIOME_KEY, id)
    }
}