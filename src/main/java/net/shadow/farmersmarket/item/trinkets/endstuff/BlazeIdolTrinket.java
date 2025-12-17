package net.shadow.farmersmarket.item.trinkets.endstuff;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.components.BlazeIdolComponent;

public class BlazeIdolTrinket extends TrinketItem {

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public BlazeIdolTrinket(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(FarmersMarket.BLAZE, new EntityAttributeModifier("farmersmarket:blaze", 1, EntityAttributeModifier.Operation.ADDITION));
        builder.put(FarmersMarket.ENDERMAN, new EntityAttributeModifier("farmersmarket:blaze", 1, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public com.google.common.collect.Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        if(BlazeIdolComponent.FIRE>0){
            return Math.round((float) BlazeIdolComponent.FIRE / BlazeIdolComponent.FIRE_MAX * 13); // full bar = max charge
        }
        return Math.round((float) BlazeIdolComponent.BLAZE / BlazeIdolComponent.BLAZE_MAX * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        if(BlazeIdolComponent.FIRE>0) {
            int red = (int) (255);
            int blue = (int) (0);
            int green = (int) (149);
            return (red << 16) | (green << 8) | blue; // RGB mix
        }

        // Glows between gold → magenta → red as it fills
        int red = (int) (255);
        int blue = (int) (0);
        int green = (int) (32);
            return (red << 16) | (green << 8) | blue; // RGB mix

    }
}
