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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.KnuckleDusterComponent;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.item.materials.KnucklesLevel;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class KnuckledusterMainhandItem extends KnucklesCommon {

    private final float attackDamage;
    private final float attackSpeed;
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public KnuckledusterMainhandItem(KnucklesLevel knucklesLevel, float attackDamage, Settings settings) {
        super(settings);
        this.attackDamage = attackDamage + knucklesLevel.getAttackDamage();
        this.attackSpeed = (float) (-3 + knucklesLevel.getAttackSpeed());
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-0.3, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-0.3, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
            if (attacker.isInSneakingPose()) {
                if (KnuckleDusterComponent.MAIN_SPECIAL > 0) {
                    KnuckleDusterComponent.resetMainSpecial();
                    KnuckleDusterComponent.enableOffSpecial(40);
                    target.velocityModified = true;
                    attacker.velocityModified = true;
                    target.setVelocity(0, 1, 0);
                    attacker.setVelocity(0, 1, 0);
                    return true;
                }
            }
            KnuckleDusterComponent.enableOffSwing(40);

        }
        return super.postHit(stack, target, attacker);
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
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
        return Math.round((float) KnuckleDusterComponent.MAIN_SPECIAL / KnuckleDusterComponent.MAX_OFF * 13); // full bar = max charge
    }
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.farmersmarket.knuckleduster_mainhand"));
        super.appendTooltip(stack, world, tooltip, context);
    }
    public boolean isDamageable() {
        return true;
    }
}
