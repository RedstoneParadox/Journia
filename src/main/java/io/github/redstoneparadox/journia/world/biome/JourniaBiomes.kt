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
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object JourniaBiomes {
    lateinit var BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var MODIFIED_BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var BANDED_MOUNTAINS_RIVER: RegistryKey<Biome>
    lateinit var BANDED_SHORE: RegistryKey<Biome>


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

    fun continentalBiome(biome: Biome, climate: OverworldClimate, weight: Double) {
        OverworldBiomes.addContinentalBiome(biome, climate, weight)
    }

    fun hillsBiome(parent: Biome, hills: Biome, weight: Double) {
        OverworldBiomes.addHillsBiome(parent, hills, weight)
    }
}