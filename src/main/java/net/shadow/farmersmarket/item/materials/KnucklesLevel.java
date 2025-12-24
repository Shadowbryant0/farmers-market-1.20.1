package net.shadow.farmersmarket.item.materials;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public class KnucklesLevel implements ToolMaterial {
    // Your IDE should override the interface's methods for you, or at least shout at you to do so.


    public static final KnucklesLevel IRON = new KnucklesLevel(MiningLevels.IRON, 250, 6.0f, .1,2.0f, 22, () -> Ingredient.ofItems(Items.IRON_INGOT));
    public static final KnucklesLevel NETHERITE = new KnucklesLevel(MiningLevels.NETHERITE, 2031, 9.0f,.3, 4.0f, 22, () -> Ingredient.ofItems(Items.IRON_INGOT));
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final double attackSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private KnucklesLevel(int miningLevel, int itemDurability, float miningSpeed, double attackspeed,float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackSpeed = attackspeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }
    public double getAttackSpeed() {
        return this.attackSpeed;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
