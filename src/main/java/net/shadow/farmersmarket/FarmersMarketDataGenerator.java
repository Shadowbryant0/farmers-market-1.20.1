package net.shadow.farmersmarket;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.shadow.farmersmarket.datagen.*;
import net.shadow.farmersmarket.world.biome.FMBiomes;
import net.shadow.farmersmarket.world.dimension.FMDimensions;
import net.shadow.farmersmarket.world.FMPlacedFeatures;
import net.shadow.farmersmarket.world.FMconfiguredFeatures;

public class FarmersMarketDataGenerator implements DataGeneratorEntrypoint {


    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FMWorldGenerator::new);

        pack.addProvider(FMBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(FMLootTableProvider::new);
        pack.addProvider(FMModelProvider::new);
    }
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, FMconfiguredFeatures::boostrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, FMPlacedFeatures::boostrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, FMBiomes::boostrap);
        registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, FMDimensions::bootstrapType);
    }
}
