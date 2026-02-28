package net.shadow.farmersmarket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.shadow.farmersmarket.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class FMBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public FMBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COMPACT_IRON)
                .add(ModBlocks.STARRY_OBSIDIAN)
                .add(ModBlocks.CRYSTALIZED_ABYSS);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.COMPACT_IRON)
                .add(ModBlocks.CRYSTALIZED_ABYSS)
                .add(ModBlocks.STARRY_OBSIDIAN);
    }
}
