package net.shadow.farmersmarket.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup FARMERS_MARKET = Registry.register(Registries.ITEM_GROUP, new Identifier(FarmersMarket.MOD_ID, "farmers_market"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.farmers_market"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {

                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.MADE_TO_HATE_YOU_DISC);

                        entries.add(ModItems.RAPIER);

                        entries.add(ModBlocks.BLOCK_OF_RUBY);

                    }).build());
    public static void registerItemGroups() {
        FarmersMarket.LOGGER.info("registering Item Groups for " + FarmersMarket.MOD_ID);
    }
}
