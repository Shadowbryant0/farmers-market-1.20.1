package net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.KnuckleDusterComponent;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class KnuckledusterOffhandhandItem extends Item {

    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public KnuckledusterOffhandhandItem(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", (double)7.0F, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)-2.9F, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-0.3, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-0.3, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getWorld().isClient) {
            if (!(user.isOnGround())) {
                Vec3d lookingDirection = user.getRotationVec(1.0f);
                if (KnuckleDusterComponent.OFF > 0) {

                    user.swingHand(Hand.OFF_HAND, true);
                    user.velocityModified = true;
                    user.addVelocity(lookingDirection.x, lookingDirection.y, lookingDirection.z);
                    KnuckleDusterComponent.resetOffhand();
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient) {
            var host = user.getPos();
            if (user.isInSneakingPose()) {
                if (KnuckleDusterComponent.OFF_SPECIAL > 0) {
                    user.swingHand(Hand.OFF_HAND, true);
                    entity.damage(entity.getDamageSources().playerAttack(user), 12);
                    Vec3d push = entity.getPos().subtract(host).normalize().multiply(0.2);
                    entity.addVelocity(push.x, -.5, push.z);
                    entity.fallDistance = 5;
                    user.velocityModified = true;
                    entity.velocityModified = true;
                    KnuckleDusterComponent.resetOffSpecial();
                    return super.useOnEntity(stack, user, entity, hand);
                }
            }
            if (KnuckleDusterComponent.OFF > 0) {
                user.swingHand(Hand.OFF_HAND, true);
                KnuckleDusterComponent.enableMainSpecial(40);
                KnuckleDusterComponent.resetOffhand();
                entity.damage(entity.getDamageSources().playerAttack(user), 8);
                Vec3d push = entity.getPos().subtract(host).normalize().multiply(0.2);
                entity.velocityModified = true;
                entity.addVelocity(push.x, 0.1, push.z);
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (255);
        int blue = (int) (0);
        int green = (int) (32);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    public int getItemBarStep(ItemStack stack) {
        if(KnuckleDusterComponent.OFF_SPECIAL>0){
            return Math.round((float) KnuckleDusterComponent.OFF_SPECIAL / KnuckleDusterComponent.MAX_OFF * 13); // full bar = max charge
        }
        return Math.round((float) KnuckleDusterComponent.OFF / KnuckleDusterComponent.MAX_OFF * 13); // full bar = max charge
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.farmersmarket.knuckleduster_offhand"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
