package net.shadow.farmersmarket.mixin.Crossbow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


    @Mixin(CrossbowItem.class)
class ShockwaveMixin {
        @Inject(method = "shoot", at = @At("HEAD"), cancellable = true)
        private static void farmersmarket$Shockwave(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated, CallbackInfo ci) {
            double boost = 1d;
            if (!world.isClient && FarmersmarketUtil.hasEnchantment(FarmersMarketEnchants.ShockwaveEnchant, crossbow) && projectile.isOf(Items.ARROW)) {
                crossbow.damage(1, shooter, stackUser -> stackUser.sendToolBreakStatus(hand));
                world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1, soundPitch);

                Vec3d lookingDirection = shooter.getRotationVec(1.0f);

                shooter.addVelocity(
                        lookingDirection.x * -boost * 0.9f,
                        lookingDirection.y * -boost * 1.2f,
                        lookingDirection.z * -boost * 0.9f
                );




                shooter.velocityModified = true;
                if (shooter instanceof PlayerEntity player) {
                    player.getItemCooldownManager().set(crossbow.getItem(), 20);
                }
                ci.cancel();
            }
            if (!world.isClient && FarmersmarketUtil.hasEnchantment(FarmersMarketEnchants.ShockwaveEnchant, crossbow) && projectile.isOf(Items.FIREWORK_ROCKET)) {
                crossbow.damage(1, shooter, stackUser -> stackUser.sendToolBreakStatus(hand));
                world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1, soundPitch);

                Vec3d lookingDirection = shooter.getRotationVec(1.0f);

                shooter.addVelocity(
                        lookingDirection.x * -boost * 1.5f,
                        lookingDirection.y * -boost * 2.5f,
                        lookingDirection.z * -boost * 1.5f
                );




                shooter.velocityModified = true;
                if (shooter instanceof PlayerEntity player) {
                    player.getItemCooldownManager().set(crossbow.getItem(), 20);
                }
                ci.cancel();
            }

        }
    }
//made using scatter enchant mixin as basis, enchancement, by MoriyaShiine
