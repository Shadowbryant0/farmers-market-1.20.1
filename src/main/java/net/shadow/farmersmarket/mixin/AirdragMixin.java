package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.LivingEntity;
import net.shadow.farmersmarket.components.Weapons.AirDragComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class AirdragMixin {
    @ModifyExpressionValue(at = @At(ordinal = 1, value = "CONSTANT", args = "floatValue=0.91"), method = "travel")
    private float init(float original) {
        return AirDragComponent.DRAGBOOLIAN() ? 0.999f : original;
    }
    @ModifyExpressionValue(at = @At(value = "CONSTANT", args = "floatValue=0.02"), method = "getOffGroundSpeed")
    private float init2(float original) {
        return AirDragComponent.DRAGBOOLIAN() ? 1f : original;
    }
}