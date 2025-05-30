package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class GreatswordClass extends SwordItem {
    private static final int COOLDOWN_TICKS = 200;


    public GreatswordClass(Item.Settings settings) {
        super(ToolMaterials.NETHERITE, 9, -3.8F, settings);
    }

    double boost = 4.0d;
    double notRightDimensionDebuff = 1.0d;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            RegistryKey<DimensionType> the_nether = DimensionTypes.THE_NETHER;
            user.setVelocity(
                    lookingDirection.x * boost,
                    lookingDirection.y * boost * 0.4f,
                    lookingDirection.z * boost
            );

            user.setVelocity(
                    lookingDirection.x * boost / notRightDimensionDebuff,
                    lookingDirection.y * boost * 0.4f/ notRightDimensionDebuff,
                    lookingDirection.z * boost / notRightDimensionDebuff
            );

            user.velocityModified = true;
            user.setSwimming(false);
        }
        {
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        }

        return super.use(world, user, hand);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x550000 ));
    }
}