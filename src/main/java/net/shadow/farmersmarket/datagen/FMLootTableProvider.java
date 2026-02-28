package net.shadow.farmersmarket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.shadow.farmersmarket.block.ModBlocks;
import net.shadow.farmersmarket.item.ModItems;

public class FMLootTableProvider extends FabricBlockLootTableProvider {
    public FMLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COMPACT_IRON, copperLikeOreDrops(ModBlocks.COMPACT_IRON, Items.RAW_IRON));
        addDrop(ModBlocks.STARRY_OBSIDIAN, nonSilkTouch(ModBlocks.STARRY_OBSIDIAN, ModItems.SHATTERED_STARS));
        addDrop(ModBlocks.CRYSTALIZED_ABYSS, nonSilkTouch(ModBlocks.CRYSTALIZED_ABYSS, ModItems.ABYSS_SHARDS));
    }
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(3.0f, 8.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
    public LootTable.Builder nonSilkTouch(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(1.0f, 1.0f))))));
    }
}
