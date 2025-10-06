package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import net.shadow.farmersmarket.util.LavaWaderCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
class PlayerDropsMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;
        if (!(((Object) this) instanceof LivingEntity victim)) return;

        ItemStack weapon = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof PlayerEntity) {

                // Drop Silver Flesh
                ItemStack sliverFlesh = new ItemStack(ModItems.SLIVER_FLESH);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, sliverFlesh));

            } else {


                // Drop Cracked Skull
                ItemStack crackedSkull = new ItemStack(Items.ROTTEN_FLESH);
                Vec3d pos = victim.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));


            }
        }

        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof PlayerEntity) {
System.out.println("Player killed");
                // Drop Silver Flesh
                ItemStack sliverFlesh = new ItemStack(Items.DIAMOND_BLOCK);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, sliverFlesh));

            } else {
                System.out.println("Entity killed");

                // Drop Cracked Skull
                ItemStack crackedSkull = new ItemStack(ModItems.SLIVER_FLESH);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));


            }
        }
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.JagerderSchuldigen, weapon) > 0) {
            World world = attacker.getWorld();
            if (victim instanceof PlayerEntity) {

                // Drop Silver Flesh
                ItemStack sliverFlesh = new ItemStack(ModItems.CRACKED_SKULL);
                Vec3d pos = attacker.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, sliverFlesh));

            } else
                if (victim instanceof WitherSkeletonEntity) {if (attacker.getRandom().nextInt(10) == 10) {

                    // Drop Cracked Skull
                    ItemStack crackedSkull = new ItemStack(Items.WITHER_SKELETON_SKULL);
                    Vec3d pos = attacker.getPos();

                    world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, crackedSkull));

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

    // ugh intelij being slow
    @ModifyExpressionValue(
            method = "travel(Lnet/minecraft/util/math/Vec3d;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;isInLava()Z")
    )
    private boolean replaceLavaBlockWithDepthStrider(
            boolean inLava,
            @Local(name = "movementInput") Vec3d movementInput,
            @Local(name = "fluidState") FluidState fluidState,
            @Local(name = "d") double d,       // buoyancy/gravity scalar used by vanilla in fluids
            @Local(name = "bl") boolean bl     // typically "onGround" or related; keep if present in your version
    ) {


        LivingEntity self = (LivingEntity) (Object) this;

        boolean shouldRun =
                inLava && this.shouldSwimInFluids() && !self.canWalkOnFluid(fluidState);
        // right here!

        if (LavaWaderCheck.getLavaWader(self) == 0) return inLava;

        if (!shouldRun) return inLava;

        runLavaDepthStrider(self, movementInput, d, bl);
        // Skip vanilla lava branch by returning false for this check.
        return false;
    }
    @Unique
    private static void runLavaDepthStrider(LivingEntity self, Vec3d movementInput, double d, boolean bl) {
        int level = EnchantmentHelper.getEquipmentLevel(FarmersMarketEnchants.LavaWader, self);
        if (level < 0) level = 0;
        if (level > 1) level = 1;

        // Base lava uses 0.02F accel; scale it up with level (tune to taste).
        float accel = 0.02F + 0.08F * level;  // 0.02, 0.04, 0.06, 0.08

        // Damping: vanilla lava multiplies velocity by 0.5 horizontally, 0.8 vertically (heavier drag).
        // Lerp these toward ~water-like values as level increases.
        float horizDamp = MathHelper.lerp(level / 1.1F, 0.5F, 0.91F); // 0.5 → 0.91
        float vertDamp  = MathHelper.lerp(level / 1.1F, 0.8F, 0.98F); // 0.8 → 0.98

        // Gravity weakening: less sinking with higher level.


        double yStart = self.getY();

        // Apply player input with boosted accel
        self.updateVelocity(accel, movementInput);
        self.move(MovementType.SELF, self.getVelocity());

        // If not fully "swimming" height, use anisotropic damping (like vanilla) but weaker with level
        if (self.getFluidHeight(FluidTags.LAVA) <= self.getSwimHeight()) {
            self.setVelocity(self.getVelocity().multiply(horizDamp, vertDamp, horizDamp));
        } else {
            // Fully submerged case; vanilla uses 0.5 overall—keep closer to that at L0, relax with level
            float fullDamp = MathHelper.lerp(level / 3.0F, 0.5F, 0.9F);
            self.setVelocity(self.getVelocity().multiply(fullDamp, vertDamp, fullDamp));
        }

        // Gravity (downward) reduced by gravityScale
        if (!self.hasNoGravity()) {
            self.addVelocity(0, .075, 0);
        }

        // Ledge "step-up" assist — keep vanilla behavior
        Vec3d v = self.getVelocity();
        if (self.horizontalCollision && self.doesNotCollide(v.x, v.y + 0.6 - self.getY() + yStart, v.z)) {
            self.setVelocity(v.x, 0.3, v.z);
        }
    }

}
