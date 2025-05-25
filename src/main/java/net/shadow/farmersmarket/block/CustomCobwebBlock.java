package net.shadow.farmersmarket.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CustomCobwebBlock extends Block {
    public CustomCobwebBlock(AbstractBlock.Settings settings) {
        super(settings);
    }


    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.slowMovement(state, new Vec3d(0.25, 0.05000000074505806, 0.25));
        {
            if (entity instanceof LivingEntity && entity.getType() != EntityType.CAVE_SPIDER && entity.getType() != EntityType.SPIDER) {
                entity.slowMovement(state, new Vec3d(0.800000011920929, 0.75, 0.800000011920929));
                double d = Math.abs(entity.getX() - entity.lastRenderX);
                double e = Math.abs(entity.getZ() - entity.lastRenderZ);
                if (d >= 0.003000000026077032 || e >= 0.003000000026077032) {
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));

                }
            }


        }
    }
}
