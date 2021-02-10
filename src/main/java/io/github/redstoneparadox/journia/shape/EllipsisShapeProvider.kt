package io.github.redstoneparadox.journia.shape

import com.terraformersmc.terraform.shapes.api.Shape
import com.terraformersmc.terraform.shapes.impl.Shapes
import kotlin.random.Random

class EllipsisShapeProvider: ShapeProvider<EllipsisShapeProviderConfig> {
    override fun get(random: Random, config: EllipsisShapeProviderConfig): Shape {
        val width = random.nextInt(config.widthRange.first, config.widthRange.last)
        val height = random.nextInt(config.heightRange.first, config.heightRange.last)

        return Shapes.ellipse(width.toDouble(), height.toDouble())
    }
}