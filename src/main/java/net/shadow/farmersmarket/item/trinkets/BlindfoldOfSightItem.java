package net.shadow.farmersmarket.item.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.item.trinkets.endstuff.EnderManPendent;

import java.util.Optional;
import java.util.UUID;

public class BlindfoldOfSightItem extends TrinketItem {
    public BlindfoldOfSightItem(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +10% luck
        modifiers.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(uuid, "farmersmarket:luck", 0.5, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // +15% Knockback Resistance
        modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uuid, "farmersmarket:knockback_resistance", 2, EntityAttributeModifier.Operation.ADDITION));
        modifiers.put(FarmersMarket.TRUESIGHT, new EntityAttributeModifier(uuid, "farmersmarket:truesight", 1, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;

    }

    public static boolean isWearingTrinket(LivingEntity livingEntity) {
        return getTrinket(livingEntity) != ItemStack.EMPTY;
    }
    public static ItemStack getTrinket(LivingEntity livingEntity) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        if (component.isPresent()) {
            for (Pair<SlotReference, ItemStack> pair : component.get().getAllEquipped()) {
                if (pair.getRight().getItem() instanceof BlindfoldOfSightItem) {
                    return pair.getRight();
                }
            }
        }
        return ItemStack.EMPTY;
    }

}
