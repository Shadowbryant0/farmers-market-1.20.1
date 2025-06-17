package net.shadow.farmersmarket.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class SwordBlockPos {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void render(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            if (player.getStackInHand(hand).isOf(ModItems.GREATSWORD) || player.getStackInHand(hand).isOf(ModItems.MAINSWORD)) {
                if (!player.isUsingItem() && !player.handSwinging) {
                    cir.setReturnValue(BipedEntityModel.ArmPose.EMPTY);
                } else if (player.handSwinging) {
                    cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
                }
            }
        }
    //register(Items.SHIELD, new Identifier("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
}

