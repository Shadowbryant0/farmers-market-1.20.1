package net.shadow.farmersmarket.entity;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.entity.projectile.DragonTear;

public class FarmersMarketEntities {
    private static final RegistryKey<EntityType<?>> ARROW_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(FarmersMarket.MOD_ID, "tear"));
    public static final EntityType<DragonTear> TEAR = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(FarmersMarket.MOD_ID, "ocean"),
            EntityType.Builder.<DragonTear>create(DragonTear::new, SpawnGroup.MISC).setDimensions(0.5f,1.15f).build("tear")
    );
    public static void registerModEntities() {
        FarmersMarket.LOGGER.info("Registering Mod Entities for " + FarmersMarket.MOD_ID);
    }
}