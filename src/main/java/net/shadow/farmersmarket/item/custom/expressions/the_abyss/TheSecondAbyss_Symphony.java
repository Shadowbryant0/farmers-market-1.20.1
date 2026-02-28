package net.shadow.farmersmarket.item.custom.expressions.the_abyss;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.components.expressions.the_abyss.SecondAbyss_Song;
import net.shadow.farmersmarket.effects.FmEffects;
import net.shadow.farmersmarket.entity.SecondAbyss;

import java.util.List;

public class TheSecondAbyss_Symphony extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public TheSecondAbyss_Symphony(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FarmersMarket.ABYSS, new EntityAttributeModifier("farmersmarket:divinity", 1, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(SecondAbyss_Song.SUMMON <100) {
            spawnSoul(world, user);
            spawnSoul(world, user);
            spawnSoul(world, user);
            spawnSoul(world, user);
            spawnSoul(world, user);
            return TypedActionResult.pass(itemStack);
        }
        user.sendMessage(Text.literal("They reject your call.").formatted(Formatting.DARK_AQUA), true);
        return TypedActionResult.pass(itemStack);

    }
    private boolean spawnSoul(World world, PlayerEntity owner) {
        if (!(world instanceof ServerWorld serverWorld))
            return false;
        List<EntityType<?>> list = List.of(EntityType.HUSK, EntityType.HUSK, EntityType.ZOMBIFIED_PIGLIN, EntityType.BEE, EntityType.VINDICATOR, EntityType.SPIDER, EntityType.ZOMBIFIED_PIGLIN, EntityType.STRAY, EntityType.WITHER_SKELETON);
        EntityType<?> type = EntityType.HUSK;
        if (type != null) {
//            Entity entity = type.create(serverWorld);

            Entity entity = list.get(owner.getRandom().nextBetween(1, 9)).create(serverWorld);
            if (entity instanceof LivingEntity living) {
                // Restore data

                living.setHealth(living.getMaxHealth());

                // Set position to owner's looking pos (Safe Summon)
                Vec3d start = owner.getEyePos();
                Vec3d direction = owner.getRotationVector();
                double range = 2.5;
                Vec3d end = start.add(direction.multiply(range));

                BlockHitResult hit = world.raycast(new RaycastContext(
                        start, end,
                        RaycastContext.ShapeType.COLLIDER,
                        RaycastContext.FluidHandling.NONE,
                        owner));

                double x = end.x;
                double y = end.y;
                double z = end.z;

                if (hit.getType() == HitResult.Type.BLOCK) {
                    Vec3d hitPos = hit.getPos();
                    Vec3d safePos = hitPos.subtract(direction.multiply(0.5));
                    x = safePos.x;
                    y = safePos.y;
                    z = safePos.z;
                } else {
                    // Fallback at end of ray if no hit
                    x = end.x;
                    y = end.y;
                    z = end.z;
                    // Ensure consistency
                    if (x == end.x && y == end.y && z == end.z) {
                        // Should be fine
                    }
                }

                living.refreshPositionAndAngles(x, y, z, owner.getYaw(), 0);

                // Tame/Own logic
                if (entity instanceof SecondAbyss summoned) {
                    summoned.setSummonerUuid(owner.getUuid());
                }

                if (entity instanceof net.minecraft.entity.passive.TameableEntity tameable) {
                    tameable.setOwner(owner);
                } else if (entity instanceof net.minecraft.entity.passive.AbstractHorseEntity horse) {
                    horse.setOwnerUuid(owner.getUuid());
                    horse.setTame(true);
                }

                if (entity instanceof net.minecraft.entity.mob.Angerable angerable) {
                    angerable.stopAnger();
                }

                serverWorld.spawnEntity(living);
                switch (living) {
                    case WitherSkeletonEntity witherSkeletonEntity ->
                            living.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                    case StrayEntity strayEntity -> living.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                    case VindicatorEntity vindicatorEntity ->
                            living.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                    case BeeEntity beeEntity -> {
                    }
                    default -> living.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
                }
                living.addStatusEffect(new StatusEffectInstance(FmEffects.Summoned, 3200));
                SecondAbyss_Song.Summon();
                // Get name for message
                owner.sendMessage(Text.literal("They respond to your summons").formatted(Formatting.DARK_AQUA), true);
                return true;
            }
        }
        return false;
    }
}
