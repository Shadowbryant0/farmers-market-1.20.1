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
import net.shadow.farmersmarket.item.ModItems;

import java.util.Objects;

public class GreatswordClass extends SwordItem {
    private static final int COOLDOWN_TICKS = 200;

    //  right click is fast short dash that slams into enemies (custom stun effect)
    public GreatswordClass(Item.Settings settings) {
        super(ToolMaterials.NETHERITE, 7, -3.3F, settings);
    }

    double boost = 4.0d;
    double notRightDimensionDebuff = 1.0d;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            RegistryKey<DimensionType> overworld = DimensionTypes.OVERWORLD;
            user.setVelocity(
                    lookingDirection.x * boost,
                    lookingDirection.y * boost * 0.4f,
                    lookingDirection.z * boost
            );



            user.velocityModified = true;
            user.setSwimming(false);
        }
        {
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        }

        return super.use(world, user, hand);
    }


}

