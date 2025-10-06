package net.shadow.farmersmarket.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.item.custom.misc.*;
import net.shadow.farmersmarket.item.custom.weapons.*;
import net.shadow.farmersmarket.item.trinkets.*;
import net.shadow.farmersmarket.item.trinkets.endstuff.Ender_Crown;

public class ModItems {



    public static final Item SLIVER_FLESH = registerItem("sliver_flesh",
            new Item(new FabricItemSettings()));
    public static final Item CRACKED_SKULL = registerItem("cracked_skull",
            new Item(new FabricItemSettings()));

    public static final Item CORRUPTEDFLESH = registerItem("corruptedflesh",
            new Item(new FabricItemSettings()));

    public static final Item FORGE_UPGRADE = registerItem("forge_upgrade",
            new Item(new FabricItemSettings()));
    public static final Item GRIEF = registerItem("grief",
            new Grief(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item VEINPIERCER = registerItem("veinpeircer",
            new Veinpiercer(new FabricItemSettings().fireproof()));

    public static final Item RAPIER = registerItem("rapier",
            new RapierWeaponItem(new FabricItemSettings().fireproof()));

    public static final Item BLOODHOUNDAXE = registerItem("bloodhoundaxe",
            new ExecutionersAxeClass(new FabricItemSettings().fireproof()));

    public static final Item GREATSWORD = registerItem("greatsword",
            new GreatswordClass(new FabricItemSettings().fireproof()));

    public static final Item MAINSWORD = registerItem("mainsword",
            new MainswordClass(new FabricItemSettings().fireproof()));
    public static final Item ALTSWORD = registerItem("altsword",
            new GreatswordClass(new FabricItemSettings().fireproof()));


    public static final Item HEXSPADE = registerItem("hexspade",
            new AceofSpadesClass(new FabricItemSettings().fireproof()));
    public static final Item BOOP = registerItem("boop",
            new BoopStick(new FabricItemSettings().fireproof()));

    public static final Item TOOTHPICK = registerItem("toothpick",
            new ToothPickItem(new FabricItemSettings().fireproof()));
    public static final Item BROADAXE = registerItem("broadaxe",
            new BroadAxe(new FabricItemSettings().fireproof()));

    public static final Item CRACKED_TOTEM = registerItem("cracked_totem",
            new CrackedTotem(new FabricItemSettings().maxCount(1)));

    public static final Item GOATPENDENT = registerItem("goatpendent",
            new goatHornPendent(new FabricItemSettings().maxCount(1)));
    public static final Item HUMANPENDENT = registerItem("humanpendent",
            new HumanToothNecklace(new FabricItemSettings().maxCount(1)));
    public static final Item RABBITPENDENT = registerItem("rabbitpendent",
            new rabbitsfootPendent(new FabricItemSettings().maxCount(1)));
    public static final Item TURTLERING = registerItem("turtlering",
            new turtleShellRing(new FabricItemSettings().maxCount(1)));
    public static final Item BRUTERING = registerItem("brutering",
            new piglinBruteRing(new FabricItemSettings().maxCount(1)));
    public static final Item GOLDENRING = registerItem("goldenring",
            new goldenRing(new FabricItemSettings().maxCount(1)));
    public static final Item STANCE = registerItem("stance",
            new Stance(new FabricItemSettings().maxCount(1)));
    public static final Item ENDER_CROWN = registerItem("ender_crown",
            new Ender_Crown(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item FBOOK = registerItem("fbook",
            new Fbook(new FabricItemSettings().maxCount(1)));
    public static final Item CSBOOK = registerItem("csbook",
            new CSbook(new FabricItemSettings().maxCount(1)));
    public static final Item CLEANSING_STONE = registerItem("cleansing_stone",
            new Cleansing_Stone(new FabricItemSettings().maxCount(1)));

    public static final Item CRACKED_EGG = registerItem("cracked_egg",
            new Cracked_egg(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE = registerItem("egg_bundle",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE3 = registerItem("egg_bundle3",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE2 = registerItem("egg_bundle2",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_EMBRYO = registerItem("egg_embryo",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_SHARDS = registerItem("egg_shards",
            new Item(new FabricItemSettings().maxCount(8).fireproof()));
    public static final Item CROWN_PARTS = registerItem("crown_parts",
            new Crown_Parts(new FabricItemSettings().maxCount(1).fireproof()));


    public static final Item GAYSCYTHE = registerItem("gayscythe",
            new FarmerScytheItem(new FabricItemSettings().maxCount(1)));

    public static final Item TIDE = registerItem("tide",
            new Tide(new FabricItemSettings().maxCount(1).fireproof()));








    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FarmersMarket.MOD_ID, name), item);
    }


    public static void registerModItems() {
        FarmersMarket.LOGGER.info("Registering Mod Items for " + FarmersMarket.MOD_ID);

    }
}
