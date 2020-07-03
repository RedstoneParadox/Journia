package io.github.redstoneparadox.journia.util

import com.terraformersmc.terraform.biome.builder.TerraformBiome

fun TerraformBiome.Builder.skyFogColor(color: Int): TerraformBiome.Builder {
    return this.fogColor(color) as TerraformBiome.Builder
}