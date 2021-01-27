package io.github.redstoneparadox.journia.shape

import com.terraformersmc.terraform.shapes.api.Shape
import java.util.*

fun interface ShapeProvider<C: ShapeProviderConfig> {
    fun get(random: Random, config: C): Shape
}