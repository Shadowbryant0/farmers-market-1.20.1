package net.shadow.farmersmarket.mixin.expressions.the_void;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.expressions.divinity.Firstdivinity_flight;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class FirstAbyssMixin extends Entity {

    @Unique
    private boolean isGrounded = false;

    public FirstAbyssMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;getAmplifier()I"), method = "travel")
    private int levitation(StatusEffectInstance instance, Operation<Integer> original) {
        velocityModified= true;
            return isGrounded ? -4 : original.call(instance);
    }

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isOnGround()Z"), method = "tickFallFlying")
    private boolean setGrounded(LivingEntity instance, Operation<Boolean> original) {
        return isGrounded;
    }
    @Inject(at = @At("HEAD"), method = "tick")
    private void setElytaGrounded(CallbackInfo info) {
        isGrounded = shackled(this);
    }

    @Unique
    private static boolean shackled(Entity user){
        World world = user.getWorld();
        final double SWEEP_RANGE = 1;
        final float SWEEP_DAMAGE = 3;
        final double AOE_RADIUS = 7.5;
        Vec3d origin = user.getEyePos();
        Vec3d look   = user.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(SWEEP_RANGE));
        if (!world.isClient) {

            if (!(world instanceof ServerWorld serverWorld)) {
                return false;
            }


            var host = user.getPos();


            List<LivingEntity> potential = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(origin, end).expand(1.0),
                    e -> e == user
            );
            for (LivingEntity self :potential){
                if (self.getOffHandStack().isOf(ModItems.FIRSTOFTHEABYSS_SHACKLES)) {
                    if(user.isLiving()) {
                        if(user.isPlayer()){
                            Firstdivinity_flight.setGROUNDED(true);
                        }
                        return true;
                    }
                }
            }
            double minDist = Double.MAX_VALUE;


            List<LivingEntity> nearby = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(host.subtract(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS),
                            host.add(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS)),
                    LivingEntity::isAlive
            );

            for (LivingEntity inrange : nearby) {
                if (inrange.getOffHandStack().isOf(ModItems.FIRSTOFTHEABYSS_SHACKLES)) {
                    if(user.isLiving()) {
                        if(user.isPlayer()){
                            Firstdivinity_flight.setGROUNDED(true);
                        }
                        return true;
                    }
                }
            }

            Firstdivinity_flight.setGROUNDED(false);
        }
        return false;
    }
}