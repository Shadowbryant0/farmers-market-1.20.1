package net.shadow.farmersmarket.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.block.custom.CustomAngelBlock;
import net.shadow.farmersmarket.block.custom.ThreadWeaver;
import org.jetbrains.annotations.Nullable;

public class ModBlocks {

    public static final Block THREAD_WEAVER = registerBlock(
            "thread_weaver",
            new ThreadWeaver(FabricBlockSettings.create().collidable(true).pistonBehavior(PistonBehavior.IGNORE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.ANVIL)));

    public static final Block COMPACT_IRON = registerBlock(
            "compact_iron_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block STARRY_OBSIDIAN = registerBlock(
            "starry_obsidian",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).luminance((state) -> 15)));
    public static final Block CRYSTALIZED_ABYSS = registerBlock(
            "crystalized_abyss",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE).luminance((state) -> 5)));
    public static final Block CLOUD_BLOCK = registerBlock(
            "cloud_block",
            new CustomAngelBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).luminance((state) -> 5)));




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
