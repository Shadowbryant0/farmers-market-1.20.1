package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShifts.GearShift;
import net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters.KnucklesCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {


    @Unique
    private static Enchantment storedEnchantment;
    @WrapOperation(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;isAvailableForRandomSelection()Z"))
    private static boolean FarmersMarket$storeEnchants(Enchantment enchantment, Operation<Boolean> original) {
        boolean originalResult = original.call(enchantment);


        if (originalResult) {
            storedEnchantment = enchantment;
        }
        return originalResult;
    }
    @WrapOperation(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
    private static boolean FarmersMarket$enchantmentSelection(EnchantmentTarget enchantmentTarget, Item item, Operation<Boolean> original) {
        //if (storedEnchantment == FarmersMarketEnchants.Forging) {
        //    return item instanceof PickaxeItem;
        //}
        if (storedEnchantment == Enchantments.SWEEPING) {
            return (!(item instanceof KnucklesCommon) && item instanceof SwordItem);
        }
        if (storedEnchantment == FarmersMarketEnchants.HuntersLullabyEnchantment) {
            return (item instanceof SwordItem || item instanceof GearShift);
        }
        if (storedEnchantment == FarmersMarketEnchants.PrimalDesires) {
            return item instanceof AxeItem;
        }
        if(item.asItem() == ModItems.GEARSHIFT){
            return original.call(enchantmentTarget, ModItems.GEARSHIFTED) || original.call(enchantmentTarget, ModItems.GEARSHIFT);
        }else
        if (item.asItem() == ModItems.GEARSHIFTED) {
            return original.call(enchantmentTarget, ModItems.GEARSHIFTED) ||original.call(enchantmentTarget, ModItems.GEARSHIFT);
        }
//        if(storedEnchantment.isAcceptableItem(new ItemStack(ModItems.GEARSHIFT))&& original.call(enchantmentTarget, item) == false){
//            if(item instanceof GearShifted) return true;
//        }
        //the hoe was used in the anvil once, so 1 anvil cost
        //if i change forms, it will show the sword was used once

        return original.call(enchantmentTarget, item);
    }
}