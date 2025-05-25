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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class CustomCobwebBlock extends Block {

    public static final List<Potion> EFFECT_POTIONS = Arrays.asList(
            Potions.SLOWNESS,
            Potions.WEAKNESS,
            Potions.POISON,
            Potions.REGENERATION,
            Potions.HEALING,
            Potions.TURTLE_MASTER,
            Potions.SLOW_FALLING,
            Potions.FIRE_RESISTANCE



    );

    public CustomCobwebBlock(AbstractBlock.Settings settings) {
        super(settings);
    }


    public void onEntityCollision(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof LivingEntity living) {
            for (Potion potion : EFFECT_POTIONS) {
                for (StatusEffectInstance effect : potion.getEffects()) {
                    living.addStatusEffect(new StatusEffectInstance(
                            effect.getEffectType(),
                            effect.getDuration(),
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.shouldShowParticles()
                    ));
                }
            }
        }
    }
}
