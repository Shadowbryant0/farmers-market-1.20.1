package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.shadow.farmersmarket.item.custom.weapons.BoopStick;
import net.shadow.farmersmarket.item.custom.weapons.FarmersScytheClass;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public class SweepingEdgeMixinMixin {
    @WrapOperation(method ="attack", constant = @Constant(classValue = SwordItem.class))
    private boolean SweepItems(Object object, Operation<Boolean> original) {
        if(object instanceof SweeplessSword){
            return false;
        }
        return original.call(object) || (object instanceof SweepingHoeItem) || (object instanceof SweepingPickaxelItem) || (object instanceof SweepingAxeItem) || (object instanceof SweepingShovelItem) || (object instanceof SweepingItem) || (object instanceof FarmersScytheClass);
    }
}