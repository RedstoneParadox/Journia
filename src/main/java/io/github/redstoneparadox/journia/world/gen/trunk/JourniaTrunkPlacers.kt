package io.github.redstoneparadox.journia.world.gen.trunk

import io.github.redstoneparadox.journia.mixin.world.gen.trunk.AccessorTrunkPlacerType
import net.minecraft.world.gen.trunk.TrunkPlacerType

object JourniaTrunkPlacers {
    val CUBEN_TRUNK_PLACER: TrunkPlacerType<CubenTrunkPlacer> = AccessorTrunkPlacerType.callRegister("journia:cuben_trunk", CubenTrunkPlacer.CODEC)
}