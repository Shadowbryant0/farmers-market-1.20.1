package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.materials.BloodMat;
import net.shadow.farmersmarket.item.materials.SickleMat;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.List;

public class RustedSickle extends HoeItem {
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;

    final double RANGE = 20;
    final float BASE_DAMAGE = 10;

    final float SWEEP_DAMAGE = 3;

    public RustedSickle(Item.Settings settings) {
        super(SickleMat.INSTANCE, 1, -2.4F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d origin = user.getEyePos();
        Vec3d look   = user.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(RANGE));
        ItemStack stack = user.getStackInHand(hand);
        if (EnchantmentHelper.getEquipmentLevel(FarmersMarketEnchants.Rusted, user) > 0) {
            if (!(world instanceof ServerWorld serverWorld)) return super.use(world, user, hand);
            if(getCharge(stack) >= MAX_CHARGE- (MAX_CHARGE/2)) {
                int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                int rust = (int) (MAX_CHARGE/2);
                charge = Math.min(charge - rust, MAX_CHARGE);
                stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
            var hit = world.raycast(new RaycastContext(
                    origin, end,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    user
            ));
            serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.PLAYERS, 3.0f, 1.0f);
            int particleCount = (int) (RANGE * 2.5);
            for (int i = 0; i < particleCount; i++) {
                double t = i / 2.5;
                Vec3d pos = origin.add(look.multiply(t));
                serverWorld.spawnParticles(ParticleTypes.SMOKE, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            }
            Vec3d impact = (hit.getType() == HitResult.Type.MISS) ? end : hit.getPos();
            LivingEntity directTarget = null;
            List<LivingEntity> potential = serverWorld.getEntitiesByClass(
                    LivingEntity.class,
                    new Box(origin, end).expand(1.0),
                    e -> e != user && e.isAlive()
            );
            double minDist = Double.MAX_VALUE;
            for (LivingEntity e : potential) {
                double d = origin.distanceTo(e.getPos());
                if (d < minDist && d <= RANGE) {
                    minDist = d;
                    directTarget = e;
                }
            }

            if (directTarget != null) {
                directTarget.damage(serverWorld.getDamageSources().mobAttack(user), BASE_DAMAGE);
                directTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 140, 2));
                //impact = directTarget.getPos();
            }
            return super.use(world, user, hand);
            }
        }
        else {
            if (!world.isClient) {
                if (getCharge(stack) >= (MAX_CHARGE / 2)) {
                    Vec3d attackerPos = user.getPos();

                    int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                    int rust = (int) (MAX_CHARGE / 2);
                    charge = Math.min(charge - rust, MAX_CHARGE);
                    stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
                    // Sweep radius (like Sweeping Edge)
                    double radius = 2.5;
                    Box box = new Box(
                            attackerPos.x - radius, attackerPos.y - 1.0, attackerPos.z - radius,
                            attackerPos.x + radius, attackerPos.y + 1.0, attackerPos.z + radius
                    );

                    List<LivingEntity> nearby = world.getEntitiesByClass(LivingEntity.class, box,
                            e -> e != user);

                    // Sweep damage ~ half base damage
                    float sweepDamage = 4.0f;

                    for (LivingEntity entity : nearby) {
                        entity.damage(entity.getDamageSources().mobAttack(user), sweepDamage);
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 160, 1));

                    }

                    // Play sweep sound and particles
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);

                    ((ServerWorld) world).spawnParticles(ParticleTypes.LARGE_SMOKE,
                            user.getX(), user.getY() + 1.0, user.getZ(),
                            1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }


        return super.use(world, user, hand);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.getWorld().isClient) {
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int gain = (int) (20);
            charge = Math.min(charge + gain, MAX_CHARGE);
            stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);

        }
        FarmersmarketUtil.sweepingEdge(target, attacker, SWEEP_DAMAGE, false);
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
        int red = (int) (183);
        int blue = (int) (14);
        int green = (int) (65);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }

}
