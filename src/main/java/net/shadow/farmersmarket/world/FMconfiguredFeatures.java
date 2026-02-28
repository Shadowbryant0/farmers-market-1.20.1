package net.shadow.farmersmarket.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.block.ModBlocks;

import java.util.List;

public class FMconfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> COMPACT_IRON = registerKey("compact_iron");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYING_OBSIDIAN_KEY = registerKey("crying_obsidian_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STARRY_OBSIDIAN_KEY = registerKey("starry_obsidian_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTALIZED_ABYSS_KEY = registerKey("crystalized_abyss_key");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest obsidianReplacables = new BlockMatchRuleTest(Blocks.OBSIDIAN);

        List<OreFeatureConfig.Target> overworldRubyOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.COMPACT_IRON.getDefaultState()));
        List<OreFeatureConfig.Target> shadowRealmCrying =
                List.of(OreFeatureConfig.createTarget(obsidianReplacables, Blocks.CRYING_OBSIDIAN.getDefaultState()));
        List<OreFeatureConfig.Target> shadowRealmStarry =
                List.of(OreFeatureConfig.createTarget(obsidianReplacables, ModBlocks.STARRY_OBSIDIAN.getDefaultState()));
        List<OreFeatureConfig.Target> shadowRealmAbyss =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.CRYSTALIZED_ABYSS.getDefaultState()));

        register(context, COMPACT_IRON, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 8));
        register(context, CRYING_OBSIDIAN_KEY, Feature.ORE, new OreFeatureConfig(shadowRealmCrying, 12));
        register(context, STARRY_OBSIDIAN_KEY, Feature.ORE, new OreFeatureConfig(shadowRealmStarry, 3));
        register(context, CRYSTALIZED_ABYSS_KEY, Feature.ORE, new OreFeatureConfig(shadowRealmAbyss, 4));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FarmersMarket.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
