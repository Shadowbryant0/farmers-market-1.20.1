package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;

import java.util.List;

public class AceofSpadesClass  extends ShovelItem {
    private static final int COOLDOWN_TICKS = 240;
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;
    private static final int HALF_CHARGE = 50;
    public AceofSpadesClass(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 1.5F, -2.5F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, stack) == 0) {
                return super.use(world, user, hand);
            } else {
                if(!user.isSneaking()) {
                    if (getCharge(stack) >= HALF_CHARGE) {
                        int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                        int heal = (int) (50);
                        charge = Math.min(charge - heal, MAX_CHARGE);
                        stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
                        {
                            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS / 2));
                        }
                        user.heal(5);
                        return super.use(world, user, hand);

                    }
                }
                if(user.isSneaking()){
                    if (getCharge(stack) >= MAX_CHARGE) {
                        if (user.experienceLevel >= 4) {
                                user.addExperienceLevels(-4);
                                stack.setDamage(stack.getDamage() - 100);
                                stack.getOrCreateNbt().putInt(CHARGE_KEY, 0);
                        }
                    }
                }

                return super.use(world, user, hand);

            }
        }
        return super.use(world, user, hand);
    }
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, stack) != 0) {
            return super.useOnEntity(stack, user, entity, hand);
        } else {
            if (!user.getItemCooldownManager().isCoolingDown(this)) {


                {
                    user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                }


                if (getCharge(stack) < MAX_CHARGE) {
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    return super.useOnEntity(stack, user, entity, hand);
                } else {
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    entity.setVelocity(0, 0, 0);

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 2, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 2, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 2, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 2, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));

                    stack.getOrCreateNbt().putInt(CHARGE_KEY, 0);
                    return super.useOnEntity(stack, user, entity, hand);
                }


            }
            return super.useOnEntity(stack, user, entity, hand);
        }
    }






    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!attacker.getWorld().isClient) {
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int gain = (int) (20);
            charge = Math.min(charge + gain, MAX_CHARGE);
            stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);

        }
        if (!world.isClient && attacker.isOnGround()) {
            Vec3d attackerPos = attacker.getPos();

            // Sweep radius (like Sweeping Edge)
            double radius = 2.5;
            Box box = new Box(
                    attackerPos.x - radius, attackerPos.y - 1.0, attackerPos.z - radius,
                    attackerPos.x + radius, attackerPos.y + 1.0, attackerPos.z + radius
            );

            List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, box,
                    e -> e != attacker && e != target && e.isAlive());

            // Sweep damage ~ half base damage
            float sweepDamage = 4.0f;

            for (LivingEntity entity : nearby) {
                entity.damage(entity.getDamageSources().mobAttack(attacker), sweepDamage);
            }

            // Play sweep sound and particles
            world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);

            ((ServerWorld) world).spawnParticles(ParticleTypes.SWEEP_ATTACK,
                    attacker.getX(), attacker.getY() + 1.0, attacker.getZ(),
                    1, 0.0, 0.0, 0.0, 0.0);
        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
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
        int red = (int) (220);
        int blue = (int) (30);
        int green = (int) (170);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xFFD700 ));
    }
}
