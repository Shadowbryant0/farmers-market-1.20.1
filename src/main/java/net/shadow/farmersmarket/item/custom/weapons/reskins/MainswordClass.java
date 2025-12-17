package net.shadow.farmersmarket.item.custom.weapons.reskins;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.shadow.farmersmarket.item.custom.weapons.GreatswordItem;

public class MainswordClass  extends GreatswordItem {
    public MainswordClass(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x4169e1));

    }

}
