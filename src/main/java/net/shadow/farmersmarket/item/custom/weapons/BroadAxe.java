package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.materials.BroadMat;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.List;

public class BroadAxe extends AxeItem {
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;


    public BroadAxe(Settings settings) {
        super(BroadMat.INSTANCE, 3, -2.5F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
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



            user.velocityModified = true;
            user.setSwimming(false);
        }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(net.minecraft.item.ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        Vec3d lookingDirection = attacker.getRotationVec(1.0f);
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
        if (!attacker.getWorld().isClient) {
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int gain = (int) (20);
            charge = Math.min(charge + gain, MAX_CHARGE);
            stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
        }
    // Regular axe damage

        stack.damage(1, attacker, e -> {});
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
        int red = (int) (255 * ratio);
        int blue = (int) (255 * (1 - ratio));
        return (red << 16) | (0x00 << 8) | blue; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }
}
