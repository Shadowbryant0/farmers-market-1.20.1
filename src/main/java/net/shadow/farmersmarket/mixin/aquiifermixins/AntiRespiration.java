package net.shadow.farmersmarket.mixin.aquiifermixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.shadow.farmersmarket.effects.FmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class AntiRespiration{

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getRespiration(Lnet/minecraft/entity/LivingEntity;)I"), method = "getNextAirUnderwater")
    private int init(int original) {
        if(this.hasStatusEffect(FmEffects.SUFFICATION)){
            return original/12;
        }
        return original;
    }

}