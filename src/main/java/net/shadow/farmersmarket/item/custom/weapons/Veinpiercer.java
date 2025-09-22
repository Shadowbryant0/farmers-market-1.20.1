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
}
