package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class BlackFlashMixin extends LivingEntity {
    protected BlackFlashMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), method = "attack")
	private boolean init(Entity instance, DamageSource source, float amount, Operation<Boolean> original) {
        if(WeaponChargeComponent.BLACKFLASH>0){
            System.out.println(amount + " pre bonus");
            float before = amount;
            double bonus = (before*0.2)*WeaponChargeComponent.FLASHCHAIN;
            amount = (float) (before + 2+ bonus);
            WeaponChargeComponent.Chained();


            System.out.println(bonus + " gained damage bonus");

            System.out.println(amount + " final number");
        }

        return original.call(instance, source, amount);
    }
}