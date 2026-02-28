package net.shadow.farmersmarket.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.shadow.farmersmarket.block.ModBlocks;
import net.shadow.farmersmarket.world.biome.FMBiomes;

public class FMMaterialRules {
    private static final MaterialRules.MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRules.MaterialRule BLACK_CONCRETE = makeStateRule(Blocks.BLACK_CONCRETE);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, OBSIDIAN), OBSIDIAN);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(FMBiomes.SHADOW_REALM_BIOME),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, OBSIDIAN)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, OBSIDIAN)),

                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GRASS_BLOCK)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
