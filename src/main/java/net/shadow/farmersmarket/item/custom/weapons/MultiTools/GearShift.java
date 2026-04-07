package net.shadow.farmersmarket.item.custom.weapons.MultiTools;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

import java.util.Map;
import java.util.stream.Collectors;

public class GearShift extends HoeItem {
    public GearShift(Settings settings) {
        super(WeaponMaterials.GEARSHIFT, 1, -2.7F, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        if(world.isClient) return super.useOnBlock(context);
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        if(player == null) return super.useOnBlock(context);

        if (state.getBlock() instanceof CropBlock crop) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (!(dx == 0 && dy == 0 && dz == 0)) {
                            BlockPos newPos = pos.add(dx, 0, dz);
                            BlockState targetState = world.getBlockState(newPos);
                            if (targetState.getBlock() instanceof CropBlock surroundingCrops) {
                                if (surroundingCrops.getAge(targetState) == surroundingCrops.getMaxAge()) {
                                    world.breakBlock(newPos, true, player);
                                    world.setBlockState(newPos, surroundingCrops.getDefaultState());
                                    if (surroundingCrops.canGrow((ServerWorld) world, world.random, newPos, surroundingCrops.getDefaultState())) {
                                        surroundingCrops.grow((ServerWorld) world, world.random, newPos, surroundingCrops.getDefaultState());
                                        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.FreshFeildsEnchantment, stack) <= 0)
                                            continue;
                                        surroundingCrops.grow((ServerWorld) world, world.random, newPos, surroundingCrops.getDefaultState());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (crop.getAge(state) == crop.getMaxAge()) {
                world.breakBlock(pos, true, player);
                world.setBlockState(pos, crop.getDefaultState());
                if (crop.canGrow((ServerWorld) world, world.random, pos, crop.getDefaultState())) {
                    crop.grow((ServerWorld) world, world.random, pos, crop.getDefaultState());
                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.FreshFeildsEnchantment, stack) <= 0)
                        return super.useOnBlock(context);
                    crop.grow((ServerWorld) world, world.random, pos, crop.getDefaultState());
                }
            }
        }
    return super.useOnBlock(context);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()){
        user.setCurrentHand(hand); // Begin charging
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        ItemStack newstack = new ItemStack(ModItems.GEARSHIFTED);
        if(stack.hasEnchantments()) {
            Map<Enchantment, Integer> map =  EnchantmentHelper.get(stack).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            EnchantmentHelper.set(map, newstack);



                stack.decrement(1);
                newstack.setDamage(stack.getDamage());
                return newstack;

        }


        return newstack;
    }
}
