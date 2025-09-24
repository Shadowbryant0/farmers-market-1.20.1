package net.shadow.farmersmarket.item.custom.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.materials.CleaverMat;
import java.util.UUID;

public class ButcheringCleaver extends SwordItem {
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ButcheringCleaver(Settings settings) {
        super(CleaverMat.INSTANCE, 2, -1.9f, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -1.9F, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 5, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-.5F, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", (double)-0.25F, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    public com.google.common.collect.Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    double boost = 1.5;
    private static final int COOLDOWN_TICKS = 24;
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            Vec3d lookingDirection = user.getRotationVec(1.0f);

            if(!user.isSneaking()) {
                user.setVelocity(
                        lookingDirection.x * boost,
                        lookingDirection.y * boost * 0.6f,
                        lookingDirection.z * boost
                );
                {
                    user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                }
            }
            if(user.isSneaking()) {
                user.setVelocity(
                        lookingDirection.x * -boost,
                        lookingDirection.y * -boost * 0.8f,
                        lookingDirection.z * -boost
                );
                {
                    user.getItemCooldownManager().set(this, 58);
                }
            }


            user.velocityModified = true;
            user.setSwimming(false);

        }

        return super.use(world, user, hand);
    }

    public boolean isCritical(LivingEntity user) {
        return (user.getVelocity().getY()) <= 0;
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof PlayerEntity player){
            if (isCritical(attacker) && player.getItemCooldownManager().isCoolingDown(this)) {
                target.damage(target.getDamageSources().mobAttack(attacker), 10);
                return super.postHit(stack, target, attacker);
            }
        }
        if (isCritical(attacker)) {
            target.damage(target.getDamageSources().magic(), 3);
            return super.postHit(stack, target, attacker);
        }
        return super.postHit(stack, target, attacker);
    }
}
