package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
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
import net.shadow.farmersmarket.item.materials.BloodMat;

import java.util.List;

public class ExecutionersAxeClass extends AxeItem {
    private static final int COOLDOWN_TICKS = 100;
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;
    private static final int QUARTER_CHARGE = 25;

    public ExecutionersAxeClass(Item.Settings settings) {
        super(BloodMat.INSTANCE, 5, -3.1F, settings);
    }

// right click pulls entity closer and applies weakness

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Inferno, stack) == 0) {
                return super.use(world, user, hand);
            } else {

                    if (getCharge(stack) >= MAX_CHARGE) {
                        stack.getOrCreateNbt().putInt(CHARGE_KEY, 0);
                        // Sweep radius (like Sweeping Edge)
                        Vec3d attackerPos = user.getPos();
                        double radius = 7.5;
                        Box box = new Box(
                                attackerPos.x - radius, attackerPos.y - 1.0, attackerPos.z - radius,
                                attackerPos.x + radius, attackerPos.y + 1.0, attackerPos.z + radius
                        );

                        List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, box,
                                e -> e != user);

                        // Sweep damage ~ half base damage


                        for (LivingEntity entity : nearby) {
                            entity.setOnFireFor(20);
                            if(entity instanceof PlayerEntity player) {
                                player.sendMessage(Text.literal("Your flesh burns"), true);
                            }

                        }
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120, 1));

                        // Play sweep sound and particles
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                SoundEvents.ENTITY_BLAZE_BURN, SoundCategory.PLAYERS, 3.0f, 1.0f);

                        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME,
                                user.getX(), user.getY() + 1.0, user.getZ(),
                                1, 0.0, 0.0, 0.0, 0.0);
                    }
                        {
                            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS * 2));
                        }
                    return super.use(world, user, hand);

            }




        }

        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Inferno, stack) == 0) {
            World world = user.getWorld();
            if (!world.isClient) {
                if (!user.getItemCooldownManager().isCoolingDown(this)) {
                    if (getCharge(stack) >= QUARTER_CHARGE) {
                        int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                        int Quarter = (int) (25);
                        charge = Math.min(charge - Quarter, MAX_CHARGE);
                        stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);


                        entity.setVelocity(user.getEyePos().subtract(entity.getPos()).normalize().multiply(0.8));
                        entity.addVelocity(0, 0.25, 0);
                        entity.velocityModified = true;
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20, 0));
                        {
                            user.getItemCooldownManager().set(this, COOLDOWN_TICKS / 2);
                        }
                        return super.useOnEntity(stack, user, entity, hand);
                    } else {
                        entity.setVelocity(user.getEyePos().subtract(entity.getPos()).normalize().multiply(0.8));
                        entity.velocityModified = true;
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));

                        {
                            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                        }
                    }

                }
            }
        }

        return super.useOnEntity(stack, user, entity, hand);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();


        if (!attacker.getWorld().isClient) {
                int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                int gain = (int) (10);
                charge = Math.min(charge + gain, MAX_CHARGE);
                stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
                return super.postHit(stack, target, attacker);

        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xF2EBE3 ));
    }
    // === Durability bar as charge indicator ===
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
        int red = (int) (200);
        int blue = (int) (200);
        int green = (int) (200);
            return (red << 16) | (green << 8) | blue; // RGB mix
    }


    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }
}
