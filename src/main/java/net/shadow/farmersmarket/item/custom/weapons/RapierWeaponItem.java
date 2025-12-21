package net.shadow.farmersmarket.item.custom.weapons;


import com.google.common.collect.ImmutableMultimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

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
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import com.google.common.collect.Multimap;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;


import java.util.List;
import java.util.UUID;

public class RapierWeaponItem extends SwordItem {

    final double RANGE = 9;
    final double MINI_RANGE = 7;
    final float BASE_DAMAGE = 14;
    final float MINI_DAMAGE = 6;

    double boost = 1.5;
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public RapierWeaponItem(Settings settings) {
        super(WeaponMaterials.RAPIER, 6, -2.2F, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.2, Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 6, Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)1F, Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", (double)-0.5F, Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d origin = user.getEyePos();
        Vec3d look   = user.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(RANGE));
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Riposte, stack) == 0) {
                if (!(world instanceof ServerWorld serverWorld)) {
                    return super.use(world, user, hand);
                }
                if (WeaponChargeComponent.RAPIER >= WeaponChargeComponent.MAX_RAPIER) {
                    WeaponChargeComponent.UseRapier(100);
                    user.setVelocity(
                            lookingDirection.x * boost,
                            lookingDirection.y * boost * 0.6f,
                            lookingDirection.z * boost
                    );
                    user.velocityModified = true;
                    user.setSwimming(false);

                    var hit = world.raycast(new RaycastContext(
                            origin, end,
                            RaycastContext.ShapeType.COLLIDER,
                            RaycastContext.FluidHandling.NONE,
                            user
                    ));
                    serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 3.0f, 1.0f);
                    int particleCount = (int) (RANGE * 2.5);
                    for (int i = 0; i < particleCount; i++) {
                        double t = i / 2.5;
                        Vec3d pos = origin.add(look.multiply(t));
                        serverWorld.spawnParticles(ParticleTypes.SWEEP_ATTACK, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
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
                        //impact = directTarget.getPos();
                    }
                }
                return super.use(world, user, hand);
            } else {
                if (!(world instanceof ServerWorld serverWorld)) {
                    return super.use(world, user, hand);
                }
                    if (WeaponChargeComponent.RAPIER >= WeaponChargeComponent.HALF_RAPIER) {
                        WeaponChargeComponent.UseRapier(50);
                        user.setVelocity(
                                   (lookingDirection.x * boost *.75),
                                (lookingDirection.y * boost *.75) * 0.6f,
                                   (lookingDirection.z * boost *.75)
                        );
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 1));
                        user.velocityModified = true;
                        user.setSwimming(false);

                            var hit = world.raycast(new RaycastContext(
                                    origin, end,
                                    RaycastContext.ShapeType.COLLIDER,
                                    RaycastContext.FluidHandling.NONE,
                                    user
                            ));
                            serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 3.0f, 1.0f);
                            int particleCount = (int) (RANGE * 2.5);
                            for (int i = 0; i < particleCount; i++) {
                                double t = i / 2.5;
                                Vec3d pos = origin.add(look.multiply(t));
                                serverWorld.spawnParticles(ParticleTypes.SWEEP_ATTACK, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
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
                                if (d < minDist && d <= MINI_RANGE) {
                                    minDist = d;
                                    directTarget = e;
                                }
                            }

                            if (directTarget != null) {
                                directTarget.damage(serverWorld.getDamageSources().mobAttack(user), MINI_DAMAGE);
                                directTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 140, 2));
                                //impact = directTarget.getPos();
                            }
                        return super.use(world, user, hand);


                }
                return super.use(world, user, hand);

            }
        }
        return super.use(world, user, hand);
    }


    public float getAttackDamage() {
        return 6F;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!attacker.getWorld().isClient) {
            WeaponChargeComponent.IncrementRapier(10);

        }
        return super.postHit(stack, target, attacker);
    }

    public com.google.common.collect.Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) WeaponChargeComponent.RAPIER / WeaponChargeComponent.MAX_RAPIER * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        int red = (int) (200);
        int blue = (int) (200);
        int green = (int) (200);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }

}