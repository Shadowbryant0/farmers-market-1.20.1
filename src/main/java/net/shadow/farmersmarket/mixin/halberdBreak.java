package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.GreatswordClass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class halberdBreak {



    @ModifyReturnValue(method = "disablesShield", at = @At("RETURN"))
    private boolean halberdBreak(boolean original) {
        return original || this.getMainHandStack().getItem() instanceof GreatswordClass;
    }

    public abstract ItemStack getMainHandStack();
}

