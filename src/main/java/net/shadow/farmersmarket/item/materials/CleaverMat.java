package net.shadow.farmersmarket.item.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CleaverMat implements ToolMaterial {
    // Your IDE should override the interface's methods for you, or at least shout at you to do so.

    @Override
    public int getDurability() {
        return 2031;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.0F;
    }
    @Override
    public float getAttackDamage() {
        return 3.0F;
    }
    @Override
    public int getMiningLevel() {
        return 3;
    }
    @Override
    public int getEnchantability() {
        return 30;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.BONE_BLOCK);
    }
    public static final CleaverMat INSTANCE = new CleaverMat();
}
