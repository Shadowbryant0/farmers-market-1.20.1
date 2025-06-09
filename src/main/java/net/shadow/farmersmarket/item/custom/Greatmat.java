package net.shadow.farmersmarket.item.custom;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class Greatmat implements ToolMaterial {
    // Your IDE should override the interface's methods for you, or at least shout at you to do so.

    @Override
    public int getDurability() {
        return 2031;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 9.0F;
    }
    @Override
    public float getAttackDamage() {
        return 4.0F;
    }
    @Override
    public int getMiningLevel() {
        return 3;
    }
    @Override
    public int getEnchantability() {
        return 22;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);
    }
    public static final Greatmat INSTANCE = new Greatmat();
}
