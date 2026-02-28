package net.shadow.farmersmarket.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.shadow.farmersmarket.world.FMPlacedFeatures;
import net.shadow.farmersmarket.world.biome.FMBiomes;

public class FMOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FMBiomes.SHADOW_REALM_BIOME),
                GenerationStep.Feature.UNDERGROUND_ORES, FMPlacedFeatures.CRYING_OBSIDIAN_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FMBiomes.SHADOW_REALM_BIOME),
                GenerationStep.Feature.UNDERGROUND_ORES, FMPlacedFeatures.COMPACT_IRON);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FMBiomes.SHADOW_REALM_BIOME),
                GenerationStep.Feature.UNDERGROUND_ORES, FMPlacedFeatures.STARRY_OBSIDIAN_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FMBiomes.SHADOW_REALM_BIOME),
                GenerationStep.Feature.UNDERGROUND_ORES, FMPlacedFeatures.CRYSTALIZED_ABYSS_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, FMPlacedFeatures.COMPACT_IRON);

    }
}
