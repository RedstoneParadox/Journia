package io.github.redstoneparadox.journia.mixin.world.gen.trunk;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrunkPlacerType.class)
public interface AccessorTrunkPlacerType {

    @Invoker
    static <T extends TrunkPlacer> TrunkPlacerType<T> callRegister(String id, Codec<T> codec) {
        throw new AssertionError("Mixin dummy");
    }
}
