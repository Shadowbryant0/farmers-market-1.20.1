package net.shadow.farmersmarket.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.enchantments.Armor.Elytra.Bracing;
import net.shadow.farmersmarket.enchantments.Armor.Leggings.LavaWader;
import net.shadow.farmersmarket.enchantments.Armor.CalciumInfused;
import net.shadow.farmersmarket.enchantments.Axe.*;
import net.shadow.farmersmarket.enchantments.Hoe.Rusted;
import net.shadow.farmersmarket.enchantments.Pickaxe.Forging;
import net.shadow.farmersmarket.enchantments.Shovel.Syphon;
import net.shadow.farmersmarket.enchantments.sword.*;
import net.shadow.farmersmarket.enchantments.Crossbow.ShockwaveEnchant;
import net.shadow.farmersmarket.enchantments.Hoe.FreshFieldsEnchantment;
import net.shadow.farmersmarket.enchantments.unique.WyrmsStride;

public class FarmersMarketEnchants {


    public static final Enchantment HuntersLullabyEnchantment = register("hunterslullabyenchantment",
            new HuntersLullabyEnchantment());

    public static final Enchantment FreshFeildsEnchantment = register("freshfeildsenchantment",
            new FreshFieldsEnchantment());


    public static final Enchantment Forging = register("forging",
            new Forging());

    public static final Enchantment Syphon = register("syphon",
            new Syphon());
    public static final Enchantment Riposte = register("riposte",
            new Riposte());
    public static final Enchantment Shout = register("shout",
            new Shout());
    public static final Enchantment Inferno = register("inferno",
            new Inferno());
    public static final Enchantment FreezerBurn = register("freezerburn",
            new FreezerBurn());
    public static final Enchantment Sharpen = register("sharpen",
            new Sharpen());
    public static final Enchantment Rusted = register("rusted",
            new Rusted());


    public static final Enchantment Starvation = register("starvation",
            new Starvation());
    public static final Enchantment Devouring = register("devouring",
            new Devouring());
    public static final Enchantment ShockwaveEnchant = register("shockwaveenchant",
            new ShockwaveEnchant());
    public static final Enchantment PrimalDesires = register("primaldesires",
            new PrimalDesires());
    public static final Enchantment JagerderSchuldigen = register("jagerderschuldigen",
            new JagerderSchuldigen());
    public static final Enchantment CalciumInfused = register("calciuminfused",
            new CalciumInfused());
    public static final Enchantment Bracing = register("bracing",
            new Bracing());
    public static final Enchantment LavaWader = register("lavawader",
            new LavaWader());

    public static final Enchantment WyrmStride = register("wyrmstride",
            new WyrmsStride());
    public static final Enchantment ADAPTABILITY = register("adaptability",
            new WyrmsStride());
    public static final Enchantment TRUESIGHT = register("truesight",
            new TrueSight());


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(FarmersMarket.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        FarmersMarket.LOGGER.info("he will eat you... " + FarmersMarket.MOD_ID);
    }

    public static void registerModEnchants() {
    }
}
