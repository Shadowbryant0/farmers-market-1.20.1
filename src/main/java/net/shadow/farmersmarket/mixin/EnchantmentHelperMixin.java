package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.random.Random;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters.KnucklesCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    private static Enchantment enchantments = null;


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
            return item instanceof SwordItem;
        }
        if (storedEnchantment == FarmersMarketEnchants.PrimalDesires) {
            return item instanceof AxeItem;
        }

        return original.call(enchantmentTarget, item);
    }
}