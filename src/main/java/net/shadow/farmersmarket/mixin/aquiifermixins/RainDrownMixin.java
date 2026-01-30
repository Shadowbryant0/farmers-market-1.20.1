package net.shadow.farmersmarket.mixin.aquiifermixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.world.World;
import net.shadow.farmersmarket.effects.FmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class RainDrownMixin extends Entity {
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    public RainDrownMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"), method = "baseTick")
	private boolean init(boolean original) {
        if(this.hasStatusEffect(FmEffects.SUFFICATION) && this.isWet()) {
            return true;
        }
        return original;
    }
}