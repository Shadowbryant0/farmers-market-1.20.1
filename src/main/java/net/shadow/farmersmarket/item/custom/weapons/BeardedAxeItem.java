package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.SweepingAxeItem;
import net.shadow.farmersmarket.item.materials.BeardedMat;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.List;

public class BeardedAxeItem extends SweepingAxeItem {
    private static final int FULL_CHARGE_TICKS = 60; // 2 seconds

    public BeardedAxeItem(Settings settings) {
        super(BeardedMat.INSTANCE, 5, -3F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Sharpen, stack) == 0){
            if(WeaponChargeComponent.BEARDED < WeaponChargeComponent.MAX_BEARDED){
                return super.use(world, user, hand);
            }else{
                WeaponChargeComponent.UseBEARDED(100);
            Vec3d attackerPos = user.getPos();

            // Sweep radius (like Sweeping Edge)
            double radius = 2.5;
            Box box = new Box(
                    attackerPos.x - radius, attackerPos.y - 1.0, attackerPos.z - radius,
                    attackerPos.x + radius, attackerPos.y + 1.0, attackerPos.z + radius
            );

            List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, box,
                    e -> e != user);

            for (LivingEntity entity : nearby) {
                entity.velocityModified = true;
                entity.addVelocity(0,1,0);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 0)); // optional visual feedback
            }
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 2.0f, 1.0f);




                user.velocityModified = true;
            user.setSwimming(false);


            }
            } else
            {
                user.setCurrentHand(hand); // Begin charging
                return TypedActionResult.consume(user.getStackInHand(hand));
            }
        }
        return super.use(world, user, hand);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }
    public int getMaxUseTime(ItemStack stack) {
        return 80;
    }
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;

        if (!world.isClient) {
            if (usedTicks >= FULL_CHARGE_TICKS ) {
                WeaponChargeComponent.IncrementBEARDED(100);
                this.playInsertSound(world, user);
            }
        }
    }



    final float SWEEP_DAMAGE = 5;
                @Override
    public boolean postHit(net.minecraft.item.ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Sharpen, stack) == 0){
        if (!attacker.getWorld().isClient) {
            WeaponChargeComponent.IncrementBEARDED(10);
            stack.damage(1, attacker, e -> {});
        }
        }else{

            if((WeaponChargeComponent.BEARDED - 10)<=0) {
                WeaponChargeComponent.UseBEARDED(10);
                target.damage(target.getDamageSources().playerAttack((PlayerEntity) attacker), 5);
            }


        }
    // Regular axe damage


if(FarmersmarketUtil.isCritical(target)){
    target.damage(target.getDamageSources().mobAttack(attacker), 10);
}
        return super.postHit(stack, target, attacker);
}
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) WeaponChargeComponent.BEARDED / WeaponChargeComponent.MAX_BEARDED * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        int red = (int) (108);
        int blue = (int) (170);
        int green = (int) (59);
        return (red << 16)| (green << 8) | blue; // RGB mix
    }
    private void playInsertSound(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.PLAYERS, 1.0f, 0.8f);
    }
}
