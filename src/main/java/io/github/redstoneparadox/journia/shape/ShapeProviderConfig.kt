package io.github.redstoneparadox.journia.shape

interface ShapeProviderConfig {
    companion object {
        val DEFAULT: ShapeProviderConfig = object : ShapeProviderConfig {}
    }
}