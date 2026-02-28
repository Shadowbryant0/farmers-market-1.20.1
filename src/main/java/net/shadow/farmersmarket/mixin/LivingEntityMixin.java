package net.shadow.farmersmarket.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
class MobDropsMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;
        if (!(((Object) this) instanceof LivingEntity victim)) return;

        ItemStack weapon = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof SkeletonEntity) {

                // Drop Silver Flesh
                ItemStack crackedSkull = new ItemStack(ModItems.CRACKED_SKULL);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));

            }
        }

        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof SkeletonEntity) {
                // Drop Silver Flesh
                ItemStack crackedSkull = new ItemStack(ModItems.CRACKED_SKULL);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));
            }
        }
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.JagerderSchuldigen, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof WitherSkeletonEntity) {
                if (attacker.getRandom().nextInt(10) == 10) {

                    // Drop Cracked Skull
                    ItemStack crackedSkull = new ItemStack(Items.WITHER_SKELETON_SKULL);
                    Vec3d pos = attacker.getPos();

                    world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));

                }
                if (victim instanceof SkeletonEntity) {
                    if (attacker.getRandom().nextInt(10) == 10) {

                        // Drop Cracked Skull
                        ItemStack crackedSkull = new ItemStack(Items.SKELETON_SKULL);
                        Vec3d pos = attacker.getPos();

                        world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));

                    }
                }
            }
        }
    }
}
@Mixin(LivingEntity.class)
class StarvationMixin {
    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float FarmersMarket$Starvation(float value, DamageSource source) {

        if (source.getSource() instanceof LivingEntity living && !living.getWorld().isClient) {
            return value + FarmersmarketUtil.getBonusStarvationDamage(living, living.getMainHandStack());
        }
        return value;
    }
           // beserk - enchancement by MoriyaShiine
}
@Mixin(LivingEntity.class)
class StarvationkilMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;
        if (!(((Object) this) instanceof LivingEntity victim)) return;
        ItemStack weapon = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Starvation, weapon) > 0) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                if (attacker instanceof PlayerEntity player) {
                    if (victim instanceof PlayerEntity) {
                        player.getHungerManager().add(12, .4f);
                    } else {
                        player.getHungerManager().add(8, .2f);
                    }
                } else {


                    attacker.heal(6);
                }


            }
        }
    }
}
        @Mixin(LivingEntity.class)
        class JagerDamageMixin {
            @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
            private float FarmersMarket$Starvation(float value, DamageSource source) {

                if (source.getSource() instanceof LivingEntity living && !living.getWorld().isClient) {
                    return value + FarmersmarketUtil.JagerDamage(living, living.getMainHandStack());
                }
                return value;
            }
            // beserk - enchancement by MoriyaShiine
        }

@Mixin(LivingEntity.class)
abstract class LavaWader {

    @Shadow
    protected abstract boolean shouldSwimInFluids();

    // Using Inject to intercept the method and modify behavior
    @Inject(
            method = "travel(Lnet/minecraft/util/math/Vec3d;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInLava()Z"),
            cancellable = true
    )
    private void replaceLavaBlockWithDepthStrider(Vec3d movementInput, CallbackInfo ci) {
        // Get the current LivingEntity instance (self)
        LivingEntity self = (LivingEntity) (Object) this;
        // Check if we should run LavaWader logic
        boolean inLava = self.isInLava();
        boolean shouldRun = inLava && this.shouldSwimInFluids();

        // Skip if no LavaWader enchantment is present or we shouldn't run
        if (FMEnchantCheck.getLavaWader(self) == 0 || !shouldRun) {
            return;
        }

        // Apply LavaWader logic
        runLavaDepthStrider(self, movementInput);

        // Cancel the default lava behavior (vanilla)
        ci.cancel();
    }

    @Unique
    private static void runLavaDepthStrider(LivingEntity self, Vec3d movementInput) {
        int level = FMEnchantCheck.getLavaWaderCollective(self);
        level = MathHelper.clamp(level, 0, 2); // Ensure level is between 0 and 2

        // Base lava uses 0.02F acceleration; scale it up with level
        float accel = 0.02F + 0.08F * level;

        // Damping (drag): interpolate between vanilla values and more fluid-like behavior
        float horizDamp = MathHelper.lerp(level / 1.1F, 0.5F, 0.91F);
        float vertDamp = MathHelper.lerp(1 / 1.1F, 0.8F, 0.98F);

        // Apply player velocity with boosted acceleration
        self.updateVelocity(accel, movementInput);
        self.move(MovementType.SELF, self.getVelocity());

        // Handle submerged case with damping
        if (self.getFluidHeight(FluidTags.LAVA) <= self.getSwimHeight()) {
            self.setVelocity(self.getVelocity().multiply(horizDamp, vertDamp, horizDamp));
        } else {
            float fullDamp = MathHelper.lerp(level / 3.0F, 0.5F, 0.9F);
            self.setVelocity(self.getVelocity().multiply(fullDamp, vertDamp, fullDamp));
        }

        // Gravity compensation
        if (!self.hasNoGravity()) {
            if(self.isSneaking()){
                self.addVelocity(0, -0.04, 0);
            }else {
                self.addVelocity(0, -0.005, 0); // Small gravity compensation
            }
        }

        // Step-up assist for ledges
        Vec3d v = self.getVelocity();
        if (self.horizontalCollision && self.doesNotCollide(v.x, v.y + 0.6 - self.getY(), v.z)) {
            self.setVelocity(v.x, 0.3, v.z); // Adjust y velocity for ledge climbing
        }
    }
}
