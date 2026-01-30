package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.shadow.farmersmarket.effects.FmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)

    public class AntiWaterBreathing {
        @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/entity/effect/StatusEffectUtil;hasWaterBreathing(Lnet/minecraft/entity/LivingEntity;)Z"), method = "hasWaterBreathing", cancellable = true)
        private static void init(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
            if(entity.hasStatusEffect(FmEffects.SUFFICATION)) {
                cir.setReturnValue(false);
            }
            else{
                cir.cancel();
            }
        }
    }

