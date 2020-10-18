package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object ShoreBiomes {
    fun register() {
        val template = TerraformBiomeBuilder.create()
            .category(Biome.Category.BEACH)
            .depth(0.0f).scale(0.125f)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
            )
            .precipitation(Biome.Precipitation.RAIN)

        JourniaBiomes.GRAVEL_BEACH = JourniaBiomes.register("gravel_beach", TerraformBiomeBuilder.create(template)
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRAVEL_CONFIG)
            .temperature(0.25F)
            .downfall(0.8F)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .skyColor(JourniaBiomes.getSkyColor(0.25f))
            )
            .build()
        )
    }
}