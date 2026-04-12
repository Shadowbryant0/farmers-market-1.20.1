package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.random.Random;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShift;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShifted;
import net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters.KnucklesCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    private static Enchantment enchantments = null;


    private static Enchantment storedEnchantment;
    private static Enchantment weaponEnchantment;
    @WrapOperation(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;isAvailableForRandomSelection()Z"))
    private static boolean FarmersMarket$storeEnchants(Enchantment enchantment, Operation<Boolean> original) {
        boolean originalResult = original.call(enchantment);

        if((enchantment == Enchantments.FIRE_ASPECT || enchantment == Enchantments.KNOCKBACK|| enchantment == Enchantments.LOOTING)){
            weaponEnchantment = enchantment;
        }
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

//        if(storedEnchantment.isAcceptableItem(new ItemStack(ModItems.GEARSHIFT))&& original.call(enchantmentTarget, item) == false){
//            if(item instanceof GearShifted) return true;
//        }

        if(item instanceof GearShift && (!original.call(EnchantmentTarget.WEAPON, item))){
            return original.call(EnchantmentTarget.WEAPON, ModItems.GEARSHIFTED);
        }
        if(item instanceof GearShift) return storedEnchantment == Enchantments.FIRE_ASPECT;


        return original.call(enchantmentTarget, item);
    }
}