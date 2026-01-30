package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Mouse.class)
public class CancleMouseScrollingMixin {

//    @Inject(method = "onMouseScroll", at =@At("HEAD"), cancellable = true)
//    private static void ScrollInvStopper(long window, double horizontal, double vertical, CallbackInfo ci) {
//        if(MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player){
//            if(HasWeak.Suff(player)){
//                ci.cancel();
//            }
//        }
//    }
}