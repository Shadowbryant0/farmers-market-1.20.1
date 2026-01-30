package net.shadow.farmersmarket.item.custom.weapons.reskins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.ExecutionersAxeItem;

public class FrostSkin extends ExecutionersAxeItem {
    public FrostSkin(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x00b7ff));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {

            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Inferno, stack) == 0) {
                int red = (int) (152);
                int blue = (int) (255);
                int green = (int) (226);
                return (red << 16) | (green << 8) | blue; // RGB mix
            } else {
                int red = (int) (0);
                int blue = (int) (225);
                int green = (int) (161);
                return (red << 16) | (green << 8) | blue; // RGB mix
            }

    }
}
