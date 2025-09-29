package net.shadow.farmersmarket.item.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class turtleShellRing extends TrinketItem {
    public turtleShellRing(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // -20% speed
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "farmersmarket:speed", -0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // -15% attack speed
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "farmersmarket:attackspeed", -0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +20% Armor
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "farmersmarket:armor", 0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +10% Armor Toughness
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "farmersmarket:toughness", 1.0, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;

    }


}
