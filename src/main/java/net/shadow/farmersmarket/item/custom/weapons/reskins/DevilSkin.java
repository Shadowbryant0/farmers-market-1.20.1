package net.shadow.farmersmarket.item.custom.weapons.reskins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.ExecutionersAxeItem;

public class DevilSkin extends ExecutionersAxeItem {
    public DevilSkin(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xd45600));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {

            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Inferno, stack) == 0) {
                int red = (int) (212);
                int blue = (int) (0);
                int green = (int) (86);
                return (red << 16) | (green << 8) | blue; // RGB mix
            } else {
                int red = (int) (255);
                int blue = (int) (0);
                int green = (int) (64);
                return (red << 16) | (green << 8) | blue; // RGB mix
            }

    }
}
