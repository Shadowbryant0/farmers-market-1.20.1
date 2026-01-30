package net.shadow.farmersmarket.mixin.aquiifermixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.shadow.farmersmarket.effects.FmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class AntiDepthStrider {


    @Shadow
    public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getDepthStrider(Lnet/minecraft/entity/LivingEntity;)I"), method = "travel")
    private int init(int original) {
        if(this.hasStatusEffect(FmEffects.SUFFICATION)){
            return original/3;
        }
        return original;
    }
}
