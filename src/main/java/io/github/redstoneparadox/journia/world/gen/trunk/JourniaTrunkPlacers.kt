package io.github.redstoneparadox.journia.world.gen.trunk

import io.github.redstoneparadox.journia.mixin.world.gen.trunk.AccessorTrunkPlacerType
import net.minecraft.world.gen.trunk.TrunkPlacerType

object JourniaTrunkPlacers {
    val GROENWOOD_TRUNK_PLACER: TrunkPlacerType<GroenwoodTrunkPlacer> = AccessorTrunkPlacerType.callRegister("journia:groenwood_trunk") { GroenwoodTrunkPlacer(it) }
}