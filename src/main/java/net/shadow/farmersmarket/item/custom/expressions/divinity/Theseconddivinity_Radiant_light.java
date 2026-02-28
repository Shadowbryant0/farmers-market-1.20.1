package net.shadow.farmersmarket.item.custom.expressions.divinity;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.FarmersMarket;

public class Theseconddivinity_Radiant_light extends TrinketItem {

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public Theseconddivinity_Radiant_light(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FarmersMarket.DIVINITY, new EntityAttributeModifier("farmersmarket:divinity", 1, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return false;
    }

}
