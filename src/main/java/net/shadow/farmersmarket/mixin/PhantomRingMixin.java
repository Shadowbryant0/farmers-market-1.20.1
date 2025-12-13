package net.shadow.farmersmarket.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.shadow.farmersmarket.item.trinkets.PhantomRingTrinket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class PhantomRingMixin<T extends Entity> {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void TribalMasks$init(EntityRendererFactory.Context ctx, CallbackInfo ci) {
    }

    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"), cancellable = true)
    protected void TribalMasks$hideNames(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof LivingEntity living && PhantomRingTrinket.isWearingTrinket(living)) {
            ci.cancel();
        }
    }
    //both of these taken from Rats Mischeif github


}