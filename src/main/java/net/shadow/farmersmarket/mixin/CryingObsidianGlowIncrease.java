package net.shadow.farmersmarket.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.shadow.farmersmarket.block.BlockStateLambda;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.ToIntFunction;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class CryingObsidianGlowIncrease implements BlockStateLambda {

    @Shadow
    public abstract Block getBlock();

    @Shadow
    public abstract void updateNeighbors(WorldAccess world, BlockPos pos, int flags);

    @Inject(method = "getLuminance", at = @At("HEAD"), cancellable = true)
    private void getCustomLuminance(CallbackInfoReturnable<Integer> cir) {
        if (this.getBlock() == Blocks.CRYING_OBSIDIAN || this.getBlock() == Blocks.RESPAWN_ANCHOR) {
            cir.setReturnValue(14);
        }
    }
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void getCustomLuminance(ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (this.getBlock() == Blocks.CRYING_OBSIDIAN || this.getBlock() == Blocks.RESPAWN_ANCHOR) {
            this.updateNeighbors(world, pos, 1);
        }
    }

}