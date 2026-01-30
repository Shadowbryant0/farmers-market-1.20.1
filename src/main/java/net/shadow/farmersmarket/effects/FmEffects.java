package net.shadow.farmersmarket.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;

public class FmEffects {
    //h
    public static final StatusEffect INTERFERENCE = registerStatusEffect("interference",
            new InterferenceEffect(StatusEffectCategory.HARMFUL,0xa91101));
    public static final StatusEffect TRUESIGHT = registerStatusEffect("truesight",
            new TrueSightEffect(StatusEffectCategory.NEUTRAL,0xa91101));
    public static final StatusEffect SUFFICATION = registerStatusEffect("suffication",
            new TrueSightEffect(StatusEffectCategory.NEUTRAL,0xa91101));


    //hh
    private  static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(FarmersMarket.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        FarmersMarket.LOGGER.info("Registering Mod Effects for " + FarmersMarket.MOD_ID);

    }
}