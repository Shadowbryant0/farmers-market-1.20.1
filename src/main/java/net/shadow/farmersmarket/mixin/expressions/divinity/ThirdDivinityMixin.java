package net.shadow.farmersmarket.mixin.expressions.divinity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.expressions.divinity.Thirddivinity_guardian;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class ThirdDivinityMixin extends Entity {

    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract boolean clearStatusEffects();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Unique
    private boolean shouldIncrease = false;


    protected ThirdDivinityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at = @At(value = "HEAD"), method = "tryUseTotem", cancellable = true)
    private void init(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if(!shouldIncrease) return;
        if(Thirddivinity_guardian.REVIVE>0) {
            Thirddivinity_guardian.Death();
            return;
        }
        Thirddivinity_guardian.ChargeAll();

        this.setHealth(3.0f);
        this.clearStatusEffects();
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        cir.setReturnValue(true);
    }
    @Inject(at = @At("HEAD"), method = "tick")
    private void init(CallbackInfo ci) {
            shouldIncrease = guardianAura(this);
        // This code is injected into the start of MinecraftServer.loadWorld()V
    }
    @ModifyReturnValue(at = @At("RETURN"), method = "getMaxHealth")
    private float init(float original) {
        return shouldIncrease ? original + 10 : original;
    }
    @Unique
    private static boolean guardianAura(Entity user){
        World world = user.getWorld();
        final double SWEEP_RANGE = 1;
        final double AOE_RADIUS = 5;
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


            List<LivingEntity> nearby = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(host.subtract(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS),
                            host.add(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS)),
                    LivingEntity::isAlive
            );

            for (LivingEntity inrange : nearby) {
                if (inrange.getOffHandStack().isOf(ModItems.THIRDIVINITY_GUARDIAN)) {
                    if(user instanceof PlayerEntity){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
