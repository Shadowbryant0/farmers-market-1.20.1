package net.shadow.farmersmarket.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.item.custom.*;
import net.shadow.farmersmarket.sound.ModSounds;

public class ModItems {



    public static final Item SLIVER_FLESH = registerItem("sliver_flesh",
            new Item(new FabricItemSettings()));

    public static final Item CORRUPTEDFLESH = registerItem("corruptedflesh",
            new Item(new FabricItemSettings()));

    public static final Item RAPIER_UPGRADE = registerItem("rapier_upgrade",
            new Item(new FabricItemSettings()));

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
    public static final Item ARBALESTDESCENDANT = registerItem("arbalestdescendant",
            new ArbalestDescend(new FabricItemSettings().maxCount(1)));

    public static final Item HEXSPADE = registerItem("hexspade",
            new AceofSpadesClass(new FabricItemSettings().fireproof()));

    public static final Item CRACKED_TOTEM = registerItem("cracked_totem",
            new CrackedTotem(new FabricItemSettings().maxCount(1)));







    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FarmersMarket.MOD_ID, name), item);
    }


    public static void registerModItems() {
        FarmersMarket.LOGGER.info("Registering Mod Items for " + FarmersMarket.MOD_ID);

    }
}
