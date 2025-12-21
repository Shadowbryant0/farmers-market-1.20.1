package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.trinkets.BlindfoldOfSightItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerScreenHandler.class)
public class BlindfoldMixin {
	@Inject(at = @At(value = "TAIL"), method = "<init>(Lnet/minecraft/entity/player/PlayerInventory;ZLnet/minecraft/entity/player/PlayerEntity;)V")
	private static void init(PlayerInventory inventory, boolean onServer, PlayerEntity owner, CallbackInfo ci) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
        if(inventory.getStack(inventory.selectedSlot).isOf(ModItems.BLINDFOLD)){

        }
	}
}