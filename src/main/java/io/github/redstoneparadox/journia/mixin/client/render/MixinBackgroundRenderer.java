package io.github.redstoneparadox.journia.mixin.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BackgroundRenderer.class)
public abstract class MixinBackgroundRenderer {

    private static Camera renderCam;
    private static ClientWorld renderWorld;

    @Inject(method = "render", at = @At("HEAD"))
    private static void captureParams(Camera camera, float f, ClientWorld clientWorld, int i, float g, CallbackInfo ci) {
        renderCam = camera;
        renderWorld = clientWorld;
    }

    @ModifyVariable(method = "render", name = "vec3d2", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/world/ClientWorld;getFogColor(F)Lnet/minecraft/util/math/Vec3d;"))
    private static Vec3d modifyColor(Vec3d vec3d2) {
        Vec3d color = vec3d2;
        if (renderWorld != null && renderCam != null && renderCam.getFocusedEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) renderCam.getFocusedEntity();
            Biome biome = renderWorld.getBiome(entity.getBlockPos());
            if (biome == JourniaBiomes.INSTANCE.getWASTELAND()) {
                color = new Vec3d(1.0, 1.0, 0.75);
            }
        }
        renderCam = null;
        renderWorld = null;
        return color;
    }

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;fogStart(F)V"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        if (camera.getFocusedEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) camera.getFocusedEntity();
            BlockPos pos = entity.getBlockPos();
            Biome biome = entity.world.getBiome(pos);
            if (biome == JourniaBiomes.INSTANCE.getWASTELAND()) {
                RenderSystem.fogDensity(2.0F);
                RenderSystem.fogStart(4.0F);
                RenderSystem.fogEnd(viewDistance);
                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
                RenderSystem.setupNvFogDistance();
                ci.cancel();
            }
        }
    }
}
