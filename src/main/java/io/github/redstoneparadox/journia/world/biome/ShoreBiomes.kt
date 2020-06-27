package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object ShoreBiomes {
    val GRAVEL_BEACH: Biome

    init {
        val template = TerraformBiome.Template(
            TerraformBiome.builder()
                .category(Biome.Category.BEACH)
                .depth(0.0f).scale(0.125f)
                .waterFogColor(329011)
                .waterColor(4159204)
                .precipitation(Biome.Precipitation.RAIN)
        )

        GRAVEL_BEACH = template.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRAVEL_CONFIG)
            .temperature(0.25F)
            .downfall(0.8F)
            .build()
    }

    fun register() {
        JourniaBiomes.register("gravel_beach", GRAVEL_BEACH)
    }
}