package net.shadow.farmersmarket.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;

import java.awt.*;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

public class AceofSpadesClass  extends ShovelItem {
    private static final int COOLDOWN_TICKS = 240;

    public AceofSpadesClass(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 3, -2.4F, settings);
    }
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)) {



            {
                user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }




            user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 10, user));
            user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 10, user));
            user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 10, user));
            user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 10, user));
            user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 10, user));


        }
        return super.useOnEntity(stack, user, entity, hand);
    }





    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addVelocity(attacker.getEyePos().subtract(target.getPos()).normalize().multiply(-0.3));
        target.velocityModified = true;
        return super.postHit(stack, target, attacker);
    }
}
