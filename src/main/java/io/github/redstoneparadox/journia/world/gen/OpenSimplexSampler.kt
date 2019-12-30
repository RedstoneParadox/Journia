package io.github.redstoneparadox.journia.world.gen

class OpenSimplexSampler(private val xStrech: Double = 1.0, private val yStrech: Double = 1.0, private val zStrech: Double = 1.0, private val noiseMultiplier: Double = 1.0) {

    private var noise = OpenSimplexNoise(0)
    private var seed: Long = 0;

    fun setSeed(seed: Long) {
        if (seed != this.seed) {
            this.seed = seed
            noise = OpenSimplexNoise(seed)
        }
    }

    fun eval(x: Int, z: Int): Double = noise.eval(x.toDouble()/xStrech, z.toDouble()/zStrech) * noiseMultiplier

    fun eval(x: Int, y: Int, z: Int): Double = noise.eval(x.toDouble()/xStrech,y.toDouble()/yStrech, z.toDouble()/zStrech) * noiseMultiplier
}