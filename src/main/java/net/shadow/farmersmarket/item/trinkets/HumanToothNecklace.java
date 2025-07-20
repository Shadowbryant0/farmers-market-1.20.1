package net.shadow.farmersmarket.item.trinkets;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.ibm.icu.impl.locale.XCldrStub;
import dev.emi.trinkets.api.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;


import java.util.Map;
import java.util.UUID;

public class HumanToothNecklace extends TrinketItem {
    public HumanToothNecklace(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // -10% luck
        modifiers.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(uuid, "farmersmarket:luck", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // -15% toughness
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "farmersmarket:armor_toughness", -0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +10% attack speed
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "farmersmarket:attackspeed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;

    }


}
