package net.shadow.farmersmarket.item.trinkets.endstuff;

import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class Ender_Crown extends TrinketItem {
    public Ender_Crown(Item.Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +30% luck
        modifiers.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(uuid, "farmersmarket:luck", 0.3, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +15% Knockback Resistance
        modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uuid, "farmersmarket:knockbackres", 0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +5 Hearts
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "farmersmarket:healthboost", 10, EntityAttributeModifier.Operation.ADDITION));
        // +.5 Attack Range
        modifiers.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(uuid, "farmersmarket:attackrange", 0.5, EntityAttributeModifier.Operation.ADDITION));
        // +10% attack damage
        modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "farmersmarket:attackdmg", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;

    }



}
