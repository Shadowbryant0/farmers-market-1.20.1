package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BowAttackGoal.class)
public abstract class ArbalestShootSkel {


    @Shadow @Final private LivingEntity actor;

    @ModifyExpressionValue(
            method = "isHoldingBow",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;isHolding(Lnet/minecraft/item/Item;)Z"))


    protected boolean HoldingCross(boolean original) {

        ItemStack itemStack = actor.getStackInHand(Hand.MAIN_HAND);
        if(itemStack.equals(new ItemStack(ModItems.ARBALESTDESCENDANT))) {

            return true;
        } else if (itemStack.equals(new ItemStack(Items.CROSSBOW))) {
            return true;

        }


        return original;
    }
}

