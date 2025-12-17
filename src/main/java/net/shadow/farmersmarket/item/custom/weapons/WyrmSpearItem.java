package net.shadow.farmersmarket.item.custom.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.SweepingItem;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.List;
import java.util.UUID;

public class WyrmSpearItem extends SweepingItem {

    private static final int COOLDOWN_TICKS = 40;
        public static final float ATTACK_DAMAGE = 8.0F;

    final float SWEEP_DAMAGE = 4;
    final double RANGE = 20;
    final float BASE_DAMAGE = 2;

    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
        private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

        public WyrmSpearItem(Item.Settings settings) {
            super(settings);
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", (double)8.0F, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)-2.9F, EntityAttributeModifier.Operation.ADDITION));
            builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)1F, EntityAttributeModifier.Operation.ADDITION));
            this.attributeModifiers = builder.build();
        }

        public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
            return !miner.isCreative();
        }

        public UseAction getUseAction(ItemStack stack) {

            if(FMEnchantCheck.getWyrmStride(stack)>0) {
                return UseAction.SPEAR;
            }
            return UseAction.NONE;
        }

        public int getMaxUseTime(ItemStack stack) {
            return 72000;
        }

        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            if (user instanceof PlayerEntity playerEntity) {
                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                if (i >= 10) {

                    if (WeaponChargeComponent.FIRE>0) {


                            float f = playerEntity.getYaw();
                            float g = playerEntity.getPitch();
                            float h = -MathHelper.sin(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
                            float k = -MathHelper.sin(g * ((float)Math.PI / 180F));
                            float l = MathHelper.cos(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
                            float m = MathHelper.sqrt(h * h + k * k + l * l);
                            float n = 3.0F;
                            h *= n / m;
                            k *= n / m;
                            l *= n / m;
                            playerEntity.addVelocity((double)h, (double)k, (double)l);
                            playerEntity.useRiptide(20);
                            if (playerEntity.isOnGround()) {
                                float o = 1.1999999F;
                                playerEntity.move(MovementType.SELF, new Vec3d((double)0.0F, (double)1.1999999F, (double)0.0F));
                            }

                            SoundEvent soundEvent;

                                soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;


                        user.velocityModified = true;
                            world.playSoundFromEntity((PlayerEntity)null, playerEntity, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    }


                }
            }
        }

        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack itemStack = user.getStackInHand(hand);
            int j = FMEnchantCheck.getWyrmStride(itemStack);
            if (j > 0) {

            if (WeaponChargeComponent.FIRE>0) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else {
                return TypedActionResult.fail(itemStack);
            }
            }else{
                Vec3d origin = user.getEyePos();
                Vec3d look   = user.getRotationVector().normalize();
                Vec3d end    = origin.add(look.multiply(RANGE));
                ItemStack stack = user.getStackInHand(hand);
                if (EnchantmentHelper.getEquipmentLevel(FarmersMarketEnchants.WyrmStride, user) == 0) {
                    if (!(world instanceof ServerWorld serverWorld)) return super.use(world, user, hand);
                    if(WeaponChargeComponent.WYRM>=WeaponChargeComponent.FIFTH_WYRM) {
                        WeaponChargeComponent.UseWYRM(20);
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
                            directTarget.setOnFireFor(4);
                            //impact = directTarget.getPos();
                        }
                        {
                            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                        }
                        return super.use(world, user, hand);
                    }
                }
                return TypedActionResult.fail(itemStack);
            }
        }



        public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
            if ((double)state.getHardness(world, pos) != (double)0.0F) {
                stack.damage(2, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }

            return true;
        }

        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
            return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
        }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(FMEnchantCheck.getWyrmStride(stack)>0){
            if(WeaponChargeComponent.WYRM>=WeaponChargeComponent.MAX_WYRM){
                WeaponChargeComponent.UseWYRM(100);
                target.setOnFireFor(10);
            }

        }

        if (!attacker.getWorld().isClient) {
            WeaponChargeComponent.IncrementWYRM(10);

        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
            if(WeaponChargeComponent.FIRE>0){
                return Math.round((float) WeaponChargeComponent.FIRE / 200 * 13); // full bar = max charge
            }
        return Math.round((float) WeaponChargeComponent.WYRM / WeaponChargeComponent.MAX_WYRM * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (255);
        int blue = (int) (0);
        int green = (int) (32);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}

