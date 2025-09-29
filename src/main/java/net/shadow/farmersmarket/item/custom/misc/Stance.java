package net.shadow.farmersmarket.item.custom.misc;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

import java.util.UUID;

public class Stance  extends Item {
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public Stance(Settings settings) {
        super(settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)1.5F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", (double)0.5F, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    public com.google.common.collect.Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
