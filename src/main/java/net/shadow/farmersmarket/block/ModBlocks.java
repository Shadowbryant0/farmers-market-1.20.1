package net.shadow.farmersmarket.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;

public class ModBlocks {
    public static final Block BLOCK_OF_RUBY = registerBlock("block_of_ruby",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block THREAD_WEAVER = registerBlock(
            "thread_weaver",
            new Block(FabricBlockSettings.create().collidable(true).pistonBehavior(PistonBehavior.IGNORE).strength(2.0F, 3.0F).sounds((BlockSoundGroup.ANVIL))));




    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(FarmersMarket.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(FarmersMarket.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
         FarmersMarket.LOGGER.info("registering mod blocks for " + FarmersMarket.MOD_ID);
    }
}
