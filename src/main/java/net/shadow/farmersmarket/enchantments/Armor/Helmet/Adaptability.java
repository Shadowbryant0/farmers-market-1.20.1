package net.shadow.farmersmarket.enchantments.Armor.Helmet;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.shadow.farmersmarket.components.Armor.AdaptabilityComponent;
import net.shadow.farmersmarket.util.FarmersMarketDamageTagsCustom;

public class Adaptability extends Enchantment {

    public Adaptability() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }



    public int getProtectionAmount(int level, DamageSource source) {

        if(source.isIn(DamageTypeTags.IS_FIRE)){
            return (int) (AdaptabilityComponent.FIRE*4);
        }
        if(source.isIn(DamageTypeTags.IS_PROJECTILE)){
            return (int) (AdaptabilityComponent.PROJECTILE*4);
        }
        if(source.isIn(FarmersMarketDamageTagsCustom.KINETIC)){
            return (int) (AdaptabilityComponent.KINETIC*4);
        }
        if(source.isIn(DamageTypeTags.IS_EXPLOSION)){
            return (int) (AdaptabilityComponent.EXPLOSIVE*4);
        }
        return 0;
    }
    @Override
    public int getMinPower(int level) {
        return 10 + 10 * level;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public boolean isTreasure() {
        return false; // only from special loot tables
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public boolean isCursed() {
        return true;
    }

    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.PROTECTION;
    }


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem;

    }
}

