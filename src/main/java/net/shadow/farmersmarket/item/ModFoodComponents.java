package net.shadow.farmersmarket.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent FLESH = new FoodComponent.Builder().hunger(3).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,90,1),100f)
            .build();

    public static final FoodComponent STEW_FLESH = new FoodComponent.Builder().hunger(10).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,500,0),100f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,90,1),100f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,500,0),100f).alwaysEdible()
            .build();
    public static final FoodComponent STEW = new FoodComponent.Builder().hunger(8).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,120,0),100f)
            .build();

}