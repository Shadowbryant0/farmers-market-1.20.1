package net.shadow.farmersmarket.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.block.BedBlock;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.item.custom.ExecutionersAxeClass;
import net.shadow.farmersmarket.item.custom.GreatswordClass;
import net.shadow.farmersmarket.item.custom.RapierWeaponItem;
import net.shadow.farmersmarket.sound.ModSounds;

public class ModItems {

    public static final Item RUBY = registerItem("ruby",
            new Item(new FabricItemSettings()));

    public static final Item RAW_RUBY = registerItem("raw_ruby",
            new Item(new FabricItemSettings()));

    public static final Item SLIVER_FLESH = registerItem("sliver_flesh",
            new Item(new FabricItemSettings()));

    public static final Item VEINPIERCER = registerItem("veinpeircer",
            new RapierWeaponItem(new FabricItemSettings()));

    public static final Item BLOODHOUNDAXE = registerItem("bloodhoundaxe",
            new ExecutionersAxeClass(new FabricItemSettings()));

    public static final Item GREATSWORD = registerItem("greatsword",
            new GreatswordClass(new FabricItemSettings()));

    public static final Item MADE_TO_HATE_YOU_DISC = registerItem("made_to_hate_you_disc",
            new MusicDiscItem(7, ModSounds.MADE_TO_HATE_YOU, new FabricItemSettings().maxCount(1), 191));

private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
    entries.add(RUBY);
    entries.add(RAW_RUBY);
}


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FarmersMarket.MOD_ID, name), item);
    }


    public static void registerModItems() {
        FarmersMarket.LOGGER.info("Registering Mod Items for " + FarmersMarket.MOD_ID);

    }
}
