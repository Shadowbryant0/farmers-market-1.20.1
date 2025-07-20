package net.shadow.farmersmarket.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.materials.Greatmat;

public class GreatswordClass extends SwordItem {
    private static final int COOLDOWN_TICKS = 200;

    //  right click is fast short dash that slams into enemies (custom stun effect)
    public GreatswordClass(Item.Settings settings) {
        super(Greatmat.INSTANCE, 3, -2.6F, settings);
    }

    double boost = 2.0d;
    double nerf = 0.5d;
    double notRightDimensionDebuff = 1.0d;
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }


    private static final float ATTACKSPEED = 3.15F;

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);

        return TypedActionResult.consume(itemStack);
        }





}

