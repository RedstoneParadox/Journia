package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object BiancoBiomes {

    val BIANCO: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.BIANCO_CONFIG)
                .temperature(3.0f)
                .downfall(0.0f)
                .precipitation(Biome.Precipitation.NONE)
                .waterColor(0x47d1d1)
                .waterFogColor(0x2eb8b8)
                .category(Biome.Category.DESERT)
        )

        BIANCO = template.builder()
            .depth(0.02f)
            .scale(0.5f)
            .build()
    }

    fun register() {
        if (true) {
            JourniaBiomes.register("bianco", BIANCO)
        }
    }
}