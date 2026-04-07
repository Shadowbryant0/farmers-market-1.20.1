package net.shadow.farmersmarket.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.MinecraftServer;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShift;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShifted;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class GearShiftMixin {
	@Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
	private void init(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof GearShift && cir.getReturnValue() == false){
            cir.setReturnValue(ModItems.GEARSHIFTED instanceof SwordItem);
        }
        if(stack.getItem() instanceof GearShifted && cir.getReturnValue() == false){
            cir.setReturnValue(ModItems.GEARSHIFT instanceof HoeItem);
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}