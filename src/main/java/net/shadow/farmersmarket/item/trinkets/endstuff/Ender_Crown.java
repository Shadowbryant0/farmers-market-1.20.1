package net.shadow.farmersmarket.item.trinkets.endstuff;

import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.shadow.farmersmarket.FarmersMarket;

import java.util.Optional;
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
        modifiers.put(FarmersMarket.ENDERMAN, new EntityAttributeModifier(uuid, "farmersmarket:enderman", 2, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;

    }

    public static boolean isWearingTrinket(LivingEntity livingEntity) {
        return getTrinket(livingEntity) != ItemStack.EMPTY;
    }
    public static ItemStack getTrinket(LivingEntity livingEntity) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        if (component.isPresent()) {
            for (Pair<SlotReference, ItemStack> pair : component.get().getAllEquipped()) {
                if (pair.getRight().getItem() instanceof Ender_Crown) {
                    return pair.getRight();
                }
            }
        }
        return ItemStack.EMPTY;
    }

}
