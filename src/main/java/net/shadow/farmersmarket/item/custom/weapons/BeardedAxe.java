package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.materials.BeardedMat;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.List;

public class BeardedAxe extends AxeItem {
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;
    private static final int FULL_CHARGE_TICKS = 60; // 2 seconds

    public BeardedAxe(Settings settings) {
        super(BeardedMat.INSTANCE, 5, -2.5F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Sharpen, stack) == 0){
            if(getCharge(stack) < MAX_CHARGE){
                return super.use(world, user, hand);
            }else{
            stack.getOrCreateNbt().putInt(CHARGE_KEY, 0);
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
                stack.getOrCreateNbt().putInt(CHARGE_KEY, 100);
                this.playInsertSound(world, user);
            }
        }
    }



    final float SWEEP_DAMAGE = 5;
                @Override
    public boolean postHit(net.minecraft.item.ItemStack stack, LivingEntity target, LivingEntity attacker) {
                    FarmersmarketUtil.sweepingEdge(target, attacker, SWEEP_DAMAGE, false);
        if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Sharpen, stack) == 0){
        if (!attacker.getWorld().isClient) {
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int gain = (int) (20);
            charge = Math.min(charge + gain, MAX_CHARGE);
            stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
            stack.damage(1, attacker, e -> {});
        }
        }else{
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int damage = (int) (10);
            if(charge-damage >=0) {
                charge = Math.min(charge - damage, MAX_CHARGE);
                stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
                target.damage(target.getDamageSources().mobAttack(attacker), 5);
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
        return getCharge(stack) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int charge = getCharge(stack);
        return Math.round((float) charge / MAX_CHARGE * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        float ratio = (float) getCharge(stack) / MAX_CHARGE;
        int red = (int) (108);
        int blue = (int) (170);
        int green = (int) (59);
        return (red << 16)| (green << 8) | blue; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }
    private void playInsertSound(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.PLAYERS, 1.0f, 0.8f);
    }
}
