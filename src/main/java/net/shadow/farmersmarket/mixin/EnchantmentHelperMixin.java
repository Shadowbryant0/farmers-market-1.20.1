package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.ExecutionersAxeClass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
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
    private static boolean FarmersMarket$letTheEnchantDecide(EnchantmentTarget enchantmentTarget, Item item, Operation<Boolean> original) {
        if (storedEnchantment == FarmersMarketEnchants.Intoxication) {
            return item instanceof ExecutionersAxeClass;
        }
        return original.call(enchantmentTarget, item);
    }
}