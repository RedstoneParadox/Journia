package io.github.redstoneparadox.journia.mixin.world.biome;

import com.google.common.collect.ImmutableList;
import io.github.redstoneparadox.journia.config.BiomesConfig;
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public abstract class MixinDefaultBiomeFeatures {
    @Shadow @Final public static TreeFeatureConfig PINE_TREE_CONFIG;
    @Shadow @Final public static TreeFeatureConfig SPRUCE_TREE_CONFIG;

    @Inject(method = "addTaigaTrees", at = @At("HEAD"), cancellable = true)
    private static void addTaigaTrees(Biome biome, CallbackInfo ci) {
        if (BiomesConfig.Vanilla.INSTANCE.getTaigaPineTrees()) {
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(Feature.TREE.configure(PINE_TREE_CONFIG).withChance(0.33333334F)), Feature.TREE.configure(SPRUCE_TREE_CONFIG))).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(8, 0.1F, 1))));
            biome.addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    Feature.RANDOM_SELECTOR.configure(
                            new RandomFeatureConfig(
                                    ImmutableList.of(),
                                    JourniaFeatures.INSTANCE.getPINE_TREE().configure(JourniaFeatures.INSTANCE.getPINE_TREE_CONFIG())
                            )
                    ).createDecoratedFeature(
                            Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                                    new CountExtraChanceDecoratorConfig(8, 0.1f, 1)
                            )
                    )
            );
            ci.cancel();
        }
    }
}
