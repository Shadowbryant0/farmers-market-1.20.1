package net.shadow.farmersmarket.item.custom;


import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.materials.RapierMat;

public class RapierWeaponItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 120;


    public RapierWeaponItem(Settings settings) {
        super(RapierMat.INSTANCE, 2, -2.3F, settings);
    }
// right click is medium dash that deals dmg and 0s your vel so it's like a stab
    double boost = 2d;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            Box box = user.getBoundingBox();
            user.getWorld().getOtherEntities(user, box);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 120, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
            Vec3d lookingDirection = user.getRotationVec(1.0f);

                user.setVelocity(
                        lookingDirection.x * boost,
                        lookingDirection.y * boost * 0.3f,
                        lookingDirection.z * boost
                );




            user.velocityModified = true;
            user.setSwimming(false);
        }
            {
                user.getItemCooldownManager().set(ModItems.RAPIER, COOLDOWN_TICKS);
                user.getItemCooldownManager().set(ModItems.VEINPIERCER, COOLDOWN_TICKS);
             }

        return super.use(world, user, hand);
    }

}