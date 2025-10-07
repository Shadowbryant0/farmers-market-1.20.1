package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class Veinpiercer extends RapierWeaponItem{
    public Veinpiercer(Settings settings) {
        super(settings);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x8a0000 ));
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (227);
        int blue = (int) (218);
        int green = (int) (201);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
}
