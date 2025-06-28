package net.shadow.farmersmarket.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.enchantments.Axe.Intoxication;
import net.shadow.farmersmarket.enchantments.Hoe.FreshFieldsEnchantment;
import net.shadow.farmersmarket.enchantments.Pickaxe.Forging;
import net.shadow.farmersmarket.enchantments.sword.HuntersLullabyEnchantment;

public class FarmersMarketEnchants {


    public static final Enchantment HuntersLullabyEnchantment = register("hunterslullabyenchantment",
            new HuntersLullabyEnchantment());

    public static final Enchantment FreshFeildsEnchantment = register("freshfeildsenchantment",
            new FreshFieldsEnchantment());

    public static final Enchantment Intoxication = register("intoxicationenchant",
            new Intoxication());
    public static final Enchantment Forging = register("forging",
            new Forging());


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(FarmersMarket.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        FarmersMarket.LOGGER.info("he will eat you... " + FarmersMarket.MOD_ID);
    }

    public static void registerModEnchants() {
    }
}
