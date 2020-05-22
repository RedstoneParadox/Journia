package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object SaltFlatsBiomes {

    val SALT_FLATS: Biome

    init {
        val template = TerraformBiome.Template(TerraformBiome.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.SALT_FLATS_CONFIG)
            .temperature(3.0f)
            .downfall(0.0f)
            .precipitation(Biome.Precipitation.NONE)
            .waterColor(0x32a598)
            .waterFogColor(329011)
            .category(Biome.Category.DESERT)
        )

        SALT_FLATS = template.builder()
            .depth(0.1f)
            .scale(0.0f)
            .build()
    }

    fun createEffects(): BiomeEffects {
        return BiomeEffects.Builder()
            .waterColor(0x32a598)
            .waterFogColor(329011)
            .fogColor(12638463)
            .build()
    }
}