package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object SaltFlatsBiomes {

    val SALT_FLATS: Biome

    init {
        val template = TerraformBiomeBuilder.create()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.SALT_FLATS_CONFIG)
            .temperature(3.0f)
            .downfall(0.0f)
            .precipitation(Biome.Precipitation.NONE)
            .effects(createEffects())
            .category(Biome.Category.DESERT)

        SALT_FLATS = TerraformBiomeBuilder.create(template)
            .depth(0.01f)
            .scale(-0.075f)
            .build()
    }

    private fun createEffects(): BiomeEffects.Builder {
        return BiomeEffects.Builder()
            .waterColor(0x32a598)
            .waterFogColor(329011)
            .fogColor(12638463)
    }
}