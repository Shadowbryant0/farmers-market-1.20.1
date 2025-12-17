package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.SweepingShovelItem;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;

import java.util.List;

public class AceofSpadesItem extends SweepingShovelItem {
    private static final int COOLDOWN_TICKS = 240;

    public AceofSpadesItem(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 1.5F, -2.5F, settings);
    }

    final double RANGE = 10;
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d origin = user.getEyePos();
        Vec3d look   = user.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(RANGE));
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, stack) == 0) {
                if (!(world instanceof ServerWorld serverWorld)) {
                    return super.use(world, user, hand);
                }
                var hit = world.raycast(new RaycastContext(
                            origin, end,
                            RaycastContext.ShapeType.COLLIDER,
                            RaycastContext.FluidHandling.NONE,
                            user
                    ));
                    serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.PLAYERS, 3.0f, 1.0f);
                    int particleCount = (int) (RANGE * 2.5);
                    for (int i = 0; i < particleCount; i++) {
                        double t = i / 2.5;
                        Vec3d pos = origin.add(look.multiply(t));
                        serverWorld.spawnParticles(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
                    }
                Vec3d impact = (hit.getType() == HitResult.Type.MISS) ? end : hit.getPos();
                    LivingEntity directTarget = null;
                    List<LivingEntity> potential = serverWorld.getEntitiesByClass(
                            LivingEntity.class,
                            new Box(origin, end),
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
                        impact = directTarget.getPos();
                        if(WeaponChargeComponent.SPADE>= WeaponChargeComponent.MAX_SPADE) {
                            WeaponChargeComponent.UseSPADE(100);
                            directTarget.setVelocity(0, 0, 0);
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() + 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() - 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() + 2, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() + 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() - 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() - 2, 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() + 2, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() - 2, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() + 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                            user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() - 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 8, user));

                        }

                        user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                        user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                        user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() + 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                        user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX(), directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                        user.getWorld().spawnEntity(new EvokerFangsEntity(directTarget.getWorld(), directTarget.getBlockPos().toCenterPos().getX() - 1, directTarget.getBlockPos().getY(), directTarget.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                        {
                            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS));
                        }
                    }
                    return super.use(world, user, hand);
                
            } else {
                if(!user.isSneaking()) {
                    if (WeaponChargeComponent.SPADE >= WeaponChargeComponent.HALF_SPADE) {
                        WeaponChargeComponent.UseSPADE(50);
                        {
                            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS / 2));
                        }
                        this.playInsertSound(world, user);
                        user.heal(5);
                        return super.use(world, user, hand);

                    }
                }
                if(user.isSneaking()){
                    if (WeaponChargeComponent.SPADE >= WeaponChargeComponent.MAX_SPADE) {
                        if (user.experienceLevel >= 4) {
                                user.addExperienceLevels(-4);
                                stack.setDamage(stack.getDamage() - 100);
                                WeaponChargeComponent.UseSPADE(100);
                        }
                    }
                }

                return super.use(world, user, hand);

            }
        }
        return super.use(world, user, hand);
    }







    final float SWEEP_DAMAGE = 4;
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!attacker.getWorld().isClient) {
            WeaponChargeComponent.IncrementSPADE(10);

        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) WeaponChargeComponent.SPADE / WeaponChargeComponent.MAX_SPADE * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (220);
        int blue = (int) (30);
        int green = (int) (170);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xFFD700 ));
    }
    private void playInsertSound(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 0.5f, 1.0f);
    }
}
