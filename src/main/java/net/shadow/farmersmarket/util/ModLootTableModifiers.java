package net.shadow.farmersmarket.util;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemPredicateArgumentType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Weighted;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import java.util.function.Predicate;

public class ModLootTableModifiers {
private static final Identifier PLAYER_DROPS_ID =
        new Identifier("minecraft", "entities/player");
    private static final Identifier VILLAGER_DROPS_ID =
            new Identifier("minecraft", "entities/villager");
    private static final Identifier IRON_DROPS_ID =
            new Identifier("minecraft", "entities/iron_golem");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, lootTableSource) -> {
if(PLAYER_DROPS_ID.equals(id)) {
    LootPool.Builder poolBuilder = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1))
            .conditionally(RandomChanceLootCondition.builder(.95f))
                    .with(ItemEntry.builder(ModItems.SLIVER_FLESH))
            .conditionally(RandomChanceLootCondition.builder(.5f))
            .with(ItemEntry.builder(ModItems.CRACKED_SKULL))
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
    tableBuilder.pool(poolBuilder.build());
}
            if(VILLAGER_DROPS_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(3))

                        .with(ItemEntry.builder(Items.EMERALD).conditionally(RandomChanceLootCondition.builder(.40f)))
                        .with(ItemEntry.builder(Items.CARVED_PUMPKIN).conditionally(RandomChanceLootCondition.builder(.05f)));

                tableBuilder.pool(poolBuilder.build());
            }
            if(IRON_DROPS_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))

                        .with(ItemEntry.builder(Items.IRON_BLOCK).conditionally(RandomChanceLootCondition.builder(.05f)));

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }


}