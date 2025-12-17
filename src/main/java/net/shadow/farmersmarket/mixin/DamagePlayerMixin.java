package net.shadow.farmersmarket.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Armor.AdaptabilityComponent;
import net.shadow.farmersmarket.components.BlazeIdolComponent;
import net.shadow.farmersmarket.components.Weapons.ParryComponent;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class DamagePlayerMixin {


    @Inject(
            at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void Parry(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if(ParryComponent.PARRY >0) {
            cir.setReturnValue(true);
        }
        if(BlazeIdolComponent.BLAZE>0){
            if(damageSource.isIn(DamageTypeTags.IS_FIRE)) {
                cir.setReturnValue(true);
            }
        }
    }
}
@Mixin(PlayerEntity.class)
abstract class AdaptPlayerMixin{


    @Inject(
            at = @At("HEAD"), method = "damage")
    private void AdaptCharge(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        AdaptabilityComponent.Decrease(source);
        AdaptabilityComponent.IncrementAdaptability(source);
    }
}
@Mixin(PlayerEntity.class)
abstract class AdaptAttackMixin extends LivingEntity{


    @Shadow
    public abstract boolean isPlayer();

    protected AdaptAttackMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            at = @At("HEAD"), method = "attack")
    private void AdaptCharge(Entity target, CallbackInfo ci) {
        if(FMEnchantCheck.getAdapting(this)>0) {
            if (this.isPlayer()) {
                target.damage(target.getDamageSources().mobAttack(this), (float) (AdaptabilityComponent.PROJECTILE + AdaptabilityComponent.EXPLOSIVE));
                if(target.getVelocity().getY()>=0) {
                    target.setVelocity(target.getVelocity().getX(), target.getVelocity().getY() + (AdaptabilityComponent.KINETIC / 4) + (AdaptabilityComponent.EXPLOSIVE / 2), target.getVelocity().getZ());
                }else {
                    target.setVelocity(target.getVelocity().getX(), (AdaptabilityComponent.KINETIC / 4) + (AdaptabilityComponent.EXPLOSIVE / 2), target.getVelocity().getZ());
                }
                target.setFireTicks((int) (AdaptabilityComponent.FIRE * 40));
            }
        }
    }
}

