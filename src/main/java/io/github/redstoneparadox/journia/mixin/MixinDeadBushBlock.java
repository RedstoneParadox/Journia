package io.github.redstoneparadox.journia.mixin;

import io.github.redstoneparadox.journia.block.JourniaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DeadBushBlock.class)
public class MixinDeadBushBlock {
    @Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
    private void canPlaceOn(BlockState floor, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Block block = floor.getBlock();
        cir.setReturnValue(cir.getReturnValueZ() || block == JourniaBlocks.INSTANCE.getCRACKED_GROUND());
    }
}
