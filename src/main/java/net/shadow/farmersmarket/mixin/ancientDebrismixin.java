package net.shadow.farmersmarket.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class ancientDebrismixin {

    @Shadow
    public abstract Block getBlock();

    @Inject(at = @At("HEAD"), method = "getPistonBehavior", cancellable = true)
	private void init(CallbackInfoReturnable<PistonBehavior> cir) {
        if (this.getBlock() == Blocks.ANCIENT_DEBRIS) {
            cir.setReturnValue(PistonBehavior.BLOCK);
        }
	}
}