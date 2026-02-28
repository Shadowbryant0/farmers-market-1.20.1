package net.shadow.farmersmarket.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class CryingObsidianGlowIncrease {

    @Shadow
    public abstract Block getBlock();


    @Shadow
    protected abstract BlockState asBlockState();


    @Inject(method = "getLuminance", at = @At("HEAD"), cancellable = true)
    private void getCustomLuminance(CallbackInfoReturnable<Integer> cir) {
        if (this.getBlock() == Blocks.CRYING_OBSIDIAN || this.getBlock() == Blocks.RESPAWN_ANCHOR) {
            cir.setReturnValue(16);
        }
    }
}