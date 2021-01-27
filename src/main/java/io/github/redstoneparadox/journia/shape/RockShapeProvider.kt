package io.github.redstoneparadox.journia.shape

import com.terraformersmc.terraform.shapes.api.Shape
import com.terraformersmc.terraform.shapes.impl.Shapes
import kotlin.random.Random

class RockShapeProvider: ShapeProvider<RockShapeProviderConfig> {
    override fun get(random: Random, config: RockShapeProviderConfig): Shape {
        val radius = random.nextInt(config.radiusRange.first, config.radiusRange.last + 1).toDouble()
        val height = random.nextInt(config.heightRange.first, config.heightRange.last + 1).toDouble()

        return Shapes.ellipsoid(radius, radius, height)
    }
}