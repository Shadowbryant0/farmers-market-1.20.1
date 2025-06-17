package net.shadow.farmersmarket.item.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.shadow.farmersmarket.item.ModItems;

public class RapierWeaponItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 100;


    public RapierWeaponItem(Settings settings) {
        super(RapierMat.INSTANCE, 2, -2.3F, settings);
    }
// right click is medium dash that deals dmg and 0s your vel so it's like a stab
    double boost = 3.5d;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            Box box = user.getBoundingBox();
            user.getWorld().getOtherEntities(user, box);
            Vec3d lookingDirection = user.getRotationVec(1.0f);

                user.setVelocity(
                        lookingDirection.x * boost,
                        lookingDirection.y * boost * 0.4f,
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