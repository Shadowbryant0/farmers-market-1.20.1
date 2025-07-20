package net.shadow.farmersmarket.item.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class TyrentMat implements ToolMaterial {
    // Your IDE should override the interface's methods for you, or at least shout at you to do so.

    @Override
    public int getDurability() {
        return 4062;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.0F;
    }
    @Override
    public float getAttackDamage() {
        return 7.0F;
    }
    @Override
    public int getMiningLevel() {
        return 4;
    }
    @Override
    public int getEnchantability() {
        return 45;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.CRYING_OBSIDIAN);
    }
    public static final TyrentMat INSTANCE = new TyrentMat();
}
