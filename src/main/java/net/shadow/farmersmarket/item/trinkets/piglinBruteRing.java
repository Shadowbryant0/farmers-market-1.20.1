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

public class piglinBruteRing extends TrinketItem {
    public piglinBruteRing(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +15% speed
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "farmersmarket:speed", 0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +15% attack dmg
        modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "farmersmarket:attackspeed", 0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +10% Armor Toughness
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "farmersmarket:attackspeed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // -10% Armor
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "farmersmarket:armor", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // -10% Armor Toughness
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "farmersmarket:toughness", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
         // -10% luck
        modifiers.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(uuid, "farmersmarket:luck", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;

    }


}
