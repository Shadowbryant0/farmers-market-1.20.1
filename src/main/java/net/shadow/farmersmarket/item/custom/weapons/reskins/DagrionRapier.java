package net.shadow.farmersmarket.item.custom.weapons.reskins;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.shadow.farmersmarket.item.custom.weapons.RapierWeaponItem;

public class DagrionRapier extends RapierWeaponItem {
    public DagrionRapier(Settings settings) {
        super(settings);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xff2400 ));
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (255);
        int blue = (int) (0);
        int green = (int) (36);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
}
