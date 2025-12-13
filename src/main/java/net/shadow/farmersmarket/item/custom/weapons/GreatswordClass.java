package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.components.Weapons.GreatSwordChargeComponent;
import net.shadow.farmersmarket.item.materials.Greatmat;

import java.util.List;
import java.util.Objects;

public class GreatswordClass extends SwordItem {
    private static final int COOLDOWN_TICKS = 200;
    //  right click is fast short dash that slams into enemies (custom stun effect)
    public GreatswordClass(Item.Settings settings) {
        super(Greatmat.INSTANCE, 3, -2.6F, settings);
    }


    private static final float ATTACKSPEED = 3.15F;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Shout, stack) == 0) {
            if(GreatSwordChargeComponent.GREAT>= GreatSwordChargeComponent.TWO_THREE_GREAT) {

                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60, 2));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
                GreatSwordChargeComponent.UseGREAT();
            }
            return TypedActionResult.consume(stack);
        }else{
            if (!world.isClient) {
                if (GreatSwordChargeComponent.GREAT>= GreatSwordChargeComponent.MAX_GREAT) {

                    Vec3d attackerPos = user.getPos();
                    GreatSwordChargeComponent.UseAltGREAT();
                    // Sweep radius (like Sweeping Edge)
                    double radius = 2.5;
                    Box box = new Box(
                            attackerPos.x - radius, attackerPos.y - 1.0, attackerPos.z - radius,
                            attackerPos.x + radius, attackerPos.y + 1.0, attackerPos.z + radius
                    );

                    List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, box,
                            e -> e != user);

                    // Sweep damage ~ half base damage


                    for (LivingEntity entity : nearby) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 0));
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 120, 0));
                        if(entity instanceof PlayerEntity player) {
                            player.sendMessage(Text.literal("Your ears bleed"), true);
                        }

                    }
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120, 1));

                    // Play sweep sound and particles
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.PLAYERS, 3.0f, 1.0f);

                    ((ServerWorld) world).spawnParticles(ParticleTypes.ANGRY_VILLAGER,
                            user.getX(), user.getY() + 1.0, user.getZ(),
                            1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        return TypedActionResult.consume(stack);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000; // hold indefinitely
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Shout, stack) == 0) {
            return UseAction.BLOCK;
        }else {
            return  UseAction.NONE;
        }
    }

    // === Absorb damage into charge ===



    // === Release charge as extra damage ===
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();


        if (!attacker.getWorld().isClient) {

                GreatSwordChargeComponent.IncrementCharge();
                return super.postHit(stack, target, attacker);

        }
        return super.postHit(stack, target, attacker);
    }


    // === Durability bar as charge indicator ===
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {

            return Math.round((float) GreatSwordChargeComponent.GREAT / GreatSwordChargeComponent.MAX_GREAT * 13); // full bar = max charge

    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Shout, stack) == 0) {
            int red = (int) (200);
            int blue = (int) (200);
            int green = (int) (200);
            return (red << 16) | (green << 8) | blue; // RGB mix
        }else{
            int red = (int) (220);
            int blue = (int) (60);
            int green = (int) (20);
            return (red << 16) | (green << 8) | blue; // RGB mix
        }
    }



}

