package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BackgroundRenderer.class)
public class LavaFogMixin {
	@WrapOperation(at = @At(ordinal = -1, value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isSpectator()Z"), method = "applyFog")
	private static boolean init(Entity instance, Operation<Boolean> original) {
        if(instance instanceof LivingEntity living) {
            if (FMEnchantCheck.getLavaWader(living) > 0)
                return true;
        }
        return original.call(instance);
    }
    //mhm, i prob will// shut, yt autoplay, i could play worse shit// i have a song prepared
}