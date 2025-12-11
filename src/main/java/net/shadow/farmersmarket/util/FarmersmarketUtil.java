package net.shadow.farmersmarket.util;


import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.*;


import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.trinkets.piglinBruteRing;

import java.util.List;
import java.util.Optional;

public class FarmersmarketUtil {




    public static boolean hasEnchantment(Enchantment enchantment, ItemStack stack) {
        System.out.println("enchant detected");
        return EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }


    public static float getMaxBonusStarvationDamage(ItemStack stack) {
        float maxBonus = 2;
        for (EntityAttributeModifier modifier : stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
            maxBonus += (float) modifier.getValue();
        }
        return maxBonus / 2;
    }

    public static float getBonusStarvationDamage(LivingEntity living, ItemStack stack) {
        System.out.println("entity detected");
        if (living != null && hasEnchantment(FarmersMarketEnchants.Starvation, stack)) {
            if (living instanceof PlayerEntity player) {
                System.out.println("player detected");
                System.out.println(player.getHungerManager().getFoodLevel());
                float hunger = 18;
                float bonus = 0;
                while (hunger > player.getHungerManager().getFoodLevel()) {
                    hunger -= 2;
                    bonus += 1F;
                }
                return Math.min(bonus, getMaxBonusStarvationDamage(stack));

            }
            System.out.println("mob detected");
            float health = living.getMaxHealth() - 1;
            float bonus = 0;
            while (health > living.getHealth()) {
                health -= 2;
                bonus += 1F;
            }
            return Math.min(bonus, getMaxBonusStarvationDamage(stack));
        }
        return 0;
        }
    // beserk - enchancement by MoriyaShiine

    public static float JagerDamage(LivingEntity living, ItemStack stack) {

        if (living != null && hasEnchantment(FarmersMarketEnchants.JagerderSchuldigen, stack)) {

            float health = living.getMaxHealth() - 8;

            if (health > living.getHealth()) {

                return 6;
            }
            return 0;
        }
        return 0;
    }
    // beserk - enchancement by MoriyaShiine
    public static boolean isCritical(LivingEntity user) {
        return (user.getVelocity().getY()) <= 0;
    }

    public static void sweepingEdge(LivingEntity target, LivingEntity attacker, float damage, boolean knockback){
        World world = attacker.getWorld();
        final double SWEEP_RANGE = 1;
        final float SWEEP_DAMAGE = 3;
        final double AOE_RADIUS = .5;
        Vec3d origin = attacker.getEyePos();
        Vec3d look   = attacker.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(SWEEP_RANGE));
        if (!world.isClient && attacker.isOnGround()) {
            if (!(world instanceof ServerWorld serverWorld)) {
                return;
            }


            var hit = target.getPos();
            var host = attacker.getPos();

            serverWorld.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 3.0f, 1.0f);
            int particleCount = (int) (SWEEP_RANGE * 2.5);
            for (int i = 0; i < particleCount; i++) {
                double t = i / 2.5;
                Vec3d pos = origin.add(look.multiply(t));
                serverWorld.spawnParticles(ParticleTypes.SWEEP_ATTACK, pos.x, pos.y-1, pos.z, 1, 0, 0, 0, 0);
            }
            LivingEntity directTarget = null;
            List<LivingEntity> potential = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(origin, end).expand(1.0),
                    e -> e != attacker && e.isAlive()
            );
            double minDist = Double.MAX_VALUE;
            for (LivingEntity e : potential) {
                double d = origin.distanceTo(e.getPos());
                if (d < minDist && d <= SWEEP_RANGE) {
                    minDist = d;
                    directTarget = e;
                }
            }

            List<LivingEntity> nearby = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(hit.subtract(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS),
                            hit.add(AOE_RADIUS, AOE_RADIUS, AOE_RADIUS)),
                    e -> e != attacker && e.isAlive()
            );

            for (LivingEntity sweeped : nearby) {
                sweeped.damage(serverWorld.getDamageSources().mobAttack(attacker), damage);
                if(knockback){
                    Vec3d push = sweeped.getPos().subtract(host).normalize().multiply(0.5);
                    Vec3d push2 = target.getPos().subtract(host).normalize().multiply(0.2);
                    target.addVelocity(push2.x, 0.1, push2.z);
                    sweeped.addVelocity(push.x, 0.1, push.z);
                    sweeped.velocityModified = true;
                    target.velocityModified = true;
                }else {
                    Vec3d push = sweeped.getPos().subtract(host).normalize().multiply(0.2);

                    sweeped.addVelocity(push.x, 0.1, push.z);
                    sweeped.velocityModified = true;
                }

            }
        }
    }
    public static ItemStack getTrinket(LivingEntity livingEntity) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        if (component.isPresent()) {
            for (Pair<SlotReference, ItemStack> pair : component.get().getAllEquipped()) {
                if (pair.getRight().getItem() instanceof piglinBruteRing) {
                    return pair.getRight();
                }
            }
        }
        return ItemStack.EMPTY;
    }
    //code from rat's mischief
    //by doctorrat/ratmaid
    //sourced by kirro
    //on the rattiest gang discord server
     //thanks!
}



