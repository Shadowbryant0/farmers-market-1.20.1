package net.shadow.farmersmarket.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.shadow.farmersmarket.FarmersMarket;

import java.util.OptionalLong;

public class FMDimensions {
    public static final RegistryKey<DimensionOptions> SHADOW_REALM_DIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(FarmersMarket.MOD_ID, "shadow_realm_dim"));
    public static final RegistryKey<World> SHADOW_REALM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(FarmersMarket.MOD_ID, "shadow_realm_key_dim"));
    public static final RegistryKey<DimensionType> SHADOW_REALM_DIM = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(FarmersMarket.MOD_ID, "shadow_realm_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(SHADOW_REALM_DIM, new DimensionType(
                OptionalLong.of(18000), // fixedTime
                false, // hasSkylight
                true, // hasCeiling
                false, // ultraWarm
                true, // natural
                0.5, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                64, // height
                128, // logicalHeight
                BlockTags.INFINIBURN_END, // infiniburn - allows nether portals
                DimensionTypes.THE_END_ID, // effectsLocation
                0.2f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0))); // piglinSafe = true
    }
}