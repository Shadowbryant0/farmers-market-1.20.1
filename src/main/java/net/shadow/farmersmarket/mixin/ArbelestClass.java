package net.shadow.farmersmarket.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WitherSkeletonEntity.class)
public interface ArbelestClass {
    //@

    //@Invoker("renderBakedItemModel")
    //void renderBakedItemModelInvoke(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrixStack, VertexConsumer vertexConsumer);
}