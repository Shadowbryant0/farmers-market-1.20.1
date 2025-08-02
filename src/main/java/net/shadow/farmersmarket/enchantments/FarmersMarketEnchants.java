package net.shadow.farmersmarket.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.enchantments.Axe.PrimalDesires;
import net.shadow.farmersmarket.enchantments.Axe.Starvation;
import net.shadow.farmersmarket.enchantments.sword.Devouring;
import net.shadow.farmersmarket.enchantments.Crossbow.ShockwaveEnchant;
import net.shadow.farmersmarket.enchantments.Hoe.FreshFieldsEnchantment;
import net.shadow.farmersmarket.enchantments.Pickaxe.Forging;
import net.shadow.farmersmarket.enchantments.sword.HuntersLullabyEnchantment;

public class FarmersMarketEnchants {


    public static final Enchantment HuntersLullabyEnchantment = register("hunterslullabyenchantment",
            new HuntersLullabyEnchantment());

    public static final Enchantment FreshFeildsEnchantment = register("freshfeildsenchantment",
            new FreshFieldsEnchantment());


    public static final Enchantment Forging = register("forging",
            new Forging());
    public static final Enchantment Starvation = register("starvation",
            new Starvation());
    public static final Enchantment Devouring = register("devouring",
            new Devouring());
    public static final Enchantment ShockwaveEnchant = register("shockwaveenchant",
            new ShockwaveEnchant());
    public static final Enchantment PrimalDesires = register("primaldesires",
            new PrimalDesires());


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(FarmersMarket.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        FarmersMarket.LOGGER.info("he will eat you... " + FarmersMarket.MOD_ID);
    }

    public static void registerModEnchants() {
    }
}
