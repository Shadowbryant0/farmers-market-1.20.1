package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KeyBinding.class)
public class NoInvKeysMixin {
//    @Shadow
//    @Final
//    public static String INVENTORY_CATEGORY;
//    @Shadow
//    @Final
//    private String category;
//
//    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
//	private boolean NoInvKeybinds(boolean original) {
//        if(MinecraftClient.getInstance().getCameraEntity() instanceof PlayerEntity player){
//        if(HasWeak.Suff(player)) {
//            if(this.category.equals(INVENTORY_CATEGORY)){
//                return false;
//            }
//
//        }
//        }
//        return original;
//    }
}