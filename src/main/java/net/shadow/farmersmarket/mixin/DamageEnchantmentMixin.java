package net.shadow.farmersmarket.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
	@Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
	private void init(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof ShovelItem){
            cir.setReturnValue(true);
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}
