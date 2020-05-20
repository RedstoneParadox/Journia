package io.github.redstoneparadox.journia.mixin.world.gen.feature;

import io.github.redstoneparadox.journia.world.gen.feature.JourniaTreeFeature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TreeFeature.class)
public abstract class MixinTreeFeature {

    @Inject(method = "generate(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At("HEAD"), cancellable = true)
    private void generate(ServerWorldAccess serverWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, TreeFeatureConfig treeFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        TreeFeature self = (TreeFeature)(Object)this;

        if (self instanceof JourniaTreeFeature) {
            ((JourniaTreeFeature) self).gen(serverWorldAccess, structureAccessor, chunkGenerator, random, blockPos, treeFeatureConfig);
        }
    }
}
