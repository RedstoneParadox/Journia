package io.github.redstoneparadox.journia.mixin.world.gen.trunk;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

@Mixin(TrunkPlacerType.class)
public interface AccessorTrunkPlacerType {

    @Invoker
    static <T extends TrunkPlacer> TrunkPlacerType<T> callRegister(String id, Function<Dynamic<?>, T> deserializer) {
        throw new AssertionError("Mixin dummy");
    }
}
