package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.shadow.farmersmarket.item.materials.BloodMat;

public class ExecutionersAxeClass extends AxeItem {
    private static final int COOLDOWN_TICKS = 100;

    public ExecutionersAxeClass(Item.Settings settings) {
        super(BloodMat.INSTANCE, 5, -3.1F, settings);
    }

// right click pulls entity closer and applies weakness

     @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
if (!user.getItemCooldownManager().isCoolingDown(this)) {
    entity.setVelocity(user.getEyePos().subtract(entity.getPos()).normalize().multiply(0.8));
    entity.velocityModified = true;

    {
        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
    }
}

        return super.useOnEntity(stack, user, entity, hand);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xF2EBE3 ));
    }

}
