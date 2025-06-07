package net.shadow.farmersmarket.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;

public class ModEffects {
    //h
    public static final StatusEffect INTERFERENCE = registerStatusEffect("Interference",
            new InterferenceEffect(StatusEffectCategory.HARMFUL,0xa91101));


    //hh
    private  static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(FarmersMarket.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        FarmersMarket.LOGGER.info("Registering Mod Effects for " + FarmersMarket.MOD_ID);

    }
}