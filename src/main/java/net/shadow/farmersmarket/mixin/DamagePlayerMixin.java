package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Armor.AdaptabilityComponent;
import net.shadow.farmersmarket.components.BlazeIdolComponent;
import net.shadow.farmersmarket.components.Weapons.ParryComponent;
import net.shadow.farmersmarket.effects.FmEffects;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class DamagePlayerMixin {


    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Inject(
            at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void Parry(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
//        if(ParryComponent.PARRY >0) {
//            cir.setReturnValue(true);
//        }
        if(BlazeIdolComponent.BLAZE>0){
            if(damageSource.isIn(DamageTypeTags.IS_FIRE)) {
                cir.setReturnValue(true);
            }
        }
    }
}
    @Mixin(LivingEntity.class)
abstract class DamageEntityMixin{
        @Shadow
        public abstract boolean hasStatusEffect(StatusEffect effect);

        @Inject(
                at = @At(value = "TAIL"), method = "modifyAppliedDamage", cancellable = true)
        private void parryPartial(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
            if (ParryComponent.PARRY > 0) {
                amount = amount/3;
                if(ParryComponent.PARRY_ULTRA>0){
                    cir.setReturnValue(0f);
                }
            }
            if(hasStatusEffect(FmEffects.TRUESIGHT)){
                amount = (float) (amount*1.5);
            }
            if(AdaptabilityComponent.adaptcheck(source)){// checks if the source can be adapted to
                amount = AdaptabilityComponent.hasAdapt(source, amount);// modifies the damage you take
            }
            cir.setReturnValue(amount);
        }
}
@Mixin(PlayerEntity.class)
abstract class AdaptPlayerMixin{


    @Inject(
            at = @At("HEAD"), method = "damage")
    private void AdaptCharge(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        AdaptabilityComponent.Decrease(source);//decreases adapting to other sources than this one
        AdaptabilityComponent.IncrementAdaptability(source);// increases adapt tothe source
    }
}

