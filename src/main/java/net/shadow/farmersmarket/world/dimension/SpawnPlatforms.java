package net.shadow.farmersmarket.world.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.shadow.farmersmarket.FarmersMarket;

public class SpawnPlatforms extends PersistentState {
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return null;
    }
    public static void ensureSpawnPlatform_shadow(ServerWorld world, BlockPos centerPos) {
        // Check if there is already a safe landing at the target height
        // Scan a few blocks down because the structure might be offset (e.g. placed at
        // Y-4).
        for (int i = 1; i <= 10; i++) {
            if (!world.getBlockState(centerPos.down(i)).isAir()) {
                return; // Found a block below, so it's safe.
            }
        }

        net.minecraft.structure.StructureTemplateManager templateManager = world.getStructureTemplateManager();
        net.minecraft.util.Identifier templateId = new net.minecraft.util.Identifier(FarmersMarket.MOD_ID,
                "pocket_spawn_1");
        java.util.Optional<net.minecraft.structure.StructureTemplate> templateOptional = templateManager
                .getTemplate(templateId);

        if (templateOptional.isPresent()) {
            net.minecraft.structure.StructureTemplate template = templateOptional.get();
            net.minecraft.util.math.Vec3i size = template.getSize();

            // Center the structure on X and Z, keep Y at one block below spawn, then lower
            // by 4
            BlockPos placePos = centerPos.down().add(-size.getX() / 2, -4, -size.getZ() / 2);

            net.minecraft.structure.StructurePlacementData placementData = new net.minecraft.structure.StructurePlacementData();
            template.place(world, placePos, placePos, placementData,
                    net.minecraft.util.math.random.Random.create(world.getSeed()), 2);
        } else {
            // Fallback: Generate 3x3 Amethyst Platform
            System.out.println("SpawnPlatforms: Placing Fallback Platform at " + centerPos);
            // Check if the block directly below the player (the potential platform center)
            // is air.
            // If it's not air, we assume a platform or structure already exists and do
            // nothing.
            // This prevents overwriting player builds or existing platforms on re-entry.
            if (world.getBlockState(centerPos.down()).isAir()) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos platformPos = centerPos.down().add(x, 0, z);
                        // Only replace air or other non-solid blocks to be safe, though the center
                        // check should handle most cases.
                        if (world.getBlockState(platformPos).isAir()) {
                            world.setBlockState(platformPos,
                                    Blocks.CRYING_OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
