package net.shadow.farmersmarket.item.custom.expressions.the_abyss;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.shadow.farmersmarket.FarmersMarket;

public class TheThirdAbyss_Void extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public TheThirdAbyss_Void(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FarmersMarket.ABYSS, new EntityAttributeModifier("farmersmarket:divinity", 1, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
