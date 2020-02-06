package io.github.redstoneparadox.journia.mixin.world.dimension;

import io.github.redstoneparadox.journia.world.biome.JourniaBiomes;
import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandRiverBiome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OverworldDimension.class)
public abstract class MixinOverworldDimension extends Dimension {
    public MixinOverworldDimension(World world, DimensionType type, float f) {
        super(world, type, f);
    }

    @Inject(method = "isFogThick", at = @At("HEAD"), cancellable = true)
    private void isFogThick(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        Biome biome = world.getBiome(new BlockPos(x, 0, z));
        if (biome instanceof WastelandBiome || biome instanceof WastelandRiverBiome) {
            cir.setReturnValue(true);
        }
    }
}
