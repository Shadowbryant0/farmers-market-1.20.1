package net.shadow.farmersmarket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.shadow.farmersmarket.block.ModBlocks;
import net.shadow.farmersmarket.item.ModItems;

public class FMModelProvider extends FabricModelProvider {
    public FMModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COMPACT_IRON);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STARRY_OBSIDIAN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRYSTALIZED_ABYSS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CLOUD_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SHATTERED_STARS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRSTDIVINITY_FLIGHT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SECONDDIVINITY_RADIENT_LIGHT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ABYSS_SHARDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.THIRDIVINITY_GUARDIAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRSTOFTHEABYSS_SHACKLES, Models.GENERATED);
        itemModelGenerator.register(ModItems.SECONDOFTHEABYSS_SONG, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNREFINED_DIVINITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNTAMED_ABYSS, Models.GENERATED);
        itemModelGenerator.register(ModItems.THIRDOFTHEABYSS_VOID, Models.GENERATED);
        itemModelGenerator.register(ModItems.CLOUD_ITEM, Models.GENERATED);

        itemModelGenerator.register(ModItems.GEARSHIFTED, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GEARSHIFT, Models.HANDHELD);
    }
}
