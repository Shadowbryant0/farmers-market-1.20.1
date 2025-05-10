package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class RapierWeaponItem extends Item {
    public RapierWeaponItem(Settings settings) {
        super(settings.maxCount(1));
    }

    double boost = 4.0d;
    double notRightDimensionDebuff = 1.0d;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            RegistryKey<DimensionType> the_nether = DimensionTypes.THE_NETHER;
                user.setVelocity(
                        lookingDirection.x * boost,
                        lookingDirection.y * boost * 0.6f,
                        lookingDirection.z * boost
                );

                user.setVelocity(
                        lookingDirection.x * boost / notRightDimensionDebuff,
                        lookingDirection.y * boost * 0.6f/ notRightDimensionDebuff,
                        lookingDirection.z * boost / notRightDimensionDebuff
                );

            user.velocityModified = true;
            user.setSwimming(false);
        }
            if (user.isFallFlying()) {
                user.getItemCooldownManager().set(ItemStack.class(RapierWeaponItem), 1000;
            } else {
                user.getItemCooldownManager().set(ItemStack.class(RapierWeaponItem), 200;
            }

        return super.use(world, user, hand);
    }
}