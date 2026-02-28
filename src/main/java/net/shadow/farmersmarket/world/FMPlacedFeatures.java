package net.shadow.farmersmarket.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.shadow.farmersmarket.FarmersMarket;

import java.util.List;

public class FMPlacedFeatures {

    public static final RegistryKey<PlacedFeature> CRYING_OBSIDIAN_KEY = registerKey("crying_obsidian_placed");
    public static final RegistryKey<PlacedFeature> STARRY_OBSIDIAN_KEY = registerKey("starry_obsidian_placed");
    public static final RegistryKey<PlacedFeature> CRYSTALIZED_ABYSS_KEY = registerKey("crystalized_abyss_key");
    public static final RegistryKey<PlacedFeature> COMPACT_IRON = registerKey("compact_iron_ore_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, COMPACT_IRON, configuredFeatureRegistryEntryLookup.getOrThrow(FMconfiguredFeatures.COMPACT_IRON),
                FMOrePlacement.modifiersWithCount(3, // Veins per Chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-20), YOffset.fixed(10))));
        register(context, CRYSTALIZED_ABYSS_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FMconfiguredFeatures.CRYSTALIZED_ABYSS_KEY),
                FMOrePlacement.modifiersWithCount(5 , // Veins per Chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-20), YOffset.fixed(10))));
        register(context, CRYING_OBSIDIAN_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FMconfiguredFeatures.CRYING_OBSIDIAN_KEY),
                FMOrePlacement.modifiersWithCount(35, // Veins per Chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(130))));
        register(context, STARRY_OBSIDIAN_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FMconfiguredFeatures.STARRY_OBSIDIAN_KEY),
                FMOrePlacement.modifiersWithCount(3, // Veins per Chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(218), YOffset.fixed(226))));

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FarmersMarket.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
