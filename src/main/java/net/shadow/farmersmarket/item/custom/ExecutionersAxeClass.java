package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class ExecutionersAxeClass extends AxeItem {
    private static final int COOLDOWN_TICKS = 100;
    public ExecutionersAxeClass(Item.Settings settings) {
        super(ToolMaterials.NETHERITE, 7, -3.0F, settings);
    }

// right click pulls entity closer and applies weakness

     @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

            entity.setVelocity(user.getEyePos().subtract(entity.getPos()).normalize().multiply(0.8));
            entity.velocityModified = true;

        {
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        }

        return super.useOnEntity(stack, user, entity, hand);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xF2EBE3 ));
    }

}
