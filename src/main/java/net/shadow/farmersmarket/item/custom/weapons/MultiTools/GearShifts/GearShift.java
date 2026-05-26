package net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShifts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GearShift extends HoeItem {
    public GearShift(Settings settings) {
        super(WeaponMaterials.GEARSHIFT, 1, -2.7F, settings);
    }
    private static int SWORD_ANVIL;
    private static int HOE_ANVIL;
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
                                    world.spawnEntity(bonusWheat(context));
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
                world.spawnEntity(bonusWheat(context));
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
            Map<Enchantment, Integer> map =  EnchantmentHelper.get(stack).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); // reads the enchantments -> writes to a map to be read elsewhere
            HOE_ANVIL = stack.getRepairCost(); //sets repair cost to variable
            SWORD_ANVIL = stack.getOrCreateNbt().getInt("sword_anvil");// retrieves sword cost from nbt

            System.out.println("Sword cost:" + SWORD_ANVIL);// prints to konsol
            System.out.println("hoe cost:" + HOE_ANVIL);
            EnchantmentHelper.set(map, newstack); // saves enchantments// the map variable is the enchantment list
            newstack.getOrCreateNbt().putInt("hoe_anvil", HOE_ANVIL);// saves hoe cost as nbt on sword form
            newstack.setRepairCost(SWORD_ANVIL); // sets repair cost for the sword form
                stack.decrement(1);// removes current hoe form
                newstack.setDamage(stack.getDamage()); // sets durability of other form
                return newstack;

        }


        return newstack;
    }

    protected Entity bonusWheat(ItemUsageContext context){
        ItemStack stack = context.getStack();
        BlockPos pos =context.getBlockPos();
        if(!(context.getWorld()instanceof ServerWorld serverWorld)){
            return null;
        }
        int bonus = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
        PlayerEntity player = context.getPlayer();
        assert player != null;
        int roll = player.getRandom().nextBetween(1,20);
        ItemStack bonusDrop = null;
        if(roll>= (20-bonus)) {
            bonusDrop = new ItemStack(Items.WHEAT);
        }else {
            bonusDrop = new ItemStack(Items.WHEAT_SEEDS);
        }
            ItemEntity entity = new ItemEntity(serverWorld,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    bonusDrop);

        return entity;
    }
}
